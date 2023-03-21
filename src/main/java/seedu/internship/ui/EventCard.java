package seedu.internship.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


/**
 * A UI component that represents an Event.
 */
public class EventCard extends UiPart<Region> {

    static class Event {
        private String start = "2019-09-23 6pm";
        private String end = "2019-09-24 8pm";
        private String description = "Technical Interview (1st Round)";

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public String getDesc() {
            return description;
        }
    }

    private static final String FXML = "EventCard.fxml";

    public final Event event;

    @FXML
    private HBox eventCard;
    @FXML
    private Label description;
    @FXML
    private Label start;
    @FXML
    private Label end;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    public EventCard(Event event) {
        super(FXML);
        this.event = event;
        description.setText(event.description);
        start.setText(event.start);
        end.setText(event.end);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return event.equals(card.event);
    }
}
