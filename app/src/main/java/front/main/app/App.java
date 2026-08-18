package front.main.app;

import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import front.main.app.setter.NavigationSetter;
import front.main.app.setter.ContentSetter;
import front.main.app.setter.MenuSetter;
import back.model.Medoc;
import back.repository.MedocRepository;;

public class App extends Application {

	public static boolean displayMenu = true;

	public static VBox navigationLayout = new VBox(10);

	public static StackPane menuLayout = new StackPane();

	public static HBox contentLayout = new HBox(0);

	private static ArrayList<Medoc> _listsMedocs = new ArrayList<>();

	// WINDOW SIZE
	public static double windowWidth = 0;
	public static double windowHeight = 0;

	@Override
	public void start(Stage stage) {
		_listsMedocs = MedocRepository.getListsMedoc();

		this.loadFont();
		this.setUpMainLayout(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}



	/**
	 * 
	 * Set up main layout
	 * - styling and position
	 * */

	 // MAIN SETUP
	public void setUpMainLayout(Stage stage) {
		/////////////////////////////////////
		// MAIN LAYOUT
		/////////////////////////////////////
		HBox mainLayout = new HBox(0);

		// NAV LAYOUT
		navigationLayout.getStyleClass().add("navigation-layout");

		// MENU LAYOUT
		menuLayout.getStyleClass().add("menu-layout");

		// CONTENT LAYOUT
		contentLayout.getStyleClass().add("content-layout");

		/**
		 * Include content and navigation layout
		 * */
		mainLayout.getChildren().addAll(navigationLayout, menuLayout, contentLayout);

		/**
		 * setup
		 * */
		NavigationSetter.set();
		ContentSetter.set();
		MenuSetter.set();

		/**
		 * Set up main Scene with Layout
		 * */
		Scene mainScene = new Scene(mainLayout);
		this.setStyle(mainScene);

		windowHeight = mainScene.getHeight();
		windowWidth = mainScene.getWidth();

		stage.setMaximized(true);
		stage.setTitle("Pharmacy");
		stage.setScene(mainScene);
		stage.show();
	}


	/**
	 * 
	 * Load fonts
	 * 
	 * */
	 private void loadFont() {
		Font.loadFont(getClass().getResourceAsStream("/fonts/GoogleSansFlex_24pt-Regular.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/fonts/GoogleSansFlex_24pt-Bold.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/fonts/GoogleSansFlex_24pt-Medium.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/fonts/GoogleSansFlex_24pt-Light.ttf"), 14);
	 }


	/**
	 * Set Style
	 * */
	public void setStyle(Scene scene){

		ArrayList<String> CSS = new ArrayList<>(List.of(
			getClass().getResource("/style/root.css").toExternalForm(),
			getClass().getResource("/style/navigation-layout.css").toExternalForm(),
			getClass().getResource("/style/menu-layout.css").toExternalForm(),
			getClass().getResource("/style/content-layout.css").toExternalForm(),
			getClass().getResource("/style/nav-button.css").toExternalForm(),
			getClass().getResource("/style/store-pane.css").toExternalForm(),
			getClass().getResource("/style/store-activity.css").toExternalForm(),
			getClass().getResource("/style/medicine-activity.css").toExternalForm(),
			getClass().getResource("/style/purchase-activity.css").toExternalForm(),
			getClass().getResource("/style/entry-activity.css").toExternalForm(),
			getClass().getResource("/style/medoc.css").toExternalForm(),
			getClass().getResource("/style/button.css").toExternalForm(),
			getClass().getResource("/style/add-medicine.css").toExternalForm(),
			getClass().getResource("/style/remove-medicine.css").toExternalForm()
		));


		scene.getStylesheets().addAll(CSS);
	}

	public static void navigateTo(String name) {
		NavigationSetter.navigateTo(name);
		ContentSetter.to(name);
	}


	public static ArrayList<Medoc> getMedocList() {
		return _listsMedocs;
	}

	public static void refresh() {
		_listsMedocs.clear();
		_listsMedocs = MedocRepository.getListsMedoc();
	}
}