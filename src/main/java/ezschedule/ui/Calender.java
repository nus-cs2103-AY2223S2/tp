package ezschedule.ui;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ezschedule.model.event.Event;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * A UI component for the calender.
 */
public class Calender extends UiPart<Region> {

    private static final String FXML = "Calender.fxml";

    private ObservableList<Event> eventList;
    private ZonedDateTime date;

    @FXML
    private FlowPane calendar;

    @FXML
    private Text year;

    @FXML
    private Text month;


    public Calender(ObservableList<Event> eventList) {
        super(FXML);
        this.eventList = eventList;
        date = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth() {
        date = date.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        date = date.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(date.getYear()));
        month.setText(String.valueOf(date.getMonth()));

        // List of activities for a given month
        Map<Integer, List<Event>> calendarEventsMap = getEventsForMonth(date);

        int monthMaxDate = date.getMonth().maxLength();
        // Check for leap year
        if (date.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(date.getYear(), date.getMonthValue(),
                        1, 0, 0, 0, 0, date.getZone())
                .getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        String date = String.valueOf(currentDate);
                        List<Event> eventsForCurrentDate = calendarEventsMap.get(currentDate);
                        calendar.getChildren().add(new CalenderBox(date, eventsForCurrentDate).getRoot());
                    }
                } else {
                    calendar.getChildren().add(new CalenderBox(null, null).getRoot());
                }
            }
        }
    }

    private Map<Integer, List<Event>> getEventsForMonth(ZonedDateTime dateFocus) {
        List<Event> events = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        eventList.forEach(e -> {
            if (e.getDate().getYear() == year && e.getDate().getMonth() == month) {
                events.add(e);
            }
        });
        return createCalendarMap(events);
    }

    private Map<Integer, List<Event>> createCalendarMap(List<Event> events) {
        Map<Integer, List<Event>> calendarEventMap = new HashMap<>();
        for (Event e : events) {
            int date = e.getDate().getDay();
            if (!calendarEventMap.containsKey(date)) {
                calendarEventMap.put(date, List.of(e));
            } else {
                List<Event> oldList = calendarEventMap.get(date);
                List<Event> newList = new ArrayList<>(oldList);
                newList.add(e);
                calendarEventMap.put(date, newList);
            }
        }
        return calendarEventMap;
    }
}
