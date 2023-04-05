package seedu.address.ui.timetable;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;
import seedu.address.ui.main.ResultDisplay;
import seedu.address.ui.main.StatusBarFooter;

/**
 * Displays list of completed jobs.
 */
public class CompleteWindow extends UiPart<Stage> {

    private static final String FXML = "CompletedJobWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    private ResultDisplay resultDisplay;
    private UnscheduledDeliveryJobListPanel jobListPanel;

    @FXML
    private Text numberOfJobs;
    @FXML
    private StackPane jobListPanelPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code CompleteWindow} with the given {@code Stage} and {@code Logic}.
     */
    public CompleteWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;
    }


    /**
     * Show complete window.
     */
    public void show() {
        logger.fine("Showing window of completed jobs");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the complete window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Complete window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Complete window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills inner parts and content of complete window.
     */
    public void fillInnerParts() {
        jobListPanel = new UnscheduledDeliveryJobListPanel(logic.getCompletedDeliveryJobList());
        int jobListLen = logic.getCompletedDeliveryJobList().size();
        numberOfJobs.setText(String.format("Total: %d job(s)", jobListLen));
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        logic.getFilteredDeliveryJobList().addListener(new ListChangeListener<DeliveryJob>() {
            @Override
            public void onChanged(Change<? extends DeliveryJob> c) {
                refreshCompletedJobList();
            }
        });
    }

    /**
     * Refreshes completed job list with updated job list
     */
    private void refreshCompletedJobList() {
        logger.info("[Complete Window] Refresh list of completed jobs");
        jobListPanelPlaceholder.getChildren().clear();
        jobListPanel = new UnscheduledDeliveryJobListPanel(logic.getCompletedDeliveryJobList());
        int jobListLen = logic.getCompletedDeliveryJobList().size();
        numberOfJobs.setText(String.format("Total: %d job(s)", jobListLen));
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

    }

    /**
     * Closes complete window.
     */
    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

}
