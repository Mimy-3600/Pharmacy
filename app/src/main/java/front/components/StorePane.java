package front.components;

import java.util.ArrayList;
import tool.TextTool;
import back.model.Medoc;
import front.main.app.App;
import front.components.medoc.MedocListStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class StorePane extends ScrollPane {
	
	private StackPane mainContainer = new StackPane();


	private ArrayList<Medoc> listsMedoc = new ArrayList<>();

	// VIEW
	private VBox mainContent = new VBox(5);
	private VBox deriveContent = new VBox(0);

	private VBox medocDeriveContent = new VBox(0);
	private Text medocSmallTitle = new Text("");

	private String actualPane = "main";

	public StorePane() {

		// BACK OPERATION
		listsMedoc = App.getMedocList();

		mainContainer.getStyleClass().add("store-pane-main-container");

		this.setMainConent();
		this.setDeriveContent();

		mainContainer.getChildren().add(mainContent);

		this.setContent(mainContainer);
		this.setFitToWidth(true);
	}

	public void setMainConent() {
		mainContent.getStyleClass().add("store-pane-main-content");
		HBox.setHgrow(mainContent, Priority.ALWAYS);
		this.setInsideNav();
		this.setMostPurchasedMedoc();
		this.setOutOfStock();
	}

	public void setDeriveContent() {
		deriveContent.getStyleClass().add("store-pane-derive-content");
		HBox.setHgrow(deriveContent, Priority.ALWAYS);
		this.setDeriveContentMedoc();
	}




	/**
	 * 
	 * Content setter
	 * 
	 * */
	public void change(String pane) {
		if(pane == "main" && actualPane != "main") {
			mainContainer.getChildren().clear();
			mainContainer.getChildren().add(mainContent);

			actualPane = pane;
		} else if (pane == "derive" && actualPane != "derive") {
			mainContainer.getChildren().clear();
			mainContainer.getChildren().add(deriveContent);

			actualPane = pane;
		}
	}


	/**
	 * 
	 * 
	 * 		MAIN CONTENT
	 * 
	 * 
	 * */
	public void setInsideNav() {
		HBox navBox = new HBox(10);
		navBox.getStyleClass().add("nav-box");
		HBox.setHgrow(navBox, Priority.ALWAYS);

		InsideNav medicineNav = new InsideNav("Médicaments", "Liste de tous les médicaments.", "Medicine");
		InsideNav purchaseNav = new InsideNav("Achats", "Exploration des achats éffectués.", "Purchase");
		InsideNav entryNav = new InsideNav("Entrées", "Gestion des stocks.", "Entry");
		InsideNav recipeNav = new InsideNav("Recette", "Visualisation des recettes.", "Recipe");

		navBox.getChildren().addAll(medicineNav, purchaseNav, entryNav, recipeNav);

		mainContent.getChildren().add(navBox);
	}


	public void setMostPurchasedMedoc() {
		HBox spacer = new HBox();
		spacer.setMinHeight(20);

		HBox mostLayoutHeaderBox = new HBox();
	 	mostLayoutHeaderBox.getStyleClass().addAll("medoc-derive-header", "medoc-main-most-header");

	 	Text mostLayoutHeaderText = new Text("Les 5 médicaments les plus vendus");
	 	mostLayoutHeaderText.getStyleClass().add("medoc-small-title");
	 	HBox spacer1 = new HBox();
	 	HBox.setHgrow(spacer1, Priority.ALWAYS);
	 	MiniButton goPurchaseButton = new MiniButton("GoMini");

	 	mostLayoutHeaderBox.getChildren().addAll(mostLayoutHeaderText, spacer1, goPurchaseButton);

	 	HBox mostLayoutContentBox = new HBox();
	 	HBox.setHgrow(mostLayoutContentBox, Priority.ALWAYS);
	 	mostLayoutContentBox.getStyleClass().add("medoc-main-most-container");

	 	goPurchaseButton.setOnAction(_ -> {
	 		App.navigateTo("Purchase");
	 	});

		mainContent.getChildren().addAll(spacer, mostLayoutHeaderBox, mostLayoutContentBox);
	}

	public void setOutOfStock() {

		HBox outHeaderBox = new HBox();
	 	outHeaderBox.getStyleClass().addAll("medoc-derive-header", "medoc-main-most-header");

	 	Text outHeaderText = new Text("Médicaments en rupture de stock");
	 	outHeaderText.getStyleClass().add("medoc-small-title");
	 	HBox spacer = new HBox();
	 	HBox.setHgrow(spacer, Priority.ALWAYS);
	 	MiniButton goEntryButton = new MiniButton("GoMini");

	 	outHeaderBox.getChildren().addAll(outHeaderText, spacer, goEntryButton);

	 	HBox outStockContent = new HBox();
	 	HBox.setHgrow(outStockContent, Priority.ALWAYS);
	 	outStockContent.getStyleClass().add("medoc-main-out-container");


	 	goEntryButton.setOnAction(_ -> {
	 		App.navigateTo("Entry");
	 	});


		mainContent.getChildren().addAll(outHeaderBox, outStockContent);
	}

	/**
	 * 
	 * 
	 * 		DERIVE CONTENT
	 * 
	 * 
	 * */
	 public void setDeriveContentMedoc() {
	 	HBox medocDeriveHeader = new HBox(10);
	 	HBox.setHgrow(medocDeriveHeader, Priority.ALWAYS);
	 	medocDeriveHeader.getStyleClass().add("medoc-derive-header");

	 	medocSmallTitle.getStyleClass().add("medoc-small-title");

	 	medocDeriveHeader.getChildren().add(medocSmallTitle);

	 	HBox.setHgrow(medocDeriveContent, Priority.ALWAYS);
	 	medocDeriveContent.getStyleClass().add("medoc-derive-content");

	 	deriveContent.getChildren().addAll(medocDeriveHeader, medocDeriveContent);
	 }


	 public void find(String search) {
	 	medocDeriveContent.getChildren().clear();


	 	ArrayList<Medoc> filtered = new ArrayList<>();
	 	
	 	for(Medoc medoc : listsMedoc) {
	 		String searchableMedocDesignation = TextTool.normalizeText(medoc.getMedocDesignation().toLowerCase());

	 		if(searchableMedocDesignation.contains(search.toLowerCase())) {
	 			filtered.add(medoc);
	 		}
	 	}

	 	if(filtered.size() == 0) {
	 		medocSmallTitle.setText("Auccun médicament trouvé");
	 	} else if(filtered.size() == 1) {
	 		medocSmallTitle.setText("1 médicament trouvé");
	 	} else {
	 		medocSmallTitle.setText(filtered.size() + " médicaments trouvés");
	 	}

 		for(Medoc filterMedoc: filtered) {
 			MedocListStyle medoc = new MedocListStyle(filterMedoc);

 			medocDeriveContent.getChildren().add(medoc);
 		}
	 }
}