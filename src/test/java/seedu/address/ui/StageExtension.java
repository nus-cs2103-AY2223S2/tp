package seedu.address.ui;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testfx.api.FxToolkit;

/**
 * Properly sets up and tears down a JavaFx stage for our testing purposes.
 *
 * @@author eugenetangkj-reused
 * Reused with slight modifications from
 * https://github.com/se-edu/addressbook-level4/blob/master/src/test/java/seedu/address/ui/testutil/StageExtension.java
 */
public class StageExtension implements BeforeEachCallback, AfterEachCallback {

    //Sets up the JavaFx stage for testing
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    //Closes and cleans up the JavaFx stage after testing
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        FxToolkit.cleanupStages();
    }
}
