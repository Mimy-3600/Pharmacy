package front.components;

import front.main.app.App;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class InsideNav extends VBox {

	private String navName;

	public InsideNav(String name, String description, String pageName) {
		navName = name;

		Text textName = new Text(navName);
		textName.getStyleClass().add("inside-nav-text");


		HBox descriptionLayout = new HBox(10);
		descriptionLayout.getStyleClass().add("inside-nav-desc-layout");
		HBox.setHgrow(descriptionLayout, Priority.ALWAYS);

		Text descriptionIn = new Text(description);
		descriptionIn.getStyleClass().add("inside-nav-desc");

		HBox spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		CircleButton goButton = new CircleButton("Go");

		descriptionLayout.getChildren().addAll(descriptionIn, spacer, goButton);

		goButton.setOnAction(_ -> {
			App.navigateTo(pageName);
		});

		this.getStyleClass().add("inside-nav");
		this.getChildren().addAll(textName, descriptionLayout);
	}
}