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

	// Source - https://stackoverflow.com/a/20536597
	// Posted by Suresh Atta, modified by community. See post 'Timeline' for change history
	// Retrieved 2026-08-18, License - CC BY-SA 3.0

	public static String getSaltString(int length) {
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < length) {
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	    }

}