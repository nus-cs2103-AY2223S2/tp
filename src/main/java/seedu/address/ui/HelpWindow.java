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

    public static final String USERGUIDE_URL = "https://docs.google.com/document/d/1nTsxXRuQh8OzcsR_LoAR6xX_pkw0IuO5HKIqznkDMzE/edit?usp=sharing";
    public static final String HELP_MESSAGE_COMMAND = "Features:\n" +
            "help\n" +
            "   - Accesses this help guide\n" +
            "cat [c/categoryName]\n" +
            "   - Adds a new category to the expense tracker\n" +
            "delcat [categoryName]\n" +
            "   - Deletes a category from the expense tracker\n" +
            "add [c/categoryName] [n/itemName] [p/price] [d/date]\n" +
            "   - Adds an expense to the user's expense tracker\n" +
            "lcat (UNAVAILABLE AS OF v1.2)\n" +
            "   - Shows all categories in the expense tracker\n" +
            "del [index]\n" +
            "   - Deletes expense at index [index]\n" +
            "find [itemname] (UNAVAILABLE AS OF v1.2)\n" +
            "   - lists all items with matching names\n" +
            "list -[command option]  (UNAVAILABLE AS OF v1.2)\n" +
            "   - Toggles the view of summary of expenses\n" +
            "       list -t  - All expenses of the user\n" +
            "       list -c  - All expenses of the user in a specific category\n" +
            "       list -w  - All expenses of the user in the past 7 days\n\n\n";

    public static final String HELP_MESSAGE = HELP_MESSAGE_COMMAND + "For more info, refer to the user guide: " + USERGUIDE_URL;

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
