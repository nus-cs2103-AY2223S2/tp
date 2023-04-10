package seedu.address.ui.timetable;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;


/**
 * Panel containing the days of month (11th, 12th,...) in the week
 */
public class DayOfMonthPanel extends UiPart<Region> {

    private static final String FXML = "DayOfMonthPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private LocalDate focusDate;
    private Stage primaryStage;
    private Logic logic;

    private Text day1;
    private Text day2;
    private Text day3;
    private Text day4;
    private Text day5;
    private Text day6;
    private Text day7;

    @FXML
    private HBox dayOfMonthPanel;

    /**
     * Creates a {@code DayofMonthPanel} with the given {@code focusDate}, {@code Logic}
     * and {@code Stage}.
     */
    public DayOfMonthPanel(LocalDate focusDate, Logic logic, Stage primaryStage) {
        super(FXML);

        // Set dependencies
        this.focusDate = focusDate;
        this.logic = logic;
        this.primaryStage = primaryStage;

        int focusDayOfWeek = focusDate.getDayOfWeek().getValue();
        setAllDateText(focusDayOfWeek);
        setAllFont(12.0);
        setAllTextColorWhite();

        dayOfMonthPanel.getChildren().addAll(day1, day2, day3, day4, day5, day6, day7);
        dayOfMonthPanel.setSpacing((primaryStage.getWidth() - 200) / 6);
        dayOfMonthPanel.setAlignment(Pos.CENTER);
    }

    /**
     * Sets all text font to specific font size
     * @param fontSize
     */
    private void setAllFont(double fontSize) {
        day1.setFont(new Font(fontSize));
        day2.setFont(new Font(fontSize));
        day3.setFont(new Font(fontSize));
        day4.setFont(new Font(fontSize));
        day5.setFont(new Font(fontSize));
        day6.setFont(new Font(fontSize));
        day7.setFont(new Font(fontSize));

    }

    /**
     * Sets all text color to white
     */
    private void setAllTextColorWhite() {
        day1.setFill(Color.WHITE);
        day2.setFill(Color.WHITE);
        day3.setFill(Color.WHITE);
        day4.setFill(Color.WHITE);
        day5.setFill(Color.WHITE);
        day6.setFill(Color.WHITE);
        day7.setFill(Color.WHITE);
    }

    /**
     * Sets text in day of month for all days in week
     * @param focusDayOfWeek day in week of focus date
     */
    private void setAllDateText(int focusDayOfWeek) {
        int day1Text = focusDate.plusDays(1 - focusDayOfWeek).getDayOfMonth();
        int day2Text = focusDate.plusDays(2 - focusDayOfWeek).getDayOfMonth();
        int day3Text = focusDate.plusDays(3 - focusDayOfWeek).getDayOfMonth();
        int day4Text = focusDate.plusDays(4 - focusDayOfWeek).getDayOfMonth();
        int day5Text = focusDate.plusDays(5 - focusDayOfWeek).getDayOfMonth();
        int day6Text = focusDate.plusDays(6 - focusDayOfWeek).getDayOfMonth();
        int day7Text = focusDate.plusDays(7 - focusDayOfWeek).getDayOfMonth();

        day1 = new Text(String.valueOf(day1Text));
        day2 = new Text(String.valueOf(day2Text));
        day3 = new Text(String.valueOf(day3Text));
        day4 = new Text(String.valueOf(day4Text));
        day5 = new Text(String.valueOf(day5Text));
        day6 = new Text(String.valueOf(day6Text));
        day7 = new Text(String.valueOf(day7Text));

    }
}
