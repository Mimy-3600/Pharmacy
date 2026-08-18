package front.activities;

import front.main.app.App;
import front.main.app.setter.MenuSetter;
import front.components.ActiveButton;
import front.components.CircleButton;
import front.components.LargeInput;
import front.components.MedicinePane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MedicineActivity extends VBox {

	public boolean isFromThere = false;
	private String name;
	private HBox header = new HBox(0);
	private HBox filterLayouBox = new HBox();
	public MedicinePane medicinePane = new MedicinePane();

	public MedicineActivity(String activityname) {
		name = activityname;

		HBox spacer = new HBox();
		spacer.setMinHeight(22);

		this.getChildren().add(spacer);
		this.setHeader();
		this.setFilterLayout();
		this.setContent();

		this.getStyleClass().add("medoc-activity");
	}

	public String getName() {
		return this.name;
	}


	private void setHeader() {
		HBox medocPresenter = new HBox();
		Text medocPresenterText = new Text("Médicaments");
		Region spacer1 = new Region();
		LargeInput search = new LargeInput("Rechercher des médicaments");
		Region spacer2 = new Region();
		CircleButton addButton = new CircleButton("Add");

		spacer1.setMinWidth(100);
		spacer2.setMinWidth(20);

		medocPresenterText.getStyleClass().add("medoc-presenter-text");
		medocPresenter.getChildren().add(medocPresenterText);
		medocPresenter.getStyleClass().add("medoc-presenter");

		search.setOnChange((_, newValue) -> {
			medicinePane.search(newValue);
		});

		addButton.setOnAction(_ -> {
			if(!App.displayMenu) {
				MenuSetter.show();
				MenuSetter.present("AddMedicine");

				if(!isFromThere) {
					this.retract();
				}
			} else {
				MenuSetter.hide();
				this.expand();
			}
			
		});

		header.getStyleClass().add("medoc-header");
		header.getChildren().addAll(medocPresenter, spacer1, search, spacer2, addButton);

		this.getChildren().add(header);
	}

	private void setContent() {
		medicinePane.getStyleClass().add("medoc-activity-content");
		HBox.setHgrow(medicinePane, Priority.ALWAYS);

		this.getChildren().add(medicinePane);
	}

	public void setFilterLayout() {
		HBox spacer = new HBox();
		spacer.setMinHeight(20);

		HBox filterspacer = new HBox();
		HBox.setHgrow(filterspacer, Priority.ALWAYS);

		ActiveButton all = new ActiveButton("Tous", "FIRST");
		ActiveButton out = new ActiveButton("En rupture de stock", "LAST");

		HBox.setHgrow(filterLayouBox, Priority.ALWAYS);
		filterLayouBox.getStyleClass().add("medoc-filter-layout");
		filterLayouBox.getChildren().addAll(filterspacer, all, out);

		all.enable();
		medicinePane.change("all");

		all.setOnAction(_ -> {
			out.disable();
			all.enable();

			medicinePane.change("all");
		});

		out.setOnAction(_ -> {
			all.disable();
			out.enable();

			medicinePane.change("out");
		});

		this.getChildren().addAll(spacer, filterLayouBox);
	}

	public void expand() {
		medicinePane.setMinWidth(1226);
		medicinePane.setMaxWidth(1226);
		isFromThere = false;
	}

	public void retract() {
		medicinePane.setMaxWidth(medicinePane.getWidth() - 400);
		medicinePane.setMinWidth(medicinePane.getWidth() - 400);
		isFromThere = true;
	}
}