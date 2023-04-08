package seedu.address.ui;

import guitests.guihandles.HelpWindowHandle;
import guitests.guihandles.StageHandle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxToolkit;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.storage.EduMateStorageManager;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains tests for closing of the {@code MainWindow}.
 */
public class MainWindowCloseTest extends GuiUnitTest {
    @TempDir
    public Path temporaryFolder;

    private MainWindow mainWindow;
    private EmptyMainWindowHandle mainWindowHandle;
    private Stage stage;

    @BeforeEach
    public void setUp() throws Exception {
        EduMateStorageManager jsonAddressBookStorage =
                new EduMateStorageManager(
                        temporaryFolder.resolve("edumate.json"),
                        temporaryFolder.resolve(".edumate_history"));
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(
                temporaryFolder.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(jsonAddressBookStorage, jsonUserPrefsStorage);
        FxToolkit.setupStage(stage -> {
            this.stage = stage;
            mainWindow = new MainWindow(stage, new LogicManager(new ModelManager(), storageManager));
            mainWindowHandle = new EmptyMainWindowHandle(stage);
            mainWindowHandle.focus();
        });
        FxToolkit.showStage();
    }

    @Test
    public void close_menuBarExitButton_allWindowsClosed() {
        mainWindowHandle.clickOnMenuExitButton();
        // The application will exit when all windows are closed.
        assertEquals(Collections.emptyList(), guiRobot.listWindows());
    }

    @Test
    public void close_externalRequest_exitAppRequestEventPosted() {
        mainWindowHandle.clickOnMenuHelpButton();
        assertTrue(HelpWindowHandle.isWindowPresent());
        mainWindowHandle.closeMainWindowExternally();
        // The application will exit when all windows are closed.
        assertEquals(Collections.emptyList(), guiRobot.listWindows());
    }

    /**
     * A handle for an empty {@code MainWindow}. The components in {@code MainWindow} are not initialized.
     */
    private class EmptyMainWindowHandle extends StageHandle {

        private EmptyMainWindowHandle(Stage stage) {
            super(stage);
        }

        /**
         * Closes the {@code MainWindow} by clicking on the menu bar's exit button.
         */
        private void clickOnMenuExitButton() {
            guiRobot.clickOn("#exitButton");
        }

        /**
         * Closes the {@code MainWindow} through an external request {@code MainWindow} (e.g pressing the 'X' button on
         * the {@code MainWindow} or closing the app through the taskbar).
         */
        private void closeMainWindowExternally() {
            guiRobot.interact(() -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
        }

        /**
         * Opens the {@code HelpWindow} by clicking on the menu bar's help button.
         */
        private void clickOnMenuHelpButton() {
            guiRobot.clickOn("#helpButton");
        }
    }
}
