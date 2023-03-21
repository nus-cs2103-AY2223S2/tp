package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;

/**
 * Informs the user on the number of undone tasks
 */
public class EventCard extends UiPart<Region> {
    private static final String FXML = "EventListCard.fxml";
    public final Event event;
    //private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private HBox studentProfiles;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label notes;
    @FXML
    private Label attachments;
    @FXML
    private Label progressBar;
    @FXML
    private Label attendance;
    @FXML
    private FlowPane tags;

    /**
     * Adds an event with a unique index
     * @param event
     * @param displayedIndex
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;

        id.setText(displayedIndex + ". ");
        name.setText(event.getName());
        attachments.setText("" + event.countAttachments());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date.setText(event.getDate().format(formatter));
        //notes.setText("" + event.countNotes());
    }

    //Add more comparison in equals
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
