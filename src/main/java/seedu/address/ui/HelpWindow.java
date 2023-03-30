package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103-w17-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide:\n" + USERGUIDE_URL;

    public static final String USER_GUIDE = "Greetings! Here's a quick and easy guide for you to use MATHUTORING. "
            + "Below are the functions supported.\n"
            + "Commands with parameter(s):\n"
            + "1. add: Adds a student to the student list. \n"
            + "2. addScore: Adds a score for a selected student.\n"
            + "3. deleteScore: Deletes a score for a selected student.\n"
            + "4. addTask: Adds a task for a selected student.\n"
            + "5. deleteTask: Deletes a task for a selected student.\n"
            + "6. check: Checks the task(s) and score(s) for a selected student.\n"
            + "7. delete: Deletes a student from the student list.\n"
            + "8. edit: Edits the student’s information.\n"
            + "9. find: Finds student(s) from the student list by their name(s).\n"
            + "10. filter: Filters out the matching result based on tag(s).\n"
            + "11. markComplete: Marks task as complete.\n"
            + "12. markInProgress: Marks task as in progress.\n"
            + "13. markLate: Marks task as late.\n"
            + "14. exportP: Exports a student's progress as PDF file.\n"
            + "15. import: Imports .json file in.\n"
            + "16. export: Exports .json file out.\n"
            + "\n"
            + "Commands without parameter(s):\n"
            + "Please note that the commands below are used without parameters. "
            + "These commands will ignore all things that follow behind the command keyword.\n"
            + "1. list: Lists all students. \n"
            + "2. switch: Switches between the score list tab and score chart tab.\n"
            + "3. help: Opens the help window. \n"
            + "4. clear: Clears all items on the student list.\n"
            + "5. exit: Closes the application. \n"
            + "\n"
            + "Addition: The student’s avatar will change if they have a male or female tag. "
            + "If a student has no or both the tags, his or her avatar will remain as the default.\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;
    @FXML
    private Label userGuide;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        userGuide.setText(USER_GUIDE);
        root.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                copyButton.setText("Copy Link");
            }
        });
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
        copyButton.setText("Link Copied!");
    }
}
