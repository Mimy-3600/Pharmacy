package front.components;

import front.interfaces.OnChange;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;


public class LargeInput extends HBox {
	private TextField searchField = new TextField();
	private CircleButton searchButton = new CircleButton("Search");
	private CircleButton resetButton = new CircleButton("Reset");
	private OnChange onChange;

	public LargeInput(String prompt) {
		this.getStyleClass().add("large-input");

		searchField.setPromptText(prompt); 
		searchField.getStyleClass().add("large-input-search-field");

		HBox.setHgrow(this, Priority.ALWAYS);
		HBox.setHgrow(searchField, Priority.ALWAYS);
		this.getChildren().addAll(searchButton, searchField, resetButton);

		resetButton.setOnAction(_ -> {
			searchField.clear();
		});

		searchField.textProperty().addListener((_, oldValue, newValue) -> {
			if(onChange != null) {
				onChange.changed(oldValue, newValue);
			}
		});
	}

	public void setOnChange(OnChange callback) {
		this.onChange = callback;
	}
}