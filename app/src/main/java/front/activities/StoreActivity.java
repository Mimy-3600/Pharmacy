package front.activities;

import front.components.CircleButton;
import front.components.LargeInput;
import front.components.PharmacyPresenter;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class StoreActivity extends VBox {

	private String name;
	private HBox header = new HBox();
	private StackPane content = new StackPane();

	public StoreActivity(String activityname) {
		name = activityname;

		this.setHeader();
		this.setContent();
		this.setClass();
		this.getChildren().addAll(header, content);
	}

	public String getName() {
		return this.name;
	}

	private void setClass() {
		header.getStyleClass().add("store-activity-header");
		content.getStyleClass().add("store-activity-content");
	}

	private void setHeader() {
		PharmacyPresenter presenter = new PharmacyPresenter();
		Region spacer1 = new Region();
		LargeInput search = new LargeInput();
		Region spacer2 = new Region();
		CircleButton notificationButton = new CircleButton("Notification");

		spacer1.getStyleClass().add("spacer");
		spacer2.getStyleClass().add("spacer");

		header.getChildren().addAll(presenter, spacer1, search, spacer2, notificationButton);
	}

	private void setContent() {
		
	}
}