package ezschedule.ui;

import java.time.format.DateTimeFormatter;

import ezschedule.model.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * A UI component that displays information of a {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";
    private static final Image CHECK_MARK_IMAGE = new Image("/images/check-mark.png");

    public final Event event;

    @FXML
    private StackPane cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label completed;
    @FXML
    private ImageView checkMark;

    /**
     * Creates a {@code EventCode} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, String displayedIndex) {
        super(FXML);
        this.event = event;

        id.setText(displayedIndex + ". ");
        name.setText(event.getName().fullName);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        date.setText(dateTimeFormatter.format(event.getDate().date));
        startTime.setText(event.getStartTime().toString());
        endTime.setText(event.getEndTime().toString());
        setCompleted(event.isCompleted());
    }

    private void setCompleted(boolean isCompleted) {
        if (isCompleted) {
            completed.setText("Completed");
            completed.setStyle("-fx-background-color: #3e7b91; -fx-background-radius: 10;");
            checkMark.setImage(CHECK_MARK_IMAGE);
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
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
