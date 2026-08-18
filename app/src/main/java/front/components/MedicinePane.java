package front.components;

import java.util.ArrayList;
import back.model.Medoc;
import front.components.medoc.MedocCardStyle;
import front.main.app.App;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tool.TextTool;

public class MedicinePane extends ScrollPane {

	public ArrayList<Medoc> _listsMedocs = new ArrayList<>();

	private VBox medicineMain = new VBox();
	private StackPane medicinecontainer = new StackPane();

	private VBox allMedocs = new VBox();
	private VBox outMedocs = new VBox();

	public MedicinePane() {
		//BACK OPERATION
		_listsMedocs = App.getMedocList();


		this.setContent(medicineMain);
		this.setFitToWidth(true);
		this.setAllMedoc(_listsMedocs);
		this.setOutMedoc(_listsMedocs);

		this.setMedicineContent();
	}

	public void setMedicineContent() {
		HBox.setHgrow(medicinecontainer, Priority.ALWAYS);
		medicinecontainer.getStyleClass().add("medicine-main-container");

		medicineMain.getChildren().add(medicinecontainer);
	}

	private void setAllMedoc(ArrayList<Medoc> medocsList) {
		allMedocs.getChildren().clear();

		int k = 0;
		int row    = (medocsList.size() / 5) + 1;
		int column = 5;

		for(int i = 0; i < row; i++) {
			HBox rowMedoc = new HBox();
			HBox.setHgrow(rowMedoc, Priority.ALWAYS);
			rowMedoc.setMinHeight(242);

			for(int j = 0; j < column; j++) {
				HBox columnMedoc = new HBox();
				columnMedoc.setMinWidth(242);
				columnMedoc.getStyleClass().add("card");

				if(k < medocsList.size()) {
					MedocCardStyle medoc = new MedocCardStyle(medocsList.get(k));

					columnMedoc.getChildren().add(medoc);
				}

				rowMedoc.getChildren().add(columnMedoc);
				k++;
			}

			allMedocs.getChildren().add(rowMedoc);
		}

		allMedocs.getStyleClass().add("all-medoc-container");
	}

	private void setOutMedoc(ArrayList<Medoc> medocsList) {
		outMedocs.getChildren().clear();

		ArrayList<Medoc> _filteredMedoc = new ArrayList<>();

		for(Medoc medoc : medocsList) {
			if(medoc.getMedocStock() <= 5) {
				_filteredMedoc.add(medoc);
			}
		}

		int k = 0;
		int row    = (_filteredMedoc.size() / 5) + 1;
		int column = 5;

		for(int i = 0; i < row; i++) {
			HBox rowMedoc = new HBox();
			HBox.setHgrow(rowMedoc, Priority.ALWAYS);
			rowMedoc.setMinHeight(242);

			for(int j = 0; j < column; j++) {
				HBox columnMedoc = new HBox();
				columnMedoc.setMinWidth(242);
				columnMedoc.getStyleClass().add("card");

				if(k < _filteredMedoc.size()) {
					MedocCardStyle medoc = new MedocCardStyle(_filteredMedoc.get(k));

					columnMedoc.getChildren().add(medoc);
				}

				rowMedoc.getChildren().add(columnMedoc);
				k++;
			}

			outMedocs.getChildren().add(rowMedoc);
		}

		outMedocs.getStyleClass().add("out-medoc-container");
	}


	public void change(String panename) {
		if(panename == "all") {
			medicinecontainer.getChildren().clear();
			medicinecontainer.getChildren().add(allMedocs);
		} else if(panename == "out") {
			medicinecontainer.getChildren().clear();
			medicinecontainer.getChildren().add(outMedocs);
		}
	}

	public void search(String searchText) {
		if(searchText != "") {
			ArrayList<Medoc> _filtered = new ArrayList<>();

			for(Medoc medoc : _listsMedocs) {
				String searchableName = TextTool.normalizeText(medoc.getMedocDesignation().toLowerCase());

				if(searchableName.contains(searchText)) {
					_filtered.add(medoc);
				}
			}

			this.setAllMedoc(_filtered);
			this.setOutMedoc(_filtered);

		} else {
			this.setAllMedoc(_listsMedocs);
			this.setOutMedoc(_listsMedocs);
		}
	}
}