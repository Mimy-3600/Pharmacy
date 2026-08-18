package front.components.medoc;

import back.model.Medoc;
import front.activities.ActivityManager;
import front.activities.MedicineActivity;
import front.components.MiniButton;
import front.main.app.App;
import front.main.app.setter.MenuSetter;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MedocCardStyle extends VBox {
	private Medoc actualMedoc;

	public MedocCardStyle(Medoc medoc) {
		actualMedoc = medoc;

		Text number = new Text(medoc.getMedocNumber());
		number.getStyleClass().add("medoc-card-number");

		HBox spacer = new HBox();
		spacer.setMinHeight(20);

		Text desigantion = new Text(medoc.getMedocDesignation());
		desigantion.getStyleClass().add("medoc-card-desigantion");
		desigantion.setWrappingWidth(200);

		VBox spacer2 = new VBox();
		VBox.setVgrow(spacer2, Priority.ALWAYS);

		HBox info = new HBox();
		HBox spacer3 = new HBox();
		HBox.setHgrow(spacer3, Priority.ALWAYS);
		Text stock = new Text(medoc.getMedocStock() + " en stock");
		Text price = new Text(medoc.getMedocUnitPrice() + " Ar");
		if(medoc.getMedocStock() <= 5) {
			stock.getStyleClass().add("medoc-card-stock");
		}
		price.getStyleClass().add("medoc-card-price");
		info.getStyleClass().add("medoc-card-info");

		info.getChildren().addAll(stock, spacer3, price);

		HBox action = new HBox();
		HBox spacer4 = new HBox();
		HBox.setHgrow(spacer4, Priority.ALWAYS);
		action.getStyleClass().add("medoc-card-action");

		MiniButton deleteButton = new MiniButton("Delete");
		MiniButton editButton = new MiniButton("Edit");
		MiniButton purchaseButton = new MiniButton("BagMini");
		MiniButton entryButton = new MiniButton("Box");

		deleteButton.setOnAction(_ -> this.menuSet());

		action.getChildren().addAll(spacer4, entryButton, purchaseButton, editButton, deleteButton);

		this.getChildren().addAll(number, spacer, desigantion, spacer2, info, action);
		this.getStyleClass().add("medoc-card");
	}

	public void menuSet() {
		MenuSetter.show();

		Node medPane = App.contentLayout.getChildren().getFirst();
		ActivityManager activityManager = (ActivityManager)medPane;

		MedicineActivity med = (MedicineActivity)activityManager.getActivityByName("Medicine");	

		if(!App.displayMenu) {
			med.retract();
			App.displayMenu = true;
		}

		MenuSetter.setMedoc(actualMedoc);
		MenuSetter.present("RemoveMedicine");
	}
}