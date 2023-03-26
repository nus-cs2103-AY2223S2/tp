package ezschedule.ui;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ezschedule.model.event.Event;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * A UI component for the calendar.
 */
public class Calender extends UiPart<Region> {

    private static final String FXML = "Calender.fxml";

    private final ObservableList<Event> eventList;
    private final FilterExecutor filterExecutor;
    private ZonedDateTime date;
    private int monthMaxDate;

    @FXML
    private FlowPane calendar;
    @FXML
    private Text year;
    @FXML
    private Text month;

    /**
     * Creates a {@code Calender} with the given {@code ObservableList}{@code CommandExecutor}}.
     */
    public Calender(ObservableList<Event> eventList, FilterExecutor filterExecutor) {
        super(FXML);
        this.eventList = eventList;
        this.filterExecutor = filterExecutor;
        date = ZonedDateTime.now();
        monthMaxDate = date.getMonth().maxLength();
        drawCalendar();
    }

    @FXML
    void backOneMonth() {
        date = date.minusMonths(1);
        monthMaxDate = date.getMonth().maxLength();
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        date = date.plusMonths(1);
        monthMaxDate = date.getMonth().maxLength();
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(date.getYear()));
        month.setText(String.valueOf(date.getMonth()));

        // List of activities for a given month
        Map<Integer, List<Event>> eventsForMonthMap = getEventsForMonth();

        // Check for leap year
        if (isLeapYear()) {
            monthMaxDate = 28;
        }
        drawCalenderBoxes(eventsForMonthMap);
    }

    private Map<Integer, List<Event>> getEventsForMonth() {
        List<Event> events = new ArrayList<>();
        eventList.forEach(e -> {
            if (isInMonth(e)) {
                events.add(e);
            }
        });
        return getCalenderMap(events);
    }

    private Map<Integer, List<Event>> getCalenderMap(List<Event> events) {
        Map<Integer, List<Event>> calendarEventsMap = new HashMap<>();
        for (Event e : events) {
            int date = e.getDate().getDay();
            if (!calendarEventsMap.containsKey(date)) {
                calendarEventsMap.put(date, List.of(e));
            } else {
                List<Event> oldList = calendarEventsMap.get(date);
                List<Event> newList = new ArrayList<>(oldList);
                newList.add(e);
                calendarEventsMap.put(date, newList);
            }
        }
        return calendarEventsMap;
    }

    private void drawCalenderBoxes(Map<Integer, List<Event>> eventsForMonthMap) {
        int dateOffset = ZonedDateTime.of(date.getYear(), date.getMonthValue(), 1, 0,
                0, 0, 0, date.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        String date = String.valueOf(currentDate);
                        List<Event> eventsForCurrentDate = eventsForMonthMap.get(currentDate);
                        calendar.getChildren().add(
                                new CalenderBox(date, eventsForCurrentDate, filterExecutor).getRoot());
                    }
                } else {
                    calendar.getChildren().add(new CalenderBox().getRoot());
                }
            }
        }
    }

    private boolean isInMonth(Event event) {
        int year = date.getYear();
        int month = date.getMonth().getValue();
        return event.getDate().getYear() == year && event.getDate().getMonth() == month;
    }

    private boolean isLeapYear() {
        return date.getYear() % 4 != 0 && monthMaxDate == 29;
    }

    /**
     * Represents a function that can update the filtered event list.
     */
    @FunctionalInterface
    public interface FilterExecutor {
        /**
         * Executes the filtering of event list.
         */
        void updateFilteredEventList(Predicate<Event> e);
    }
}
