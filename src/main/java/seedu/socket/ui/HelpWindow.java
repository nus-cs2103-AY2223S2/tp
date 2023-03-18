package seedu.socket.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.socket.MainApp;
import seedu.socket.commons.core.LogsCenter;

/**
 * Controller for a Help Window.
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-t12-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "COMMAND LIST\n"
            + "\n"
            + "1. Adding a person: add\n"
            + "Format: add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] "
            + "[g/GITHUB_PROFILE] [l/LANGUAGE] [t/TAG]…\n\n"
            + "2. Listing all persons\n"
            + "Format: list [t/TAG]\n\n"
            + "3. Editing a person : edit\n"
            + "Format: edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] "
            + "[g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]…\n\n"
            + "4. Removing a person's attribute : remove\n"
            + "Format: remove INDEX [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] \"\n"
            + "[g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]…\n\n"
            + "5. Clearing groups of person : clear\n"
            + "Format: clear [t/TAG]...\n\n"
            + "6. Locating persons by name or tag: find\n"
            + "Format: find KEYWORD [MORE_KEYWORDS]… [t/TAG]…\n\n"
            + "7. Deleting a person : delete\n"
            + "Format: delete INDEX\n\n"
            + "8. Sorting persons (by other fields) : sort\n"
            + "Format: sort [CATEGORY]\n\n"
            + "9. Viewing a person's detail : view\n"
            + "Format: view INDEX\n\n"
            + "10. Undoing a change : undo\n"
            + "Format: undo\n\n"
            + "11. Redoing a change : redo\n"
            + "Format: redo"
            + "12. Exiting the program : exit\n\n"
            + "For more info:";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessage;
    @FXML
    private Hyperlink userGuideLink;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        userGuideLink.setText(USERGUIDE_URL);
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
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * Opens the URL to the user guide in a browser.
     */
    @FXML
    private void openUserGuide() {
        MainApp.openUrl(USERGUIDE_URL);
        userGuideLink.setVisited(false);
    }
}
