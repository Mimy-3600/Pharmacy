package front.components;

import javafx.scene.control.Button;

public class ActiveButton extends Button {

	private boolean isEnable = false;

	public ActiveButton(String name, String position) {
		this.setText(name);
		this.getStyleClass().addAll("active-button", position);
	}


	public void enable() {
		if(this.isEnable) {
			return;
		}
		this.getStyleClass().add("active");
		this.isEnable = true;
	}

	public void disable() {
		if(!this.isEnable) {
			return;
		}
		this.getStyleClass().remove("active");
		this.isEnable = false;
	}

	public boolean getIsActive() {
		return this.isEnable;
	}
}