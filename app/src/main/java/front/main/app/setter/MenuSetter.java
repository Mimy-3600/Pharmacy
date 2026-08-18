package front.main.app.setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import back.model.Medoc;
import front.components.menu.AddMedicine;
import front.components.menu.RemoveMedecine;
import front.main.app.App;
import front.uitools.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;

public class MenuSetter {

	private static Map<String, VBox> _menuDictionnary = new HashMap<>();

	public static AddMedicine _addmedicine = new AddMedicine();
	public static RemoveMedecine _removeMedecine = new RemoveMedecine(
		new Medoc("XXXXXX", "Unknown", new BigDecimal(0), 0, false)
	);

	public static void show() {
		if(!App.displayMenu) {
			Timeline displayBlock = Animation.getWidthAnimation(App.menuLayout, 200, 400);
			displayBlock.play();

			App.displayMenu = true;
	 	}
	}

	public static void hide() {
		if(App.displayMenu) {
	 		Timeline displayNone = Animation.getWidthAnimation(App.menuLayout, 200, 0);
			displayNone.play();

			App.menuLayout.getChildren().clear();
			App.displayMenu = false;
	 	}
	}

	public static void present(String formName) {
		App.menuLayout.getChildren().clear();

		if(!_menuDictionnary.containsKey(formName)) {
			throw new RuntimeException("Try to present conten that doesn't exist : `" + formName + "`");
		}

		if(formName == "RemoveMedecine" && _removeMedecine == null) {
			throw new RuntimeException("No medoc found yet");
		}

		App.menuLayout.getChildren().add(_menuDictionnary.get(formName));
	}

	public static void set() {
		_menuDictionnary.put("AddMedicine", _addmedicine);
		_menuDictionnary.put("RemoveMedicine", _removeMedecine);
	}

	public static void setMedoc(Medoc medoc) {
		System.out.println(medoc.getMedocDesignation());
		_removeMedecine = new RemoveMedecine(medoc);

		_menuDictionnary.remove("RemoveMedicine");
		_menuDictionnary.put("RemoveMedicine", _removeMedecine);
	}

}