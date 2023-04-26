package taa.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.Closeable;
import java.io.IOException;

import javafx.scene.image.Image;
import taa.MainApp;

/**
 * A container for App specific utility functions
 */
public class AppUtil {

    /**
     * Gets an {@code Image} from the specified path.
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException with {@code errorMessage} if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /** Closes the closable if it is non-null to save resource.*/
    public static void closeIfClosable(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {
                // Can't help if closing failed.
            }
        }
    }
}
