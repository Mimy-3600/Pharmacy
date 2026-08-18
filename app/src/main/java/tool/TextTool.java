package tool;

import java.util.Random;
import java.text.Normalizer;

public class TextTool {
	public static String normalizeText(String text) {
		return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
	}

	public static String generateRadomColor() {
		Random rand = new Random();

		int red   = rand.nextInt(256);
		int blue  = rand.nextInt(256);
		int green = rand.nextInt(256);

		String redS   = Integer.toHexString(red);
		String greenS = Integer.toHexString(green);
		String blueS  = Integer.toHexString(blue);

		if(redS.length() == 1) redS = "0" + redS;
		if(greenS.length() == 1) greenS = "0" + greenS;
		if(blueS.length() == 1) blueS = "0" + blueS;

		return "#" + redS + greenS + blueS;
	}
}