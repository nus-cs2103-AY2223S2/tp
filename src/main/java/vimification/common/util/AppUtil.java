package vimification.common.util;

import static java.util.Objects.requireNonNull;

import javafx.scene.image.Image;
import vimification.MainApp;

/**
 * A container for App specific utility functions.
 */
public class AppUtil {

    /**
     * Gets an {@code Image} from the specified path.
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }
}
