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
import seedu.address.ui.jobs.DayJobListPanel;

/**
 * Controller for a timetable page
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
     * Creates a {@code TimeTableWindow} with the given {@code Stage} and {@code Logic}.
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

        deliveryJobListPanelPlaceholder1.setStyle("-fx-background-color: DARKCYAN");
        deliveryJobListPanelPlaceholder2.setStyle("-fx-background-color: DARKCYAN");
        deliveryJobListPanelPlaceholder3.setStyle("-fx-background-color: DARKCYAN");
        deliveryJobListPanelPlaceholder4.setStyle("-fx-background-color: DARKCYAN");
        deliveryJobListPanelPlaceholder5.setStyle("-fx-background-color: DARKCYAN");
        deliveryJobListPanelPlaceholder6.setStyle("-fx-background-color: DARKCYAN");
        deliveryJobListPanelPlaceholder7.setStyle("-fx-background-color: DARKCYAN");

        deliveryJobListPanelPlaceholder1.setPrefWidth(102.0);
        deliveryJobListPanelPlaceholder2.setPrefWidth(102.0);
        deliveryJobListPanelPlaceholder3.setPrefWidth(102.0);
        deliveryJobListPanelPlaceholder4.setPrefWidth(102.0);
        deliveryJobListPanelPlaceholder5.setPrefWidth(102.0);
        deliveryJobListPanelPlaceholder6.setPrefWidth(102.0);
        deliveryJobListPanelPlaceholder7.setPrefWidth(102.0);

        addJobSlotsToPanel(deliveryJobListPanelPlaceholder1, logic.getDayofWeekJob(1));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder2, logic.getDayofWeekJob(2));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder3, logic.getDayofWeekJob(3));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder4, logic.getDayofWeekJob(4));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder5, logic.getDayofWeekJob(5));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder6, logic.getDayofWeekJob(6));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder7, logic.getDayofWeekJob(7));


        jobListPanel.getChildren().addAll(deliveryJobListPanelPlaceholder1, deliveryJobListPanelPlaceholder2,
                deliveryJobListPanelPlaceholder3, deliveryJobListPanelPlaceholder4,
                deliveryJobListPanelPlaceholder5, deliveryJobListPanelPlaceholder6,
                deliveryJobListPanelPlaceholder7);
        jobListPanel.setSpacing((primaryStage.getWidth() - 714) / 7);
        jobListPanel.setAlignment(Pos.CENTER);


    }

    private void addJobSlotsToPanel(StackPane panelPlaceholder, DeliveryList jobListInDay) {
        if (jobListInDay != null) {
            DayJobListPanel jobListInDayPane = new DayJobListPanel(jobListInDay);
            panelPlaceholder.getChildren().add(jobListInDayPane.getRoot());
        }
    }
}
