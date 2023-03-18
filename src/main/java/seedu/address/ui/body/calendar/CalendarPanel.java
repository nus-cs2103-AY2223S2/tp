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
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
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

        Map<LocalDate, List<IndexedEvent>> dateEventsMap = new HashMap<>();
        for (IndexedEvent event : getIndexedEvents(events)) {
            LocalDate date = event.getDateKey();
            List<IndexedEvent> dateEvents = dateEventsMap.getOrDefault(date, new LinkedList<>());
            dateEvents.add(event);
            dateEventsMap.putIfAbsent(date, dateEvents);
        }
        dateEventsMap.keySet().stream().sorted().forEach(date -> {
            List<IndexedEvent> dateEvents = dateEventsMap.get(date);
            addDayCard(dateEvents, date);
        });
    }

    private void addDayCard(List<IndexedEvent> events, LocalDate date) {
        if (events == null || events.isEmpty()) {
            return;
        }
        if (date == null) {
            return;
        }
        calendarContent.getChildren().add(new CalendarDayCard(events, date).getRoot());
    }

    private List<IndexedEvent> getIndexedEvents(List<Event> events) {
        List<IndexedEvent> indexedEvents = new LinkedList<>();
        int i = 0;
        for (Event event : events) {
            indexedEvents.add(new IndexedEvent(Index.fromZeroBased(i), event));
            i += 1;
        }
        return indexedEvents;
    }

    static class IndexedEvent {
        private final Index index;
        private final Event event;

        public IndexedEvent(Index index, Event event) {
            CollectionUtil.requireAllNonNull(index, event);
            this.index = index;
            this.event = event;
        }

        public Index getIndex() {
            return index;
        }

        public Event getEvent() {
            return event;
        }

        public LocalDate getDateKey() {
            return event.getStartDateTime().getDateTime().toLocalDate();
        }
    }
}
