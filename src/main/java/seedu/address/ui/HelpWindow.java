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
    public static final String GUIDE = "Refer to the user guide: ";
    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-w10-1.github.io/tp/UserGuide.html";
    public static final String FEATURE_HEADER = "\n\nFeatures:\n";
    public static final String PARAMETERS_COMPULSORY_ADD_MODULE = "n/MODULE_NAME t/DESCRIPTION";
    public static final String PARAMETERS_OPTIONAL_ADD_MODULE = "[e/TIMESLOT] [a/VENUE] [c/RESOURCE] "
            + "[s/TEACHER] [d/DEADLINES] [r/REMARKS]\n";
    public static final String PARAMETERS_EDIT_MODULE = "[n/MODULE_NAME] [t/DESCRIPTION] [e/TIMESLOT] [a/VENUE] "
            + "[c/RESOURCE] [s/TEACHER] [d/DEADLINES] [r/REMARKS]\n";
    public static final String ADD = "1. Adding a module.\n"
            + "Usage: " + PARAMETERS_COMPULSORY_ADD_MODULE + PARAMETERS_OPTIONAL_ADD_MODULE
            + "Note: Compulsory fields (n/...  t/...) Optional fields (e/... a/... c/... s... d/... r/...)\n"
            + "Example: add n/CS2101 t/Tutorial e/Tuesday 10:00 12:00 a/COM1\n"
            + "Example: add n/CS2030S t/Tutorial d/250520 15:00\n"
            + "Example: add n/CS2103T t/Lecture";
    public static final String DELETE = "\n\n2. Deleting a module.\n"
            + "Usage: delete INDEX\n"
            + "Example: delete 1\n"
            + "Example: delete 2";
    public static final String EDIT = "\n\n3. Editing a module.\n"
            + "Usage: edit INDEX" + PARAMETERS_EDIT_MODULE
            + "Example: edit 2 t/Lecture r/Hybrid lectures\n"
            + "Note: At least one field is required.";
    public static final String FIND = "\n\n4. Finding a module based on its description.\n"
            + "Usage: find SEARCH_DESCRIPTION\n"
            + "Example: find Tutorial\n"
            + "Example: find CS2103T";
    public static final String SORT = "\n\n5. Sorting the modules.\n"
            + "Usage: sort TIMESLOT/DEADLINE\n"
            + "Example: sort timeslot\n"
            + "Example: sort deadline\n"
            + "Note: Currently, only sorting by either timeslot or deadline is supported. Please stay tuned for more "
            + "features. :)";
    public static final String CLEAR = "\n\n6. Clear all entries.\n" + "Example: clear";
    public static final String LIST = "\n\n7. List all entries.\n" + "Example: list";
    public static final String EXIT = "\n\n8. Exit the application.\n" + "Example: exit";
    public static final String HELP = "\n\n9. Getting help.\n" + "Example: help";
    public static final String HELP_MESSAGE = ADD + DELETE + EDIT + FIND + SORT
            + CLEAR + LIST + EXIT + HELP;
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    @FXML
    private Button copyButton;
    @FXML
    private Label guide;
    @FXML
    private Label urlLink;
    @FXML
    private Label featuresHeader;
    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        guide.setText(GUIDE);
        urlLink.setText(USERGUIDE_URL);
        featuresHeader.setText(FEATURE_HEADER);
        helpMessage.setText(HELP_MESSAGE);
        root.setResizable(true);


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
