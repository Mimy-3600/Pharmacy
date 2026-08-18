package front.components.medoc;

import back.model.Medoc;
import front.components.CircleButton;
import front.uitools.Img;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MedocListStyle extends HBox {

	private Medoc m_medoc;

	public MedocListStyle(Medoc medoc) {
		this.getStyleClass().add("medoc-list-style");
		HBox.setHgrow(this, Priority.ALWAYS);
		m_medoc = medoc;

		this.setSpacing(10);
		this.setContent();
	}

	public Medoc getMedoc() {
		return this.m_medoc;
	}


	private void setContent() {
		// ImageView img = new ImageView(Img.getImg("pharmacy.png"));
		// img.getStyleClass().add("medoc-list-style-img");
		// img.setFitHeight(50);
		// img.setFitWidth(50);


		VBox medocInfo = new VBox(0);
		Text medocNumber = new Text(m_medoc.getMedocNumber());
		Text medocDesignation = new Text(m_medoc.getMedocDesignation());
		medocNumber.getStyleClass().add("medoc-list-style-number");
		medocDesignation.getStyleClass().add("medoc-list-style-designation");
		medocInfo.getChildren().addAll(medocNumber, medocDesignation);

		HBox spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox medocStockC = new HBox();
		Text medocStock = new Text("stock: " + m_medoc.getMedocStock());
		medocStock.getStyleClass().add("medoc-list-style-stock");
		medocStockC.getStyleClass().add("medoc-list-style-stock-c");
		if(m_medoc.getMedocStock() <= 5) {
			medocStock.getStyleClass().add("urgent");
			medocStockC.getStyleClass().add("urgent");
		}
		medocStockC.getChildren().add(medocStock);

		HBox medocUnitPriceC = new HBox();
		Text ar = new Text("Ar ");
		HBox spacer2 = new HBox();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		Text medocUnitPrice = new Text("" + m_medoc.getMedocUnitPrice());
		ar.getStyleClass().add("medoc-list-style-ar");
		medocUnitPrice.getStyleClass().add("medoc-list-style-unit-price");
		medocUnitPriceC.getStyleClass().add("medoc-list-style-unit-price-c");
		medocUnitPriceC.getChildren().addAll(ar, spacer2, medocUnitPrice);

		CircleButton purchaseButton = new CircleButton("Bag");

		this.getChildren().addAll(medocInfo, spacer, medocStockC, medocUnitPriceC, purchaseButton);
	}
}