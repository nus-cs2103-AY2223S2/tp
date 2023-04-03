package ezschedule.ui;

import java.util.List;

import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.EventMatchesDatePredicate;
import ezschedule.ui.Calendar.FilterExecutor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A UI component for the {@code Calendar}.
 */
public class CalendarBox extends UiPart<Region> {

    private static final String FXML = "CalendarBox.fxml";

    private List<Event> events;
    private FilterExecutor filterExecutor;

    @FXML
    private StackPane calendarBoxPane;
    @FXML
    private Text calendarDate;
    @FXML
    private VBox calendarEvents;
    @FXML
    private Rectangle calendarHighlight;
    @FXML
    private Label now;

    /**
     * Creates an empty {@code CalendarBox}.
     */
    public CalendarBox() {
        super(FXML);
    }

    /**
     * Creates a filled {@code CalendarBox} with the given {@code List<Event>} and date to display.
     */
    public CalendarBox(boolean isFind, boolean isToday, String date,
                       List<Event> events, FilterExecutor filterExecutor) {
        super(FXML);
        this.events = events;
        this.filterExecutor = filterExecutor;
        setHighlight(isFind);
        setToday(isToday);
        setDate(date);
        setEvents();
    }

    /**
     * List all events for the clicked date.
     */
    @FXML
    public void handleListEvents() {
        if (events == null) {
            return;
        }

        int firstEventIndex = 0;
        Date date = events.get(firstEventIndex).getDate();
        filterExecutor.updateFilteredEventList(new EventMatchesDatePredicate(date));
    }

    private void setHighlight(boolean isFind) {
        if (isFind) {
            calendarHighlight.setStroke(Color.DARKORANGE);
        }
    }

    private void setToday(boolean isToday) {
        if (isToday) {
            now.setText("now");
            now.setStyle("-fx-text-fill: white; "
                    + "-fx-background-color: #4c837a; "
                    + "-fx-background-radius: 5;");
        }
    }

    private void setDate(String date) {
        calendarDate.setText(date);
    }

    private void setEvents() {
        if (events == null) {
            return;
        }

        int firstEventIndex = 0;
        Event firstEvent = events.get(firstEventIndex);
        String firstEventName = getEventName(firstEvent);
        setEventLabel(firstEventName);

        if (events.size() == 2) {
            int secondEventIndex = 1;
            Event secondEvent = events.get(secondEventIndex);
            String secondEventName = getEventName(secondEvent);
            setEventLabel(secondEventName);

        } else if (events.size() > 2) {
            String moreEvents = "...";
            setEventLabel(moreEvents);
        }
    }

    private String getEventName(Event event) {
        String name = event.getName().toString();
        if (isNameTooLong(name)) {
            name = refactorName(name);
        }
        return name;
    }

    private boolean isNameTooLong(String name) {
        return name.length() > 5;
    }

    private String refactorName(String name) {
        return name.substring(0, 5) + "...";
    }

    private void setEventLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: #4c837a; "
                + "-fx-background-radius: 5; "
                + "-fx-font-size: 11; "
                + "-fx-padding: 0 10 0 10;");
        calendarEvents.getChildren().add(label);
    }
}
