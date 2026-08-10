package front.components;

import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import front.uitools.SVGStock;

public class NavButton extends VBox {

	private Button navBtn;
	private Text label;

	public NavButton(String buttonName, String svgName) {
		navBtn = new Button();
		navBtn.setGraphic(SVGStock.get(svgName));
		navBtn.getStyleClass().add("button-svg");

		label = new Text(buttonName);
		label.getStyleClass().add("nav-button-label");

		this.setSpacing(10);
		this.getChildren().addAll(navBtn, label);
		this.getStyleClass().add("nav-button");
	}

	public Button getTouchButton() {
		return this.navBtn;
	}

}