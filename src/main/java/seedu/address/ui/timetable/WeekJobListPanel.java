package seedu.address.ui.timetable;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.ui.UiPart;

/**
 * Panel containing job list in the week
 */
public class WeekJobListPanel extends UiPart<Region> {

    private static final String FXML = "WeekJobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    private StackPane deliveryJobListPanelPlaceholder1;
    private StackPane deliveryJobListPanelPlaceholder2;
    private StackPane deliveryJobListPanelPlaceholder3;
    private StackPane deliveryJobListPanelPlaceholder4;
    private StackPane deliveryJobListPanelPlaceholder5;
    private StackPane deliveryJobListPanelPlaceholder6;
    private StackPane deliveryJobListPanelPlaceholder7;

    @FXML
    private HBox jobListPanel;

    /**
     * Creates a {@code WeekJobListPanel} with the given {@code Stage} and {@code Logic}.
     */
    public WeekJobListPanel(Logic logic, Stage primaryStage) {
        super(FXML);

        // Set dependencies
        this.logic = logic;
        this.primaryStage = primaryStage;

        deliveryJobListPanelPlaceholder1 = new StackPane();
        deliveryJobListPanelPlaceholder2 = new StackPane();
        deliveryJobListPanelPlaceholder3 = new StackPane();
        deliveryJobListPanelPlaceholder4 = new StackPane();
        deliveryJobListPanelPlaceholder5 = new StackPane();
        deliveryJobListPanelPlaceholder6 = new StackPane();
        deliveryJobListPanelPlaceholder7 = new StackPane();

        setAllPlaceholderBackgroundColor("DARKCYAN");
        setAllPalceholderPrefWidth((primaryStage.getWidth() - 96) / 7);
        addAllPlaceholderJobs();


        jobListPanel.getChildren().addAll(deliveryJobListPanelPlaceholder1, deliveryJobListPanelPlaceholder2,
                deliveryJobListPanelPlaceholder3, deliveryJobListPanelPlaceholder4,
                deliveryJobListPanelPlaceholder5, deliveryJobListPanelPlaceholder6,
                deliveryJobListPanelPlaceholder7);
        jobListPanel.setSpacing(12);
        jobListPanel.setAlignment(Pos.CENTER);
    }

    /**
     * Sets background color for all job list panel placeholders for all days in week
     * @param backgroundColor
     */
    private void setAllPlaceholderBackgroundColor(String backgroundColor) {
        deliveryJobListPanelPlaceholder1.setStyle("-fx-background-color: " + backgroundColor);
        deliveryJobListPanelPlaceholder2.setStyle("-fx-background-color: " + backgroundColor);
        deliveryJobListPanelPlaceholder3.setStyle("-fx-background-color: " + backgroundColor);
        deliveryJobListPanelPlaceholder4.setStyle("-fx-background-color: " + backgroundColor);
        deliveryJobListPanelPlaceholder5.setStyle("-fx-background-color: " + backgroundColor);
        deliveryJobListPanelPlaceholder6.setStyle("-fx-background-color: " + backgroundColor);
        deliveryJobListPanelPlaceholder7.setStyle("-fx-background-color: " + backgroundColor);

    }

    /**
     * Sets width size for all job list panel placeholders for all days in week
     * @param widthSize
     */
    private void setAllPalceholderPrefWidth(double widthSize) {
        deliveryJobListPanelPlaceholder1.setPrefWidth(widthSize);
        deliveryJobListPanelPlaceholder2.setPrefWidth(widthSize);
        deliveryJobListPanelPlaceholder3.setPrefWidth(widthSize);
        deliveryJobListPanelPlaceholder4.setPrefWidth(widthSize);
        deliveryJobListPanelPlaceholder5.setPrefWidth(widthSize);
        deliveryJobListPanelPlaceholder6.setPrefWidth(widthSize);
        deliveryJobListPanelPlaceholder7.setPrefWidth(widthSize);

    }

    /**
     * Sets job lists for all job list panel placeholders for all days in week
     */
    private void addAllPlaceholderJobs() {
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder1, logic.getDayofWeekJob(1));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder2, logic.getDayofWeekJob(2));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder3, logic.getDayofWeekJob(3));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder4, logic.getDayofWeekJob(4));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder5, logic.getDayofWeekJob(5));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder6, logic.getDayofWeekJob(6));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder7, logic.getDayofWeekJob(7));
    }

    /**
     * Sets job list for each job list panel placeholder
     * @param panelPlaceholder job list panel placeholder for specific day
     * @param jobListInDay job list in specific day
     */
    private void addJobSlotsToPanel(StackPane panelPlaceholder, DeliveryList jobListInDay) {
        if (jobListInDay != null) {
            DayJobListPanel jobListInDayPane = new DayJobListPanel(logic, jobListInDay);
            panelPlaceholder.getChildren().add(jobListInDayPane.getRoot());
        }
    }
}
