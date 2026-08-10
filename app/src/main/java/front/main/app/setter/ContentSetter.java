package front.main.app.setter;

//debug
import static java.lang.System.out;

import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import front.main.app.App;

public class ContentSetter {

	public static VBox mainContentConainer = new VBox(10);

	public static void set() {
		HBox.setHgrow(App.contentLayout, Priority.ALWAYS);
		App.contentLayout.setMinWidth(400);

		mainContentConainer.getStyleClass().add("content-container");
		VBox.setVgrow(mainContentConainer, Priority.ALWAYS);
		mainContentConainer.setMinHeight(500);

		App.contentLayout.getChildren().add(mainContentConainer);
	}

}