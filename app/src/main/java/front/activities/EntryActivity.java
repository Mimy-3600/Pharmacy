package front.activities;

import javafx.scene.text.Text;
import front.components.CircleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class EntryActivity extends VBox {

	private HBox header = new HBox();

	private String name;

	public EntryActivity(String activityname) {
		name = activityname;

		this.setHeader();
	}

	public String getName() {
		return this.name;
	}

	private void setHeader() {
		HBox entryPresenter = new HBox();
		Text entryresenterText = new Text("Entrées");
		Region spacer1 = new Region();
		CircleButton noticationButton = new CircleButton("Notification");

		HBox.setHgrow(spacer1, Priority.ALWAYS);

		entryresenterText.getStyleClass().add("entry-presenter-text");
		entryPresenter.getChildren().add(entryresenterText);
		entryPresenter.getStyleClass().add("entry-presenter");


		header.getStyleClass().add("entry-header");
		header.getChildren().addAll(entryPresenter, spacer1, noticationButton);

		this.getChildren().add(header);
	}

}