package seedu.address.ui.body.calendar;

import com.calendarfx.view.DetailedDayView;

import javafx.fxml.FXML;
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

        calendarPlaceholder.getChildren().add(new DetailedDayView());
    }
}
