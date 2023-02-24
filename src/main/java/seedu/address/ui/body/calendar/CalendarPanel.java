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
import seedu.address.ui.UiPart;

/**
 * A UI component containing a calendar.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarPanel.fxml";

    @FXML
    private StackPane calendarPlaceholder;


    /**
     * Creates a {@code CalendarPanel}.
     */
    public CalendarPanel() {
        super(FXML);

        calendarPlaceholder.getChildren().add(getCalendarNode());
    }

    private Node getCalendarNode() {
        Calendar<String> calendar = new Calendar<>("Events");
        calendar.setStyle(Calendar.Style.STYLE5);
        calendar.setReadOnly(true); // disables entry modification

        CalendarSource calendarSource = new CalendarSource("Calendars");
        calendarSource.getCalendars().add(calendar);

        DetailedWeekView weekView = new DetailedWeekView();
        weekView.setContextMenuCallback(null); // disables the context menu
        weekView.setEntryFactory(param -> null); // disables entry creation
        weekView.getCalendarSources().add(calendarSource);

        // Updates the current date and time every 10 seconds
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            weekView.setToday(LocalDate.now());
            weekView.setTime(LocalTime.now());
        }, 10, 10, TimeUnit.SECONDS);

        return weekView;
    }
}
