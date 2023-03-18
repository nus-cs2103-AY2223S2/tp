package seedu.address.ui.body.calendar;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    @FXML
    private Label calendarContentPlaceholder;

    /**
     * Creates a {@code CalendarPanel} of the given {@code eventList}.
     */
    public CalendarPanel(ObservableList<Event> eventList) {
        super(FXML);
        Objects.requireNonNull(eventList);

        fillCalendarContent(eventList);
        eventList.addListener((ListChangeListener<Event>) c -> fillCalendarContent(eventList));
    }

    private void fillCalendarContent(List<Event> events) {
        calendarContent.getChildren().clear();
        if (events == null || events.isEmpty()) {
            calendarContent.getChildren().add(calendarContentPlaceholder);
            return;
        }

        Map<LocalDate, List<Event>> dateEventsMap = new HashMap<>();
        for (Event event : events) {
            LocalDate date = event.getStartDateTime().getDateTime().toLocalDate();
            List<Event> dateEvents = dateEventsMap.getOrDefault(date, new LinkedList<>());
            dateEvents.add(event);
            dateEventsMap.putIfAbsent(date, dateEvents);
        }
        dateEventsMap.keySet().stream().sorted().forEach(date -> {
            List<Event> dateEvents = dateEventsMap.get(date);
            addDayCard(dateEvents, date);
        });
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
