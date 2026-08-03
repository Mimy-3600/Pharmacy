package org.main.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import org.main.app.setter.NavigationSetter;

public class App extends Application {

	public static boolean displayMenu = true;

	public static VBox navigationLayout = new VBox(0);

	public static VBox menuLayout = new VBox(0);

	public static VBox contentLayout = new VBox(0);

	@Override
	public void start(Stage stage) {
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
		mainLayout.getStyleClass().add("content-layout");

		/**
		 * Include content and navigation layout
		 * */
		mainLayout.getChildren().addAll(navigationLayout, contentLayout, menuLayout);

		/**
		 * setup
		 * */
		NavigationSetter.set();

		/**
		 * Set up main Scene with Layout
		 * */
		Scene mainScene = new Scene(mainLayout);
		this.setStyle(mainScene);

		stage.setMaximized(true);
		stage.setTitle("Pharmacy");
		stage.setScene(mainScene);
		stage.show();
	}


	/**
	 * Set Style
	 * */
	public void setStyle(Scene scene){
		String navigationLayoutCSS = getClass().getResource("/style/navigation-layout.css").toExternalForm();
		String menuLayoutCSS = getClass().getResource("/style/menu-layout.css").toExternalForm();
		String contentLayoutCSS = getClass().getResource("/style/content-layout.css").toExternalForm();

		scene.getStylesheets().addAll(navigationLayoutCSS, menuLayoutCSS, contentLayoutCSS);
	}
}