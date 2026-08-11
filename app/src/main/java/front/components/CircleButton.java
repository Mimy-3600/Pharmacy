package front.components;

import front.uitools.SVGStock;
import javafx.scene.control.Button;

public class CircleButton extends Button{

	public CircleButton(String svg) {
		this.getStyleClass().add("circle-button");
		this.setGraphic(SVGStock.get(svg));
	}

}