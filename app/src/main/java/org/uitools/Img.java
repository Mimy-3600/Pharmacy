package org.uitools;

import java.io.InputStream;
import javafx.scene.image.Image;

public class Img {
	public static Image getImg(String name) {
		InputStream stream = Img.class.getResourceAsStream("/image/" + name);

		if(stream != null) {
			Image img = new Image(stream);

			return img;
		} else {
			throw new NullPointerException("Make sure " + name + " exists in /main/resources/image");
		}
	}
}