package ezschedule.ui;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ezschedule.model.event.Event;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * A {@code Calendar} UI that displays information of {@code Event}.
 */
public class Calendar extends UiPart<Region> {

    private static final String FXML = "Calendar.fxml";

    private final ObservableList<Event> eventList;
    private final ObservableList<Event> findEventList;
    private final FilterExecutor filterExecutor;
    private final ZonedDateTime today;

    private ZonedDateTime date;
    private int monthMaxDate;

    @FXML
    private FlowPane calendar;
    @FXML
    private Text year;
    @FXML
    private Text month;

    /**
     * Creates a {@code Calendar} with the given {@code ObservableList} and {@code FilterExecutor}.
     */
    public Calendar(ObservableList<Event> eventList, ObservableList<Event> findEventList,
                    FilterExecutor filterExecutor) {
        super(FXML);
        this.eventList = eventList;
        this.findEventList = findEventList;
        this.filterExecutor = filterExecutor;
        date = ZonedDateTime.now();
        today = ZonedDateTime.now();
        monthMaxDate = date.getMonth().maxLength();
        eventList.addListener((ListChangeListener<Event>) c -> refreshCalendar());
        findEventList.addListener((ListChangeListener<Event>) c -> refreshCalendar());
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

    @FXML
    void jumpToNow() {
        date = ZonedDateTime.now();
        monthMaxDate = date.getMonth().maxLength();
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void refreshCalendar() {
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(date.getYear()));
        month.setText(String.valueOf(date.getMonth()));

        // List of activities for a given month
        Map<Integer, List<Event>> eventsForCurrentMonthMap = getEventsForCurrentMonth();

        // Check for non leap year and if it's february
        if (!isLeapYear() && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        drawCalendarBoxes(eventsForCurrentMonthMap);
    }

    private Map<Integer, List<Event>> getEventsForCurrentMonth() {
        List<Event> eventsForCurrentMonth = new ArrayList<>();
        eventList.forEach(e -> {
            if (isInCurrentMonth(e)) {
                eventsForCurrentMonth.add(e);
            }
        });
        return getDateToEventMap(eventsForCurrentMonth);
    }

    private Map<Integer, List<Event>> getDateToEventMap(List<Event> eventsForCurrentMonth) {
        Map<Integer, List<Event>> dateToEventMap = new HashMap<>();
        for (Event e : eventsForCurrentMonth) {
            int date = e.getDate().getDay();
            if (!dateToEventMap.containsKey(date)) {
                dateToEventMap.put(date, List.of(e));
            } else {
                List<Event> oldList = dateToEventMap.get(date);
                List<Event> newList = new ArrayList<>(oldList);
                newList.add(e);
                dateToEventMap.put(date, newList);
            }
        }
        return dateToEventMap;
    }

    private void drawCalendarBoxes(Map<Integer, List<Event>> eventsForMonthMap) {
        int dateOffset = ZonedDateTime.of(date.getYear(), date.getMonthValue(), 1, 0,
                0, 0, 0, date.getZone()).getDayOfWeek().getValue() % 7;
        int rows = 6;
        int columns = 7;

        // Draw 6 rows and 7 columns of boxes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        List<Event> eventsForCurrentDate = eventsForMonthMap.get(currentDate);
                        drawOneFilledCalendarBox(currentDate, eventsForCurrentDate);
                    }
                } else {
                    drawOneEmptyCalendarBox();
                }
            }
        }
    }

    private void drawOneFilledCalendarBox(int currentDate, List<Event> eventsForCurrentDate) {
        String date = String.valueOf(currentDate);
        boolean isFindCommand = isFindCommand(eventsForCurrentDate);
        calendar.getChildren().add(new CalendarBox(isFindCommand, isToday(currentDate),
                date, eventsForCurrentDate, filterExecutor).getRoot());
    }

    private void drawOneEmptyCalendarBox() {
        calendar.getChildren().add(new CalendarBox().getRoot());
    }

    private boolean isInCurrentMonth(Event event) {
        int year = date.getYear();
        int month = date.getMonth().getValue();
        return event.getDate().getYear() == year && event.getDate().getMonth() == month;
    }

    private boolean isToday(int currentDate) {
        return today.getYear() == date.getYear()
                && today.getMonth() == date.getMonth()
                && today.getDayOfMonth() == currentDate;
    }

    private boolean isLeapYear() {
        // Centuries year that are leap year are divisible by 400
        boolean isCenturyYear = date.getYear() % 100 == 0;
        if (isCenturyYear) {
            return date.getYear() % 400 == 0;
        }
        // Leap years are years that are divisible by 4
        return date.getYear() % 4 == 0;
    }

    private boolean isFindCommand(List<Event> eventsForCurrentDate) {
        return eventsForCurrentDate != null
                && eventsForCurrentDate.stream().anyMatch(findEventList::contains);
    }

    /**
     * Represents a function that updates the filtered event list.
     */
    @FunctionalInterface
    public interface FilterExecutor {
        /**
         * Executes the filtering of event list.
         */
        void updateFilteredEventList(Predicate<Event> e);
    }
}
