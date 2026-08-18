package front.activities;

import javafx.scene.text.Text;
import front.components.CircleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PurchaseActivity extends VBox {

	private HBox header = new HBox();

	private String name;

	public PurchaseActivity(String activityname) {
		name = activityname;

		this.setHeader();
	}

	public String getName() {
		return this.name;
	}

	private void setHeader() {
		HBox purchasePresenter = new HBox();
		Text purchaseresenterText = new Text("Achats");
		Region spacer1 = new Region();
		CircleButton noticationButton = new CircleButton("Notification");

		HBox.setHgrow(spacer1, Priority.ALWAYS);

		purchaseresenterText.getStyleClass().add("purchase-presenter-text");
		purchasePresenter.getChildren().add(purchaseresenterText);
		purchasePresenter.getStyleClass().add("purchase-presenter");


		header.getStyleClass().add("purchase-header");
		header.getChildren().addAll(purchasePresenter, spacer1, noticationButton);

		this.getChildren().add(header);
	}
}