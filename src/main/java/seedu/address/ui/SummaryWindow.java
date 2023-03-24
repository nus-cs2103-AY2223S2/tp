package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class SummaryWindow extends UiPart<Stage> {

    // TODO: 23/3/2023 add more information to the summary page, get info from SummaryCommand
    public static final String COUNT_MESSAGE = "Contact Counts: ";
    public static final String TAG_MESSAGE = "Existing tags: ";

    private static final Logger logger = LogsCenter.getLogger(SummaryWindow.class);
    private static final String FXML = "SummaryWindow.fxml";
    private static int SIZE;
    @FXML
    private Label countMessage;

    @FXML
    private Label tagMessage;
    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public SummaryWindow(Stage root) {
        super(FXML, root);
        countMessage.setText(COUNT_MESSAGE + String.valueOf(SummaryWindow.SIZE));
        tagMessage.setText(TAG_MESSAGE + String.valueOf(SummaryWindow.SIZE));
    }

    /**
     * Creates a new HelpWindow.
     */
    public SummaryWindow() {
        this(new Stage());
    }

    /**
     * Set size of list.
     */
    public static void setSize(int size) {
        System.out.println(size);
        SummaryWindow.SIZE = size;
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
        logger.fine("Showing summary page of the application.");
        countMessage.setText(COUNT_MESSAGE + SummaryWindow.SIZE);
        tagMessage.setText(TAG_MESSAGE + SummaryWindow.SIZE);
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

}
