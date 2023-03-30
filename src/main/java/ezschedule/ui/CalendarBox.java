package ezschedule.ui;

import java.util.List;

import ezschedule.model.event.Event;
import ezschedule.model.event.EventMatchesDatePredicate;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A UI component that displays information of {@code Event} in the calendar.
 */
public class CalendarBox extends UiPart<Region> {

    private static final String FXML = "CalendarBox.fxml";

    private List<Event> events;
    private Calendar.FilterExecutor filterExecutor;

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
     * Creates an empty {@code CalenderBox}.
     */
    public CalendarBox() {
        super(FXML);
    }

    /**
     * Creates a {@code CalenderBox} with the given {@code List<Event>} and date to display.
     */
    public CalendarBox(boolean isFind, boolean isToday, String date,
                       List<Event> events, Calendar.FilterExecutor filterExecutor) {
        super(FXML);
        this.events = events;
        this.filterExecutor = filterExecutor;
        setHighlight(isFind);
        setDate(date);
        setToday(isToday);
        setEvents();
    }

    /**
     * List all events for the clicked date.
     */
    @FXML
    public void handleListEvents() {
        if (events != null) {
            filterExecutor.updateFilteredEventList(new EventMatchesDatePredicate(events.get(0).getDate()));
        }
    }

    private void setHighlight(boolean isFind) {
        if (isFind) {
            calendarHighlight.setStroke(Color.DARKORANGE);
        }
    }

    private void setDate(String date) {
        calendarDate.setText(date);
    }

    private void setToday(boolean isToday) {
        if (isToday) {
            now.setText("now");
            now.setStyle("-fx-text-fill: white; "
                    + "-fx-background-color: #4c837a; "
                    + "-fx-background-radius: 5;");
        }
    }

    private void setEvents() {
        if (events != null) {
            int firstEvent = 0;
            String eventNameOne = getEventName(events.get(firstEvent));
            Label eventOne = new Label(eventNameOne);
            setEventLabelStyle(eventOne);
            calendarEvents.getChildren().add(eventOne);

            if (events.size() == 2) {
                int secondEvent = 0;
                String eventNameTwo = getEventName(events.get(secondEvent));
                Label eventTwo = new Label(eventNameTwo);
                setEventLabelStyle(eventTwo);
                calendarEvents.getChildren().add(eventTwo);
            } else if (events.size() > 2) {
                Label moreEvents = new Label("...");
                setEventLabelStyle(moreEvents);
                calendarEvents.getChildren().add(moreEvents);
            }
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

    private void setEventLabelStyle(Label label) {
        label.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: #4c837a; "
                + "-fx-background-radius: 5; "
                + "-fx-font-size: 11; "
                + "-fx-padding: 0 10 0 10;");
    }
}
