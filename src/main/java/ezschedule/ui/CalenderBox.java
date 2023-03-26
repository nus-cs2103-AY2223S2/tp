package ezschedule.ui;

import java.util.List;

import ezschedule.model.event.Event;
import ezschedule.model.event.EventMatchesDatePredicate;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A UI component that displays information of {@code Event} in the calendar.
 */
public class CalenderBox extends UiPart<Region> {

    private static final String FXML = "CalenderBox.fxml";

    private List<Event> events;
    private Calender.FilterExecutor filterExecutor;

    @FXML
    private StackPane calenderBoxPane;
    @FXML
    private Text calenderDate;
    @FXML
    private VBox calenderEvents;
    @FXML
    private Rectangle calenderHighlight;

    /**
     * Creates an empty {@code CalenderBox}.
     */
    public CalenderBox() {
        super(FXML);
    }

    /**
     * Creates a {@code CalenderBox} with the given {@code List<Event>} and date to display.
     */
    public CalenderBox(String date, List<Event> events, Calender.FilterExecutor filterExecutor) {
        super(FXML);
        this.events = events;
        this.filterExecutor = filterExecutor;
        setDate(date);
        setBackgroundColor();
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

    private void setDate(String date) {
        calenderDate.setText(date);
    }

    private void setBackgroundColor() {
        calenderEvents.setStyle("-fx-background-color:GRAY");
    }

    private void setEvents() {
        if (events != null) {
            for (int i = 0; i < events.size(); i++) {
                if (i >= 1) {
                    Text moreEvents = new Text("...");
                    calenderEvents.getChildren().add(moreEvents);
                    break;
                }
                String eventName = getEventName(events.get(i));
                Text event = new Text(eventName);
                calenderEvents.getChildren().add(event);
            }
        }
    }

    private String getEventName(Event event) {
        String name = event.getName().toString();
        if (name.length() > 5) {
            String firstFiveChars = name.substring(0, 5);
            name = firstFiveChars + "...";
        }
        return name;
    }
}
