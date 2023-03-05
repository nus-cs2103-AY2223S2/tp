package seedu.address.ui.body.calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.DetailedWeekView;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

/**
 * A UI component containing a calendar.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarPanel.fxml";

    @FXML
    private VBox calendarContent;

    /**
     * Creates a {@code CalendarPanel}.
     */
    public CalendarPanel() {
        super(FXML);

        calendarContent.getChildren().addAll(
                new CalendarDayCard().getRoot(),
                new CalendarDayCard().getRoot(),
                new CalendarDayCard().getRoot(),
                new CalendarDayCard().getRoot(),
                new CalendarDayCard().getRoot(),
                new CalendarDayCard().getRoot()
        );
    }
}
