package org.main.app.setter;

import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.animation.Timeline;
import org.uitools.Animation;
import org.main.app.App;

public class NavigationSetter {

	public static void set() {
		Button btn = new Button("Click me !!");

		App.navigationLayout.getChildren().add(btn);

		/**
		 * Animation
		 * */
		btn.setOnAction(event -> {
			if(App.displayMenu) {
				Timeline displayNone = Animation.getWidthAnimation(App.menuLayout, 200, 0);
				displayNone.play();
			} else {
				Timeline displayBlock = Animation.getWidthAnimation(App.menuLayout, 250, 250);
				displayBlock.play();
			}

			App.displayMenu = !App.displayMenu;
		});
	}
}