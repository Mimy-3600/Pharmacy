package front.main.app.setter;

import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import front.uitools.Animation;
import front.components.NavButton;
import front.main.app.App;

public class NavigationSetter {

	public static void set() {
		NavButton btn = new NavButton("qjox", "Medicine");
		NavButton btn2 = new NavButton("Fonts", "Medicine");
		NavButton btn3 = new NavButton("brown", "Medicine");
		NavButton btn4 = new NavButton("Medicine", "Medicine");
		NavButton btn5 = new NavButton("Medicine", "Medicine");

		App.navigationLayout.getChildren().addAll(btn, btn2, btn3, btn4, btn5);

		/**
		 * Animation
		 * */
		btn.getTouchButton().setOnAction(event -> {
			if(App.displayMenu) {
				Timeline displayNone = Animation.getWidthAnimation(App.menuLayout, 200, 0);
				displayNone.play();
			} else {
				Timeline displayBlock = Animation.getWidthAnimation(App.menuLayout, 300, 300);
				displayBlock.play();
			}

			App.displayMenu = !App.displayMenu;
		});
	}
}