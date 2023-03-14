package seedu.address.commons.util;


import javafx.scene.image.Image;

/**
 * This class is here to replace the {@code AppUtil} class in the original
 * AB3 codebase. It will not use static methods, and will instead use
 * a singleton pattern.
 */
public class AppHelper {
    /**
     * The singleton instance of this class.
     */
    public static final AppHelper INSTANCE;

    static {
        INSTANCE = new AppHelper();
    }

    private AppHelper() {
    }

    /**
     * @see AppUtil#getImage(String)
     */
    public Image getImage(String imagePath) {
        return AppUtil.getImage(imagePath);
    }

    /**
     * @see AppUtil#checkArgument(Boolean)
     */
    public void checkArgument(Boolean condition) {
        AppUtil.checkArgument(condition);
    }

    /**
     * @see AppUtil#checkArgument(Boolean)
     */
    public void checkArgument(Boolean condition, String errorMessage) {
        AppUtil.checkArgument(condition, errorMessage);
    }
}
