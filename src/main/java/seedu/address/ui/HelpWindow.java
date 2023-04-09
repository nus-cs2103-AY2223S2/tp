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

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-t11-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide for more in-depth information: "
                                                  + USERGUIDE_URL;

    public static final String UNDO_COMMAND_MESSAGE = "1) Reverts the patient records to the state"
                                                          + " before the previous command was executed:\n"
                                                          + "Format: undo";

    public static final String REDO_COMMAND_MESSAGE = "2) Reverts the patient records to the state"
                                                          + " before the previous undo was executed:\n"
                                                          + "Format: redo";

    public static final String ADD_COMMAND_MESSAGE = "3) Add a person to the patient records:\n"
                                                         + "Format: add n/NAME i/NRIC"
                                                         + " dob/DATE OF BIRTH"
                                                         + " p/PHONE_NUMBER a/ADDRESS"
                                                         + " d/DRUG ALLERGIES g/GENDER"
                                                         + " ad/DOCTOR"
                                                         + " [e/EMAIL] [t/TAG] [m/MEDICINE]";

    public static final String EDIT_COMMAND_MESSAGE = "4) Edit an existing person in patient records"
                                                          + " based on the person's INDEX number:\n"
                                                          + "Format: edit INDEX [i/NRIC] [n/NAME] [dob/DATE OF BIRTH]"
                                                          + " [p/PHONE]"
                                                          + " [a/ADDRESS] [d/DRUG ALLERGIES]"
                                                          + " [g/GENDER] [ad/DOCTOR]"
                                                          + " [e/EMAIL] [t/TAG] [m/MEDICINE]";

    public static final String DELETE_COMMAND_MESSAGE = "5) Deletes patient with the specified NRIC from"
                                                            + " the address book:\n"
                                                            + "Format: delete i/NRIC";

    public static final String LIST_COMMAND_MESSAGE = "6) Lists all patients in the records system:\n"
                                                          + "Format: list";

    public static final String VIEW_COMMAND_MESSAGE = "7) View a patient's record:\n"
                                                          + "Format: view i/NRIC";

    public static final String FIND_COMMAND_MESSAGE = "8) Find patients according to a particular attribute:\n"
                                                          + "Format: find (i/NRIC | n/NAME | t/TAG |"
                                                          + " ad/doctor | m/MEDICINE)";

    public static final String BACKUP_COMMAND_MESSAGE = "9) Backs up the patient records to specified"
                                                            + " slot represented by INDEX_NO with an optional"
                                                            + " description:\n"
                                                            + " Format: backup INDEX_NO [b/DESCRIPTION]";

    public static final String LOAD_DATA_COMMAND_MESSAGE = "10) Loads the data from a specified slot represented"
                                                               + " by an index.\n"
                                                               + " Format: load INDEX_NO";

    public static final String VIEW_BACKUP_COMMAND_MESSAGE = "11) Shows all the backups available:\n"
                                                                 + "Format: viewbackups";

    public static final String DELETE_BACKUP_COMMAND_MESSAGE = "12) Deletes the data from a specified slot"
                                                                   + " represented by an index.\n"
                                                                   + "Format: deletebackup INDEX_NO";

    public static final String CLEAR_COMMAND_MESSAGE = "13) Purge all data in records system:\n"
                                                           + "Format: clear";

    public static final String LIGHT_MODE_COMMAND_MESSAGE = "14) Switch to light mode:\n"
                                                                + "Format: light";

    public static final String DARK_MODE_COMMAND_MESSAGE = "15) Switch to dark mode:\n"
                                                               + "Format: dark";

    public static final String NOTE_MESSAGE = "Note that square brackets signify optional arguments.\n"
                                                  + "Parentheses indicate that at least one of the arguments"
                                                  + " must be present.";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label undoMessage;

    @FXML
    private Label redoMessage;

    @FXML
    private Label addMessage;

    @FXML
    private Label viewMessage;

    @FXML
    private Label editMessage;

    @FXML
    private Label deleteMessage;

    @FXML
    private Label findMessage;

    @FXML
    private Label listMessage;

    @FXML
    private Label noteMessage;

    @FXML
    private Label backupMessage;

    @FXML
    private Label loadBackupMessage;

    @FXML
    private Label viewBackupMessage;

    @FXML
    private Label deleteBackupMessage;

    @FXML
    private Label lightModeMessage;

    @FXML
    private Label darkModeMessage;

    @FXML
    private Label clearMessage;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        undoMessage.setText(UNDO_COMMAND_MESSAGE);
        redoMessage.setText(REDO_COMMAND_MESSAGE);
        addMessage.setText(ADD_COMMAND_MESSAGE);
        editMessage.setText(EDIT_COMMAND_MESSAGE);
        deleteMessage.setText(DELETE_COMMAND_MESSAGE);
        listMessage.setText(LIST_COMMAND_MESSAGE);
        viewMessage.setText(VIEW_COMMAND_MESSAGE);
        findMessage.setText(FIND_COMMAND_MESSAGE);
        loadBackupMessage.setText(LOAD_DATA_COMMAND_MESSAGE);
        viewBackupMessage.setText(VIEW_BACKUP_COMMAND_MESSAGE);
        deleteBackupMessage.setText(DELETE_BACKUP_COMMAND_MESSAGE);
        backupMessage.setText(BACKUP_COMMAND_MESSAGE);
        clearMessage.setText(CLEAR_COMMAND_MESSAGE);
        lightModeMessage.setText(LIGHT_MODE_COMMAND_MESSAGE);
        darkModeMessage.setText(DARK_MODE_COMMAND_MESSAGE);
        noteMessage.setText(NOTE_MESSAGE);

    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException if this method is called on a thread other than the JavaFX Application Thread.
     *                               <p>
     *                               if this method is called during animation or layout processing.
     *                               <p>
     *                               if this method is called on the primary stage.
     *                               <p>
     *                               if {@code dialogStage} is already showing.
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
