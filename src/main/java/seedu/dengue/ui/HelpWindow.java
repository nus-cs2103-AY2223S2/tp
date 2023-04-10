package seedu.dengue.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.dengue.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    private static final String USERGUIDE_URL = "https://ay2223s2-cs2103-w17-2.github.io/tp/UserGuide.html";
    private static final String INDENT = "    ";
    private static final String HELP_MESSAGE = "For more information, please refer to the user guide:\n"
            + INDENT + USERGUIDE_URL + "\n"
            + "\nThe available commands for this application are:\n"
            + INDENT + "add, edit, delete, clear, list, find, sort, undo, redo,\n"
            + INDENT + "overview, checkout, import, export, help, exit\n"
            + "\nFormatting:\n"
            + INDENT + "add n/NAME a/AGE p/POSTAL d/DATE [v/VARIANT]...\n\n"
            + INDENT + "edit INDEX [n/NAME] [a/AGE] [p/POSTAL] [d/DATE] [v/VARIANT]...\n\n"
            + INDENT + "delete INDEX...\n"
            + INDENT + "delete { d/DATE | [sd/START_DATE] [ed/END_DATE] }\n\n"
            + INDENT + "clear\n\n"
            + INDENT + "list\n\n"
            + INDENT + "find [n/NAME] [p/POSTAL] [v/VARIANT]... \\\n"
            + INDENT + "        { [a/AGE] | [sa/START_AGE] [ea/END_AGE] } \\\n"
            + INDENT + "        { [d/DATE] | [sd/START_DATE] [ed/END_DATE] }\n\n"
            + INDENT + "sort { n/ | a/ | p/ | d/ }\n\n"
            + INDENT + "undo [INTEGER]\n\n"
            + INDENT + "redo [INTEGER]\n\n"
            + INDENT + "overview { p/ | a/ | v/ }\n\n"
            + INDENT + "checkout [FILENAME]\n\n"
            + INDENT + "import [FILENAME]\n\n"
            + INDENT + "export [FILENAME]\n\n"
            + INDENT + "help\n\n"
            + INDENT + "exit";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private TextArea helpMessage;

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
