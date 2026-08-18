package front.components.menu;

import tool.TextTool;
import java.math.BigDecimal;
import java.util.function.UnaryOperator;
import back.model.Medoc;
import back.repository.MedocRepository;
import front.main.app.App;
import front.main.app.setter.MenuSetter;
import front.components.LegitButton;
import front.components.MedicinePane;
import front.activities.ActivityManager;
import front.activities.MedicineActivity;
import front.components.CircleButton;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddMedicine extends VBox {

	private TextField medocnumberField = new TextField();
	private TextField medocNameField = new TextField();
	private TextField medocPriceField = new TextField();

	private LegitButton register = new LegitButton("Enregistrer");

	public AddMedicine() {
		this.getStyleClass().add("add-medicine");

		this.setSpacing(10);
		this.setAction();
		this.setContent();
	}

	public void setAction() {
		HBox actionBar = new HBox();
		HBox spacer =new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		CircleButton resetButton = new CircleButton("Reset");
		CircleButton closeButton = new CircleButton("Close");

		resetButton.setOnAction(_ -> this.reset());

		actionBar.getStyleClass().add("add-medicine-action-bar");
		actionBar.getChildren().addAll(spacer, resetButton, closeButton);

		closeButton.setOnAction(_ -> {
			Node medPane = App.contentLayout.getChildren().getFirst();
			ActivityManager activityManager = (ActivityManager)medPane;

			MedicineActivity med = (MedicineActivity)activityManager.getActivityByName("Medicine");	

			MenuSetter.hide();
			med.expand();
		});

		this.getChildren().add(actionBar);
	}

	private void reset() {
		medocnumberField.setText(TextTool.getSaltString(6));
		medocNameField.setText("");
		medocPriceField.setText("");
	}

	public void setContent() {
		Text medocNumber = new Text("Numero médicament : ");
		medocNumber.getStyleClass().add("add-medicine-label");
		medocnumberField.getStyleClass().addAll("add-medicine-input", "number");

		Text medocName = new Text("Nom du médicament");
		medocName.getStyleClass().add("add-medicine-label");
		medocNameField.getStyleClass().add("add-medicine-input");

		Text medocPrice = new Text("Prix unitaire médicament");
		medocPrice.getStyleClass().add("add-medicine-label");
		medocPriceField.getStyleClass().addAll("add-medicine-input", "price");

		medocnumberField.setText(TextTool.getSaltString(6));
		medocPriceField.setText("");

		HBox spacer = new HBox();
		VBox.setVgrow(spacer, Priority.ALWAYS);

		HBox priceText = new HBox();
		priceText.getStyleClass().add("add-medicine-price-text");
		HBox spacer2 = new HBox();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		Text price = new Text("0.0 Ar");


		HBox acceptBox = new HBox();
		HBox spacer3 = new HBox();
		HBox.setHgrow(spacer3, Priority.ALWAYS);

		UnaryOperator<TextFormatter.Change> filter = change ->  {
			String text = change.getControlNewText();
			if(text.matches("\\d*(\\.\\d{0,2})?")) {
				if(text.length() != 0) {				
					price.setText(Float.parseFloat(text) + " Ar");
				} else {
					price.setText("0.0 Ar");
				}
				return change;
			}

			return null;
		};

		medocPriceField.setTextFormatter(new TextFormatter<>(filter));

		priceText.getChildren().addAll(spacer2, price);
		acceptBox.getChildren().addAll(spacer3, register);

		medocnumberField.textProperty().addListener((_, _, _) -> this.verify());
		medocNameField.textProperty().addListener((_, _, _) -> this.verify());
		medocPriceField.textProperty().addListener((_, _, _) -> this.verify());

		register.setOnAction(_ -> this.save());

		this.getChildren().addAll(medocNumber, medocnumberField, medocName, medocNameField, medocPrice, medocPriceField, spacer, priceText, acceptBox);
	}

	private void verify() {
		if(medocnumberField.getText().isBlank() 
			|| medocNameField.getText().isBlank() 
			|| (medocPriceField.getText().isBlank() || Float.parseFloat(medocPriceField.getText()) == 0.0)
		) {
			register.disable();
		} else {
			register.enable();
		}
	}

	private void save() {
		if(!register.getIsEnable()) {
			return;
		} else {
			Medoc medoc = new Medoc(
				medocnumberField.getText(), 
				medocNameField.getText(), 
				new BigDecimal(medocPriceField.getText()), 
				0, 
				true
			);

			if(MedocRepository.registerNewMedoc(medoc)) {
				System.out.println("Ok");
				this.reset();
				App.refresh();

				Node medPane = App.contentLayout.getChildren().getFirst();
				ActivityManager activityManager = (ActivityManager)medPane;

				MedicineActivity med = (MedicineActivity)activityManager.getActivityByName("Medicine");	
				MedicinePane medpane = med.medicinePane;

				medpane.refresh();


				MenuSetter.hide();
				medpane.setMinWidth(1226);
			}
		}
	}


}