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
    public static final String HELP_MESSAGE = "Refer to the user guide for more in-depth information: " + USERGUIDE_URL;
    public static final String ADD_COMMAND_MESSAGE = "1) Add a person to the patient records: \n"
                                                     + "Format: add n/NAME i/NRIC p/PHONE_NUMBER e/EMAIL"
                                                     + " a/ADDRESS [d/DIAGNOSIS] [t/TAG]";
    public static final String GET_COMMAND_MESSAGE = "2) Get a patient's record: \nFormat: get i/NRIC";
    public static final String EDIT_COMMAND_MESSAGE = "3) Edit an existing person in patient records: \n"
                                                        + "Format: edit i/NRIC [n/NAME] [p/PHONE]"
                                                        + " [e/EMAIL] [a/ADDRESS] [d/DIAGNOSIS] [t/TAG]";
    public static final String DELETE_COMMAND_MESSAGE = "4) Deletes patient with the specified NRIC from"
                                                        + " the address book: \nFormat: delete i/NRIC";
    public static final String FILTER_COMMAND_MESSAGE = "5) Filters patients according to a particular attribute. "
                                                        + "Eg. Medicine usage or health conditions: \n"
                                                        + "Format: filter KEYWORD [MORE_KEYWORDS]";
    public static final String LIST_COMMAND_MESSAGE = "6) Lists all patients in the records system: \nFormat: list";
    public static final String SAVE_COMMAND_MESSAGE = "7) Save current data to specified save file: \n"
                                                      + "Format: save FILE_NO";
    public static final String CLEAR_COMMAND_MESSAGE = "8) Purge all data in records system: \nFormat: clear";
    public static final String NOTE_MESSAGE = "Note that square brackets signify optional arguments";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label addMessage;

    @FXML
    private Label getMessage;

    @FXML
    private Label editMessage;

    @FXML
    private Label deleteMessage;

    @FXML
    private Label filterMessage;

    @FXML
    private Label listMessage;

    @FXML
    private Label noteMessage;

    @FXML
    private Label saveMessage;

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
        addMessage.setText(ADD_COMMAND_MESSAGE);
        getMessage.setText(GET_COMMAND_MESSAGE);
        editMessage.setText(EDIT_COMMAND_MESSAGE);
        deleteMessage.setText(DELETE_COMMAND_MESSAGE);
        filterMessage.setText(FILTER_COMMAND_MESSAGE);
        listMessage.setText(LIST_COMMAND_MESSAGE);
        saveMessage.setText(SAVE_COMMAND_MESSAGE);
        clearMessage.setText(CLEAR_COMMAND_MESSAGE);
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
