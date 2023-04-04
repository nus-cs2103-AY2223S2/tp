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
    // TODO: 24/3/2023 write test cases for summary feature
    public static final String COUNT_MESSAGE = "Contact Counts: ";
    public static final String TAG_MESSAGE = "Existing tags: ";
    public static final String POTENTIAL_EARNINGS_MESSAGE = "Total Potential Earnings: ";
    public static final String COMPANIES_MESSAGE = "Existing companies: ";

    private static final Logger logger = LogsCenter.getLogger(SummaryWindow.class);
    private static final String FXML = "SummaryWindow.fxml";
    private static int SIZE;
    private static String POTENTIAL_EARNINGS;
    private static String COMPANIES;
    private static String TAGS;
    @FXML
    private Label countMessage;

    @FXML
    private Label tagMessage;

    @FXML
    private Label potentialEarnings;
    @FXML
    private Label companies;
    @FXML
    private Label tags;
    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public SummaryWindow(Stage root) {
        super(FXML, root);
        countMessage.setText(COUNT_MESSAGE + SummaryWindow.SIZE);
        tagMessage.setText(TAG_MESSAGE + SummaryWindow.SIZE);
        potentialEarnings.setText(POTENTIAL_EARNINGS_MESSAGE + SummaryWindow.POTENTIAL_EARNINGS);
        companies.setText(COMPANIES_MESSAGE + SummaryWindow.COMPANIES);
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
        SummaryWindow.SIZE = size;
    }
    /**
     * Sets the value of the potential earnings.
     */
    public static void setPotentialEarnings(String potentialEarnings) {
        SummaryWindow.POTENTIAL_EARNINGS = potentialEarnings;
    }
    /**
     * Sets the value of tags.
     */
    public static void setTags(String tags) {
        SummaryWindow.TAGS = tags;
    }

    /**
     * Sets the value of companies.
     */
    public static void setCompanies(String companies) {
        SummaryWindow.COMPANIES = companies;
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
        tagMessage.setText(TAG_MESSAGE + SummaryWindow.TAGS);
        potentialEarnings.setText(POTENTIAL_EARNINGS_MESSAGE + SummaryWindow.POTENTIAL_EARNINGS);
        companies.setText(COMPANIES_MESSAGE + SummaryWindow.COMPANIES);
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
