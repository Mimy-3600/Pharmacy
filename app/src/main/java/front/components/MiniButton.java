package front.components;

import front.uitools.SVGStock;
import javafx.scene.control.Button;;

public class MiniButton extends Button {

	public MiniButton(String svg) {
		this.setGraphic(SVGStock.get(svg));
		this.getStyleClass().add("mini-button");
	}

}