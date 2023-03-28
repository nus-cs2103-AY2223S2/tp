package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-w14-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    public static final String COMMAND_SUMMARY =
            "Prefix:\n" +
            "n/NAME \n" +
            "p/PHONE \n" +
            "a/ADDRESS \n" +
            "e/EMAIL \n" +
            "t/TELEGRAM \n" +
            "g/[GROUP…\u200B]            Accepts multiple keywords\n" +
            "m/[MODULE…]          Accepts multiple keywords\n" +
            "\n" +
            "\n" +
            "Command Usage:\n" +
            "exit              Exits the application\n" +
            "\n" +
            "help              Displays help window\n" +
            "\n" +
            "add n/NAME          Adds a contact, name is compulsory  \n" +
            "add n/NAME p/PHONE a/ADDRESS e/EMAIL t/TELEGRAM [g/GROUP]…\u200B [m/MODULE]…\n" +
            "\n" +
            "edit INDEX [z/FIELD]…        Edits the fields for the conatct at index\n" +
            "edit [z/FIELD]  ...          Edits your user profile\n" +
            "\n" +
            "view              Views your profile\n" +
            "view INDEX            Views the contact at index\n" +
            "view n/FULLNAME          Views the contact with name\n" +
            "\n" +
            "delete INDEX            Deletes contact at index\n" +
            "\n" +
            "tag CONTACT_INDEX m/MODULE_TAG    Tags the contact with the module tag\n" +
            "tag m/MODULE_TAG        Tags your user profile with module tag\n" +
            "\n" +
            "untag CONTACT_INDEX m/MODULE_TAG  Tags the contact with the module tag\n" +
            "untag m/MODULE_TAG        Tags your user profile with module tag\n" +
            "\n" +
            "sample NUMBER          Generates a sample list of contacts\n" +
            "\n" +
            "find z/[FIELD] [MORE_FIELDS]….      Accepts only one Prefix, accepts multiple               keywords\n" +
            "\n" +
            "sort              Sorts the contacts by their indices\n" +
            "sort z/a            Sorts the contacts by their z attribute in                 ascending order\n" +
            "sort z/d            Sorts the contacts by their z attribute in                 descending order\n" +
            "sort z/              Sorts the contacts by their z attribute based               on the default order\n" +
            "sort z1/ z2/            Sorts the contacts by their z1 attribute, and               breaks ties using their z2 attribute\n" +
            "sort [z/]…..            Sorts the contacts by multiple attributes\n" +
            "\n" +
            "\n" +
            "\n" +
            "save FILENAME          Saves your data into a file at FILENAME.json\n" +
            "\n" +
            "load FILENAME          Loads your data from a file at                   FILENAME.json\n" +
            "\n" +
            "meet [INDEX]...          Suggests locations to meet with contacts at               the indexes\n" +
            "eat [INDEX]…            Suggests locations to eat with contacts at               the indexes\n" +
            "study [INDEX]…          Suggests locations to study with contacts at               the indexes\n" +
            "\n" +
            "Tips:\n" +
            "Press UP key when textfield is selected    Loads previous command into textfield\n" +
            "Press DOWN key when textfield is selected  Loads next command into textfield";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label commandSummary;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        commandSummary.setText(COMMAND_SUMMARY);
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
}
