package front.activities;

import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

public class EntryActivity extends VBox {

	private String name;

	public EntryActivity(String activityname) {
		name = activityname;

		Text t = new Text(activityname);
		this.getChildren().add(t);
	}

	public String getName() {
		return this.name;
	}

}