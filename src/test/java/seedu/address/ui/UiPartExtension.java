package seedu.address.ui;

import java.util.concurrent.TimeoutException;

import org.testfx.api.FxToolkit;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Provides an isolated stage to test an individual {@code UiPart}.
 *
 * @@author eugenetangkj-reused
 * Reused with slight modifications from
 * https://github.com/se-edu/addressbook-level4/blob/master/src/test/java/seedu/address/ui/testutil/UiPartExtension.java
 */
public class UiPartExtension extends StageExtension {
    //CSS files related to the stage
    private static final String[] CSS_FILES = {"view/DarkTheme.css", "view/Extensions.css"};

    //Set up the related UI component for testing
    public void setUiPart(final UiPart<? extends Parent> uiPart) {
        try {
            FxToolkit.setupScene(() -> {
                Scene scene = new Scene(uiPart.getRoot());
                scene.getStylesheets().setAll(CSS_FILES);
                return scene;
            });
            FxToolkit.showStage();
        } catch (TimeoutException te) {
            throw new AssertionError("Timeout should not take place.", te);
        }
    }
}
