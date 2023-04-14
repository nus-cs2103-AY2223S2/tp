package seedu.sudohr.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.sudohr.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String COMMAND_LIST = "These are the commands used in SudoHR:\n\n"
            + "Employee-related commands:\n"
            + "    add    -   Add employee\n"
            + "    list   -   List employees\n"
            + "    edit   -   Edit employee\n"
            + "    find   -   Find employee by name\n"
            + "    feid   -   Find employee by ID\n"
            + "    del    -   Delete employee\n"
            + "\n"
            + "Department-related commands:\n"
            + "    adep   -   Add department\n"
            + "    ldep   -   List departments\n"
            + "    edep   -   Edit department\n"
            + "    fdep   -   Find department by name\n"
            + "    ddep   -   Delete department\n"
            + "    aetd   -   Add employee to department\n"
            + "    refd   -   Remove employee from department\n"
            + "    led    -   List employee's departments\n"
            + "    leid   -   List employees in department\n"
            + "    ldhc   -   List department head count\n"
            + "\n"
            + "Leave-related commands:\n"
            + "    aetl   -   Add employee to leave\n"
            + "    aelr   -   Add employee leave (range)\n"
            + "    defl   -   Delete employee from leave\n"
            + "    leol   -   List employees on leave\n"
            + "    llbe   -   List leaves taken by employee\n"
            + "    llve   -   List leaves\n\n";
    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103t-t17-2.github.io/tp/UserGuide";
    public static final String HELP_MESSAGE = COMMAND_LIST + "For more information, refer to the user guide: "
            + USERGUIDE_URL;

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
