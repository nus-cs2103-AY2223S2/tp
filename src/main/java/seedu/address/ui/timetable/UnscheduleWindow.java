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
 * Controller for unschedule window.
 */
public class UnscheduleWindow extends UiPart<Stage> {

    private static final String FXML = "UnscheduleWindow.fxml";
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
     * Creates a {@code AddressBookWindow} with the given {@code Stage} and {@code Logic}.
     */
    public UnscheduleWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;
    }


    /**
     * Show main window.
     */
    public void show() {
        logger.fine("Showing unscheduled window page");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the unscheduled window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the unscheduled window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the unscheduled window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills Inner Parts and content of unscheduled window.
     */
    public void fillInnerParts() {
        jobListPanel = new UnscheduledDeliveryJobListPanel(logic.getUnscheduledDeliveryJobList());
        int jobListLen = logic.getUnscheduledDeliveryJobList().size();
        numberOfJobs.setText(String.format("Total: %d job(s)", jobListLen));
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        logic.getFilteredDeliveryJobList().addListener(new ListChangeListener<DeliveryJob>() {
            @Override
            public void onChanged(Change<? extends DeliveryJob> c) {
                refreshUnscheduledJobList();
            }
        });
    }

    private void refreshUnscheduledJobList() {
        logger.info("[Unscheduled Window] Refresh list of unscheduled jobs");
        jobListPanelPlaceholder.getChildren().clear();
        jobListPanel = new UnscheduledDeliveryJobListPanel(logic.getUnscheduledDeliveryJobList());
        int jobListLen = logic.getUnscheduledDeliveryJobList().size();
        numberOfJobs.setText(String.format("Total: %d job(s)", jobListLen));
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());
    }



    /**
     * Exits unscheduled window
     */
    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

}
