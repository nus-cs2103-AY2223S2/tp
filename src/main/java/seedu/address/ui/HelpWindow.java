package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL =
            "https://ay2223s2-cs2103-f10-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Click on the button to navigate to the user guide >>>";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private static final int STAGE_WIDTH = 427;
    private static final String XDG_OPEN = "xdg-open";

    @FXML
    private Button openButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        root.setMinWidth(STAGE_WIDTH); // This fixes a bug on Linux where the stage sometimes appear with 0 width
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Navigates the user to the user guide URL.
     */
    @FXML
    private void openUrl() {
        try {
            openUrlWithDesktop();
        } catch (UnsupportedOperationException e) {
            logger.warning("Desktop#browse(URL) is not supported on the current platform, falling back to xdg-open");
            openUrlWithXdgOpen();
        }
    }

    private void openUrlWithDesktop() {
        try {
            Desktop.getDesktop().browse(new URL(USERGUIDE_URL).toURI());
        } catch (IOException e) {
            logger.warning("Cannot open user guide URL");
        } catch (URISyntaxException e) {
            logger.warning("Cannot open user guide URL");
        }
    }

    /**
     * A fallback for when {@code Desktop#browse(URL)} is not supported by the current platform.<p>
     *
     * This could occur if the platform is Linux.
     */
    private void openUrlWithXdgOpen() {
        try {
            String[] command = new String[] {XDG_OPEN, USERGUIDE_URL};
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            logger.warning("Cannot open user guide URL");
        } catch (SecurityException e) {
            logger.warning("Cannot open user guide URL due to system's security manager");
        }
    }
}
