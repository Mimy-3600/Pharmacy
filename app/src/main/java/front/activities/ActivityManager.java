package front.activities;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import front.activities.StoreActivity;
import front.activities.MedicineActivity;
import front.activities.PurchaseActivity;
import front.activities.EntryActivity;
import front.activities.RecipeActivity;

public class ActivityManager extends StackPane {

	// List activities
	private Map<String, VBox> activities = new HashMap<>(); 

	private StoreActivity storeActivity = new StoreActivity("Store");

	private MedicineActivity medicineActivity = new MedicineActivity("Medicine");

	private PurchaseActivity purchaseActivity = new PurchaseActivity("Purchase");

	private EntryActivity entryActivity = new EntryActivity("Entry");

	private RecipeActivity recipeActivity = new RecipeActivity("Recipe");

	public ActivityManager() {
		this.setStyle();
		this.setUp();
		this.getChildren().addAll(storeActivity);
	}


	private void setUp() {
		activities.put(storeActivity.getName(), storeActivity);
		activities.put(medicineActivity.getName(), medicineActivity);
		activities.put(purchaseActivity.getName(), purchaseActivity);
		activities.put(entryActivity.getName(), entryActivity);
		activities.put(recipeActivity.getName(), recipeActivity);
	}

	public void setStyle() {
		this.getStyleClass().add("store-activity");
	}

	public void setActivity(String name) {
		this.getChildren().clear();
		this.getChildren().addAll(activities.get(name));
	}
}