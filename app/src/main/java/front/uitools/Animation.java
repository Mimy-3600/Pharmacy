package front.uitools;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Interpolator;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class Animation {
	public static Timeline getWidthAnimation(Region target, Integer millisecond, Integer targetWidth) {
		Timeline timeline = new Timeline(
			new KeyFrame(
				Duration.millis(millisecond),
				new KeyValue(target.prefWidthProperty(), targetWidth, Interpolator.EASE_BOTH)
			)
		);

		return timeline;
	}
}