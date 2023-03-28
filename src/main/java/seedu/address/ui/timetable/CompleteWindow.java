package seedu.address.ui.timetable;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;
import seedu.address.ui.jobs.DeliveryJobListPanel;
import seedu.address.ui.main.ResultDisplay;
import seedu.address.ui.main.StatusBarFooter;

/**
 * Displays contact list.
 */
public class CompleteWindow extends UiPart<Stage> {

    private static final String FXML = "CompletedJobWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    private ResultDisplay resultDisplay;
    private DeliveryJobListPanel jobListPanel;

    @FXML
    private Text numberOfJobs;
    @FXML
    private StackPane jobListPanelPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code AddressBookWindow} with the given {@code Stage} and {@code Logic}.
     */
    public CompleteWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;
    }


    /**
     * Show main window.
     */
    public void show() {
        logger.fine("Showing completed job window");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * fillInnerParts.
     */
    public void fillInnerParts() {
        jobListPanel = new DeliveryJobListPanel(logic.getCompletedDeliveryJobList());
        int jobListLen = logic.getCompletedDeliveryJobList().size();
        numberOfJobs.setText(String.format("Total: %d job(s)", jobListLen));

        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

}
