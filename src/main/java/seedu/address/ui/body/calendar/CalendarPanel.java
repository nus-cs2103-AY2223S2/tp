package seedu.address.ui.body.calendar;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.event.Event;
import seedu.address.ui.UiPart;

/**
 * A UI component containing a calendar.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarPanel.fxml";

    @FXML
    private VBox calendarContent;

    /**
     * Creates a {@code CalendarPanel} of the given {@code eventList}.
     */
    public CalendarPanel(ObservableList<Event> eventList) {
        super(FXML);
        Objects.requireNonNull(eventList);

        eventList.addListener((ListChangeListener<Event>) c -> {
            calendarContent.getChildren().clear();
            fillCalendarContent(sortEvents(eventList));
        });
    }

    private List<Event> sortEvents(List<Event> events) {
        return events.stream()
                .sorted(Comparator.comparing(event -> event.getStartDateTime().getDateTime()))
                .collect(Collectors.toList());
    }

    private void fillCalendarContent(List<Event> events) {
        LocalDate date = null;
        List<Event> dateEvents = new LinkedList<>();
        for (Event event : events) {
            if (!Objects.equals(date, event.getStartDateTime().getDateTime().toLocalDate())) {
                addDayCard(dateEvents, date);
                date = event.getStartDateTime().getDateTime().toLocalDate();
                dateEvents.clear();
            }
            dateEvents.add(event);
        }
    }

    private void addDayCard(List<Event> events, LocalDate date) {
        if (events == null || events.isEmpty()) {
            return;
        }
        if (date == null) {
            return;
        }
        calendarContent.getChildren().add(new CalendarDayCard(events, date).getRoot());
    }
}
