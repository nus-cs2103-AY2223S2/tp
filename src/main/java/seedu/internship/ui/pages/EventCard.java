package seedu.internship.ui.pages;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import seedu.internship.model.event.Event;
import seedu.internship.ui.UiPart;


/**
 * A UI component that represents an Event.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventCard.fxml";

    public final Event event;

    @FXML
    private HBox eventCard;
    @FXML
    private Circle circle;
    @FXML
    private Separator separator;
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
    @FXML
    private Label companyAndPosition;
    @FXML
    private VBox timelineContainer;
    @FXML
    private VBox cardContent;
    @FXML
    private Separator separator2;
    @FXML
    private HBox wrapper;
    @FXML
    private Label eventId;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    private EventCard(Event event) {
        super(FXML);
        this.event = event;
        setEventCardContent();
    }

    /**
     * Returns the appropriate {@code EventCard} based on the parameter {@code withTimeline}
     *
     * @param event The event for this EventCard to display
     * @param withTimeline  A boolean value to indicate presence of a timeline
     * @return An {@code EventCard}
     */
    public static EventCard of(Event event, boolean withTimeline) {
        EventCard eventCard = new EventCard(event);
        if (withTimeline) {
            eventCard.cardContent.setPadding(new Insets(10, 0, 10, 0));
            eventCard.separator2.setManaged(false);
            eventCard.companyAndPosition.setManaged(false);
            eventCard.eventCard.setPrefWidth(500);
            eventCard.title.setPrefWidth(450);
        } else {
            eventCard.circle.setManaged(false);
            eventCard.circle.setVisible(false);
            eventCard.separator.setManaged(false);
            eventCard.timelineContainer.setManaged(false);
            eventCard.eventCard.setStyle("-fx-padding: 10 10 10 10;");
            String companyAndPositionString = String.format("[%s, %s] ",
                    event.getInternship().getCompany(),
                    event.getInternship().getPosition());
            eventCard.companyAndPosition.setText(companyAndPositionString);
        }
        return eventCard;
    }

    public void setEventCardContent() {
        title.setText(event.getName().toString());
        description.setText(event.getDescription().toString());
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

    /**
     * Sets id for this EventCard
     * @param i Integer for the index
     */
    public void setEventId(int i) {
        this.eventId.setText(String.format("%d. ", i));
    }
}
