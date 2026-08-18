package front.main.app.setter;

import java.util.Map;
import java.util.HashMap;
import front.components.NavButton;
import front.main.app.App;

public class NavigationSetter {

	private static Map<String, NavButton> navButton = new HashMap<>();
	private static String actualPage = "Main";

	public static void set() {

		NavButton mainButton = new NavButton("Principale", "Store");
		NavButton medocButton = new NavButton("Médicaments", "Medicine");
		NavButton purchaseButton = new NavButton("Achats", "Purchase");
		NavButton entryButton = new NavButton("Entrées", "Entry");
		NavButton dashboardButton = new NavButton("Recette", "Recipe");

		//Region spacer = new Region();
		//VBox.setVgrow(spacer, Priority.ALWAYS);

		//VBox themeToggle = new VBox();
		//themeToggle.getStyleClass().add("theme-toggle");

		navButton.put("Store", mainButton);
		navButton.put("Medicine", medocButton);
		navButton.put("Purchase", purchaseButton);
		navButton.put("Entry", entryButton);
		navButton.put("Recipe", dashboardButton);

		App.navigationLayout.getChildren().addAll(mainButton, medocButton, purchaseButton, entryButton, dashboardButton);
		//App.navigationLayout.getChildren().add(spacer);
		//App.navigationLayout.getChildren().add(themeToggle);

		mainButton.enable();
		MenuSetter.hide();

		for(Map.Entry<String, NavButton> navItem : navButton.entrySet()) {
			NavButton btn = navItem.getValue();

			btn.getTouchButton().setOnAction(_ -> {
				navigateTo(btn.getName());
				ContentSetter.to(btn.getName());
			});
		}
	}

	public static void navigateTo(String pageName) {
		if(actualPage != pageName) {
			for(Map.Entry<String, NavButton> navItem : navButton.entrySet()) {
				NavButton btn = navItem.getValue();

				btn.disable();
			}

			if(pageName == "Purchase" || pageName == "Recipe" || pageName == "Entry") {
				MenuSetter.show();
			} else {
				MenuSetter.hide();
			}

			actualPage = pageName;
			navButton.get(pageName).enable();
		}
	}
}