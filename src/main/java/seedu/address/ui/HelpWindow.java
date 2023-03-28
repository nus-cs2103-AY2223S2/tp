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

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-w10-1.github.io/tp/UserGuide.html";
    public static final String FEATURE_AVAILABLE = "\n\nFeatures:\n";
    public static final String PARAMETERS_COMPULSORY = "/nMODULE_NAME t/TAG (Lecture, Tutorial, Lab) ";
    public static final String PARAMETERS_OPTIONAL = "[e/TIMESLOT] [a/VENUE] [c/RESOURCE] "
            + "[s/TEACHER] [d/DEADLINES] [r/REMARKS]\n";

    public static final String ADD = "1. Adding a module.\n" + "Parameters: " + PARAMETERS_COMPULSORY
            + PARAMETERS_OPTIONAL + "Example: add n/CS2103T t/Lecture a/COM3";
    public static final String DELETE = "\n\n2. Deleting a module.\n" + "Parameters: INDEX (positive integer)"
            + "\nExample: delete 1";
    public static final String EDIT = "\n\n3. Editing a module.\n" + "Parameters: INDEX (positive integer)"
            + "[/nMODULE_NAME] [t/TAG]" + PARAMETERS_OPTIONAL + "Example: edit 2 t/Lecture r/Hybrid lectures";
    public static final String FIND = "\n\n4. Finding a module.\n" + "Parameters: KEYWORDS [MORE KEYWORDS]"
            + "\nExample: find Tutorial\nExample: find CS2103T";
    public static final String CLEAR = "\n\n5. Clear all entries.\n" + "No Parameters\nExample: clear";
    public static final String LIST = "\n\n6. List all entries.\n" + "No Parameters\nExample: list";
    public static final String EXIT = "\n\n7. Exit the application.\n" + "No Parameters\nExample: exit";
    public static final String HELP = "\n\n8. Getting help.\n" + "No Parameters\nExample: help";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL + FEATURE_AVAILABLE
            + ADD + DELETE + EDIT + FIND + CLEAR + LIST + EXIT + HELP;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

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
