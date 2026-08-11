package front.components;

import front.components.CircleButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;

public class LargeInput extends HBox {
	private TextField searchField = new TextField();
	private CircleButton searchButton = new CircleButton("Search");
	private CircleButton resetButton = new CircleButton("Reset");

	public LargeInput() {
		this.getStyleClass().add("large-input");

		searchField.setPromptText("Rechercher des médicaments, des achats ou des entrées"); 
		searchField.getStyleClass().add("large-input-search-field");

		HBox.setHgrow(this, Priority.ALWAYS);
		HBox.setHgrow(searchField, Priority.ALWAYS);
		this.getChildren().addAll(searchButton, searchField, resetButton);

		resetButton.setOnAction(e -> {
			searchField.clear();
		});
	}
}