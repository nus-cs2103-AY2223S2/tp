package seedu.dengue.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.dengue.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103-w17-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "If you want more information, you can refer to the user guide:\n"
            + USERGUIDE_URL + "\n"
            + "\nFor a brief summary, the following are the available commands for this application:\n"
            + "add, list, edit, find, delete, clear, undo, redo, sort, overview, import, export, help, exit\n"
            + "\nFormatting:\n"
            + "\nadd: add n/PATIENT_NAME p/POSTAL_CODE d/DATE a/AGE [v/DENGUE_VARIANT]...\n"
            + "\nlist: list\n"
            + "\nedit: edit INDEX [n/NAME] [p/POSTAL] [d/DATE] [a/AGE] [v/DENGUE_VARIANT]...\n"
            + "\nfind: find [n/NAME] [p/POSTAL] [d/DATE] [a/AGE] [v/DENGUE_VARIANT]...\n"
            + "or find [n/NAME] [p/POSTAL] [sd/STARTDATE] [ed/ENDDATE] [a/AGE] [v/DENGUE_VARIANT]...\n"
            + "or find [n/NAME] [p/POSTAL] [d/DATE] [sa/STARTAGE] [ea/ENDAGE] [v/DENGUE_VARIANT]...\n"
            + "\ndelete INDEX…\n"
            + "or delete [d/DATE]\n"
            + "or delete [sd/STARTDATE] [ed/ENDDATE]\n"
            + "\nclear: clear\n"
            + "\nundo: undo [INTEGER]\n"
            + "\nredo: redo [INTEGER]\n"
            + "\nsort: sort [n/] [a/] [d/]\n"
            + "\noverview: overview [p/] [a/] [v/]"
            + "\nimport: import [FILENAME]\n"
            + "\nexport: export [FILENAME]\n"
            + "\nhelp: help\n"
            + "exit: exit";
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
     *      if this method is called on a thread other than the JavaFX Application Thread.
     *      if this method is called during animation or layout processing.
     *      if this method is called on the primary stage.
     *      if {@code dialogStage} is already showing.
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
