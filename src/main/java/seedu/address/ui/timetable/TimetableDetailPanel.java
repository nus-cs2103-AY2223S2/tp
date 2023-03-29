package seedu.address.ui.timetable;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;

/**
 * Controller for a timetable page
 */
public class TimetableDetailPanel extends UiPart<Region> /*implements Initializable*/ {

    private static final String FXML = "TimetablePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;
    private LocalDate focusDate;

    @FXML
    private HBox monthYearPanel;

    @FXML
    private HBox dayOfWeekPanel;

    @FXML
    private HBox dayOfMonthPanel;

    @FXML
    private HBox jobListPanel;

    /**
     * Creates a {@code TimeTableWindow} with the given {@code Stage} and {@code Logic}.
     */
    public TimetableDetailPanel(LocalDate focusDate, Logic logic, Stage primaryStage) {
        super(FXML);

        // Set dependencies
        this.logic = logic;
        this.primaryStage = primaryStage;
        this.focusDate = focusDate;
        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        focusDate = logic.getFocusDate();

        //Get year and month of week
        Text year = new Text(String.valueOf(focusDate.getYear()));
        Text month = new Text(String.valueOf(focusDate.getMonth()));
        year.setFont(new Font(24));
        month.setFont(new Font(24));

        year.setText(String.valueOf(focusDate.getYear()));
        month.setText(String.valueOf(focusDate.getMonth()));
        monthYearPanel.getChildren().addAll(year, month);
        monthYearPanel.setSpacing(20);
        monthYearPanel.setAlignment(Pos.CENTER);

        dayOfWeekPanel.getChildren().add(new DayOfWeekPanel(logic, primaryStage).getRoot());
        dayOfMonthPanel.getChildren().add(new DayOfMonthPanel(focusDate, logic, primaryStage).getRoot());
        jobListPanel.getChildren().add(new WeekJobListPanel(logic, primaryStage).getRoot());

        logic.updateSortedDeliveryJobListByDate();
        logic.setWeekDeliveryJobList(focusDate);

    }
}
