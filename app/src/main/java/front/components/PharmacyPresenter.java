package front.components;

import front.uitools.Img;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PharmacyPresenter extends HBox {

	public PharmacyPresenter() {
		this.getStyleClass().add("pharmacy-presenter");

		ImageView logo = new ImageView(Img.getImg("pharmacy.png"));
		logo.getStyleClass().add("pharmacy-presenter-logo");
		logo.setFitWidth(60);
		logo.setFitHeight(60);
		logo.setSmooth(true);
		HBox.setMargin(logo, new Insets(0, 10, 0, 0));

		Text phar = new Text("Phar");
		Text macy = new Text("macy");
		phar.getStyleClass().add("phar");
		macy.getStyleClass().add("macy");

		this.getChildren().addAll(logo, phar, macy);
	}
}