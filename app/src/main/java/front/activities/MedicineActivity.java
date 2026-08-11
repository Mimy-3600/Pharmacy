package front.activities;

import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

public class MedicineActivity extends VBox {

	private String name;

	public MedicineActivity(String activityname) {
		name = activityname;

		Text t = new Text(activityname);
		this.getChildren().add(t);
	}

	public String getName() {
		return this.name;
	}
}