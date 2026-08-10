package front.uitools;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.shape.SVGPath;

public class SVGStock {

	private static boolean isSetup = false;

	private static Map<String, String> table = new HashMap<>();

	public static SVGPath get(String svgName) {

		if(!isSetup) {
			setup();
		}
		if(!table.containsKey(svgName)) {
			throw new IllegalArgumentException(svgName + " doesn't exist");
		}

		SVGPath path = new SVGPath();
		path.setContent(table.get(svgName));
		path.getStyleClass().add(table.get(svgName + "Type"));
		double scale = 18.0 / path.getBoundsInLocal().getWidth();
		path.setScaleX(scale);
		path.setScaleY(scale);

		return path;
	}



	private static void setup() {
		if(table.isEmpty()) {
			table.put("Medicine",
				"M467.766,44.211c-29.494-29.494-68.22-44.24-106.884-44.181c-38.666-0.06-77.392,14.688-106.886,44.182l-82.428,82.426l213.71,213.71l82.428-82.426C526.755,198.875,526.755,103.199,467.766,44.211z M409.917,57.219c-2.638,13.788-16.006,22.842-29.853,20.142c-7.854-1.497-15.945-2.277-24.039-2.338c-8.033,0-16.127,0.779-24.039,2.338c-8.572,1.619-17.024-1.197-22.84-7.014c-3.474-3.476-5.934-7.972-6.955-13.187c-2.635-13.787,6.355-27.096,20.203-29.795c11.031-2.158,22.422-3.236,33.692-3.236c11.269,0,22.6,1.138,33.689,3.237C403.562,30.003,412.616,43.372,409.917,57.219z M44.242,253.966C14.688,283.52,0,322.185,0,360.911c0,38.606,14.746,77.332,44.24,106.826c58.988,58.988,154.666,58.986,213.712-0.06l82.367-82.367l-213.71-213.711L44.242,253.966z"
			);
			table.put("MedicineType", "button-nav-icon");

			isSetup = true;
		}
	}
}