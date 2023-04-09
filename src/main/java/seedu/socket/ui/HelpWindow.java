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
    public static final String HELP_MESSAGE_HEADER = "COMMAND LIST\n\n";
    public static final String HELP_MESSAGE_GENERAL = "\n1. Viewing help\n"
            + "Format: help\n\n"
            + "2. Undo a change\n"
            + "Format: undo\n\n"
            + "3. Redo an undone change\n"
            + "Format: redo\n\n"
            + "4. Exit the program\n"
            + "Format: exit\n\n";
    public static final String HELP_MESSAGE_PERSON = "\n1. Adding a person: add\n"
            + "Format: add n/NAME [p/PHONE] [e/EMAIL] [a/ADDRESS] "
            + "[g/GITHUB_PROFILE] [l/LANGUAGE] [t/TAG]…\n\n"
            + "2. Listing all persons\n"
            + "Format: list [t/TAG]\n\n"
            + "3. Editing a person : edit\n"
            + "Format: edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] "
            + "[g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]…\n\n"
            + "4. Removing a person's field: remove\n"
            + "Format: remove INDEX [p/[PHONE]] [e/[EMAIL]] [a/[ADDRESS]] "
            + "[g/[GITHUBPROFILE]] [l/[LANGUAGE]] [t/[TAG]]…\n\n"
            + "5. Clearing groups of person: clear\n"
            + "Format: clear [t/TAG]...\n\n"
            + "6. Locating persons by keyword(s): find\n"
            + "Format: find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]\n\n"
            + "7. Deleting a person: delete\n"
            + "Format: delete INDEX\n\n"
            + "8. Sorting persons (by other fields): sort\n"
            + "Format: sort [CATEGORY]\n\n"
            + "9. Viewing a person's detail: view\n"
            + "Format: view INDEX\n\n";
    public static final String HELP_MESSAGE_PROJECT = "\n1. Adding a project: addpj\n"
            + "Format: addpj n/PROJECT_NAME h/REPO HOST r/REPO NAME d/DEADLINE [m/MEETING]\n\n"
            + "2. Editing a project: editpj\n"
            + "Format: editpj [n/NAME] [h/REPO_HOST] [r/REPO_NAME] [d/DEADLINE] [m/MEETING]\n\n"
            + "3. Deleting a project: deletepj\n"
            + "Format: deletepj INDEX\n\n"
            + "4. Removing a project's field(s): removepj\n"
            + "Format: removepj INDEX [h/[REPO_HOST]] [r/[REPO_NAME]] [d/[DEADLINE]] [m/[MEETING]]\n\n"
            + "5. Clearing all projects: clearpj\n"
            + "Format: clearpj\n\n"
            + "6. Sorting projects (by other fields): sortpj\n"
            + "Format: sortpj [CATEGORY]\n\n"
            + "7. Assign a person to a project: assign\n"
            + "Format: assign CONTACT_INDEX PROJECT_INDEX\n\n"
            + "8. Unassign a person from a project: unassign\n"
            + "Format: unassign INDEX n/name\n\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";


    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessage;
    @FXML
    private Label helpMessageHeader;
    @FXML
    private Label helpMessage1;
    @FXML
    private Label helpMessage2;

    @FXML
    private Hyperlink userGuideLink;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        root.setHeight(500);
        helpMessageHeader.setText(HELP_MESSAGE_HEADER);
        helpMessage.setText(HELP_MESSAGE_GENERAL);
        helpMessage1.setText(HELP_MESSAGE_PERSON);
        helpMessage2.setText(HELP_MESSAGE_PROJECT);
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
