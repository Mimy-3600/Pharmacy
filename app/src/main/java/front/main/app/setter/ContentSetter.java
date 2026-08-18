package front.main.app.setter;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import front.main.app.App;
import front.activities.ActivityManager;

public class ContentSetter {

	public static VBox mainContentConainer = new VBox(10);
	public static ActivityManager activityManager = new ActivityManager();

	public static void set() {
		HBox.setHgrow(App.contentLayout, Priority.ALWAYS);
		App.contentLayout.setMinWidth(400);

		HBox.setHgrow(activityManager, Priority.ALWAYS);

		App.contentLayout.getChildren().add(activityManager);
	}

	public static void to(String name) {
		activityManager.setActivity(name);
	}
}