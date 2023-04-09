package seedu.techtrack.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.techtrack.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103-w16-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Here are the list of commands available: "
            + "\n1. add n/{name} c/{CONTACT} e/{EMAIL} coy/{COMPANY} jd/{JOB DESCRIPTION} [t/{TAG}]... w/{WEBSITE} "
            + "w/{WEBSITE} $/{SALARY} d/{DEADLINE} x/{EXPERIENCE}"
            + "\n2. edit {index} [n/{name}] [c/{CONTACT}] [e/{EMAIL}] [coy/{COMPANY}] [jd/{JOB DESCRIPTION}] "
            + "[t/{TAG}]... [w/{WEBSITE}] [$/{SALARY}] [d/{DEADLINE}] [x/{EXPERIENCE}]"
            + "\n3. delete {index}"
            + "\n4. view {index}"
            + "\n5. name {keyword}"
            + "\n6. salary asc/desc"
            + "\n7. deadline asc/desc"
            + "\n8. company {company}"
            + "\n9. tag {tag}"
            + "\n10. list"
            + "\n11. clear"
            + "\n12. help"
            + "\n13. exit"
            + "\n**Fields in '[field]' are optional (Edit command require at least one field)"
            + "\nOr, you can also refer to our user guide: " + USERGUIDE_URL;

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
