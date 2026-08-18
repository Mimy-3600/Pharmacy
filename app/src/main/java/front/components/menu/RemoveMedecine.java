package front.components.menu;

import front.main.app.App;
import front.main.app.setter.MenuSetter;

import java.math.BigDecimal;
import back.model.Medoc;
import back.repository.MedocRepository;
import front.activities.ActivityManager;
import front.activities.MedicineActivity;
import front.components.CircleButton;
import front.components.LegitButton;
import front.components.MedicinePane;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RemoveMedecine extends VBox{
	private LegitButton cancel = new LegitButton("Annuler");
	private LegitButton confirm = new LegitButton("Confirmer");

	private Medoc actualMedoc = new Medoc("XXXXX", "Unknown", new BigDecimal(0), 0, false);

	public RemoveMedecine(Medoc medoc) {
		this.getStyleClass().add("remove-medicine");

		actualMedoc = medoc;

		this.setSpacing(20);
		this.setAction();
		this.setContent();
	}

	private void setAction() {
		HBox actionBar = new HBox();
		HBox spacer =new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		CircleButton closeButton = new CircleButton("Close");

		actionBar.getStyleClass().add("add-medicine-action-bar");
		actionBar.getChildren().addAll(spacer, closeButton);

		closeButton.setOnAction(_ -> {
			Node medPane = App.contentLayout.getChildren().getFirst();
			ActivityManager activityManager = (ActivityManager)medPane;

			MedicineActivity med = (MedicineActivity)activityManager.getActivityByName("Medicine");	
			MenuSetter.hide();
			med.expand();
			med.isFromThere = false;

			this.getChildren().clear();
		});

		this.getChildren().add(actionBar);
	}

	public void setContent() {
		VBox confirmBox = new VBox(10);
		VBox spacer3 = new VBox();
		VBox.setVgrow(spacer3, Priority.ALWAYS);
		confirmBox.getStyleClass().add("remove-medicine-action-box");

		VBox medocInfo = new VBox(20);
		Text question = new Text("Voulez vous supprimez le médicament, ");
		Text medocName = new Text(actualMedoc.getMedocDesignation());
		medocName.setWrappingWidth(300);
		medocName.getStyleClass().add("remove-medicine-medoc-name");
		Text questionMark = new Text(" ?");

		cancel.enable();
		confirm.enable();

		confirm.setOnAction(_ -> this.deleteMedoc());

		cancel.setOnAction(_ -> {
			Node medPane = App.contentLayout.getChildren().getFirst();
			ActivityManager activityManager = (ActivityManager)medPane;

			MedicineActivity med = (MedicineActivity)activityManager.getActivityByName("Medicine");	
			MenuSetter.hide();
			med.expand();
			med.isFromThere = false;

			this.getChildren().clear();
		});

		medocInfo.getChildren().addAll(question, medocName, questionMark);

		confirmBox.getChildren().addAll(cancel, confirm);

		this.getChildren().addAll(medocInfo, spacer3, confirmBox);
	}

	private void deleteMedoc() {
		if(MedocRepository.deleteMedoc(actualMedoc)) {
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