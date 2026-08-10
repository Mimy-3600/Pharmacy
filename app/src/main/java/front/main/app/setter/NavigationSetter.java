package front.main.app.setter;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import front.uitools.Animation;
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
		hideMenu();

		for(Map.Entry<String, NavButton> navItem : navButton.entrySet()) {
			NavButton btn = navItem.getValue();
			String page = navItem.getKey();

			btn.getTouchButton().setOnAction(event -> {
				navigateTo(btn.getName());
				if(btn.getName() == "Purchase" || btn.getName() == "Recipe") {
					showMenu();
				} else {
					hideMenu();
				}
			});
		}
	}

	public static void navigateTo(String pageName) {
		if(actualPage != pageName) {
			for(Map.Entry<String, NavButton> navItem : navButton.entrySet()) {
				NavButton btn = navItem.getValue();
				String page = navItem.getKey();

				btn.disable();
			}

			actualPage = pageName;
			navButton.get(pageName).enable();
		}
	}


	/**
	 * 
	 * toggle menu
	 *
	 * Animation
	 *
	 * */
	 public static void showMenu() {
	 	if(!App.displayMenu) {
			Timeline displayBlock = Animation.getWidthAnimation(App.menuLayout, 200, 300);
			displayBlock.play();

			App.displayMenu = true;
	 	}
	 }

	 public static void hideMenu() {
	 	if(App.displayMenu) {
	 		Timeline displayNone = Animation.getWidthAnimation(App.menuLayout, 200, 0);
			displayNone.play();

			App.displayMenu = false;
	 	}
	 }
}