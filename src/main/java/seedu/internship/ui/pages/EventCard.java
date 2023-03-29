package seedu.internship.ui.pages;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
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
    private VBox timelineContainer;
    @FXML
    private VBox cardContent;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    private EventCard(Event event) {
        super(FXML);
        this.event = event;
        setEventCardContent();
    }

    public static EventCard of(Event event, boolean withTimeline) {
        EventCard eventCard = new EventCard(event);
        if (withTimeline) {
            eventCard.cardContent.setPadding(new Insets(10,0,10,0));
        } else {
            eventCard.circle.setManaged(false);
            eventCard.circle.setVisible(false);
            eventCard.separator.setManaged(false);
            eventCard.timelineContainer.setManaged(false);
            eventCard.eventCard.setStyle(
                    "-fx-background-color: derive(#9bd2ec, 60%);" + "-fx-padding: 10 10 10 10;");
            eventCard.eventCard.setPrefWidth(300);
            eventCard.eventCard.setPrefHeight(200);
        }
        return eventCard;
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
