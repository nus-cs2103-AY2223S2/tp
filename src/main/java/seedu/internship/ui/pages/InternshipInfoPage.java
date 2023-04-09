package seedu.internship.ui.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * A page to display detailed information about an internship.
 */
public class InternshipInfoPage extends Page {

    private static final String FXML = "InternshipInfoPage.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipInfoPage.class);

    private Internship internship;
    private Collection<Event> internshipEvents;

    @FXML
    private ScrollPane pageContainer;

    @FXML
    private VBox titleSegment;
    @FXML
    private Separator separator;
    @FXML
    private Label position;
    @FXML
    private Label company;

    @FXML
    private VBox detailSegment;
    @FXML
    private HBox statusHolder;
    @FXML
    private Label status;
    @FXML
    private VBox descriptionHolder;
    @FXML
    private Label description;
    @FXML
    private Label tagsHolder;
    @FXML
    private FlowPane tags;

    @FXML
    private VBox eventSegment;
    @FXML
    private VBox eventPanel;


    /**
     * Creates a {@code InternshipInfoPage} displaying all information of an Internship.
     */
    public InternshipInfoPage(Internship internship, Collection<Event> events) {
        super(FXML);
        this.internship = internship;
        this.internshipEvents = events;
        setContent();
    }


    /**
     * Sets the content for all controls for InternshipInfoPage.
     */
    public void setContent() {
        eventPanel.getChildren().clear();
        position.setText(internship.getPosition().positionName);
        company.setText(internship.getCompany().companyName);
        status.setText(internship.getStatus().toString());
        description.setText(internship.getDescription().toString());
        eventPanel.getChildren().addAll(makeEventCards()
                        .stream()
                        .map(param -> param.getRoot())
                .collect(Collectors.toList()));
    }

    /**
     * Returns an ArrayList<{@code EventCard}> of indexed {@code EventCard}.
     */
    public ArrayList<EventCard> makeEventCards() {
        ArrayList<EventCard> eventCards = new ArrayList<>();
        for (Event event : internshipEvents) {
            EventCard eventCard = EventCard.of(event, true);
            eventCard.setEventId(eventCards.size() + 1);
            eventCards.add(eventCard);
        }
        return eventCards;
    }


}
