package seedu.internship.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.internship.model.event.Event;


/**
 * A UI component that represents an Event.
 */
public class EventCard extends UiPart<Region> {

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
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label title;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    public EventCard(Event event) {
        super(FXML);
        this.event = event;
        setEventCardContent();
    }

    public void setEventCardContent() {
        title.setText(event.getName().toString());
        description.setText(event.getDescription().descriptionMessage);
        if (event.isDeadline()) {
            endLabel.setText("Deadline: ");
            end.setText(event.getEnd().toString());
            start.setManaged(false);
            startLabel.setManaged(false);
        } else {
            start.setText(event.getStart().toString());
            end.setText(event.getEnd().toString());
        }
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
