package front.components;

import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import front.uitools.SVGStock;

public class NavButton extends VBox {

	private Button navBtn;
	private Text label;
	private boolean isActive;
	private String name;

	public NavButton(String buttonName, String svgName) {
		name = svgName;

		navBtn = new Button();
		navBtn.setGraphic(SVGStock.get(svgName));
		navBtn.getStyleClass().add("button-svg");

		label = new Text(buttonName);
		label.getStyleClass().add("nav-button-label");

		isActive = false;

		this.setSpacing(10);
		this.getChildren().addAll(navBtn, label);
		this.getStyleClass().add("nav-button");
	}

	public Button getTouchButton() {
		return this.navBtn;
	}

	public void enable() {
		navBtn.getStyleClass().add("active");
		label.getStyleClass().add("active");
		isActive = true;
	}

	public void disable() {
		navBtn.getStyleClass().remove("active");
		label.getStyleClass().remove("active");
		isActive = false;
	}

	public boolean isEnable() {
		return this.isActive;
	}


	public String getName() {
		return this.name;
	}
}