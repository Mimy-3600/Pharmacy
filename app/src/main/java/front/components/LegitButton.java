package front.components;

import javafx.scene.control.Button;;

public class LegitButton extends Button {

	private boolean isEnable = false;

	public LegitButton(String buttonName) {
		super(buttonName);

		this.getStyleClass().add("legit-button");
	}


	public void enable() {
		if(isEnable) return;
		this.getStyleClass().add("enable");
		isEnable = true;
	}

	public void disable() {
		if(!isEnable) return;
		this.getStyleClass().remove("enable");
		isEnable = false;
	}

	public boolean getIsEnable() {
		return this.isEnable;
	}
}