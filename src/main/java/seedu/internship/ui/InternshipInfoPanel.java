package seedu.internship.ui;

import java.util.Collection;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * A panel to display detailed information about an internship.
 */
public class InternshipInfoPanel extends UiPart<Region> {

    private static final String FXML = "InternshipInfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipInfoPanel.class);

    private Internship internship;

    @FXML
    private ScrollPane infoPanelContainer;

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
     * Creates a {@code InternshipInfoPanel} displaying all information of an Internship.
     */
    public InternshipInfoPanel() {
        super(FXML);
        // disable full display of info panel
        displayFullInfoPanel(false);

        // display default message
        displayDefaultMessage();
    }

    /**
     * Displays a message in the titleSegment of the InternshipInfoPanel.
     */
    public void displayDefaultMessage() {
        position.setText("Run 'view' command to view some internships.");
        position.setStyle("-fx-font-size: 14pt");
    }

    /**
     * Displays entire body of the InternshipInfoPanel if isVisible is set to True.
     * False otherwise. The body of InfoPanel includes the detailSegment and the eventSegment.
     */
    public void displayFullInfoPanel(boolean isVisible) {

        separator.setManaged(isVisible);
        company.setManaged(isVisible);

        ObservableList<Node> eventSegmentChildren = eventSegment.getChildren();
        ObservableList<Node> detailSegmentChildren = detailSegment.getChildren();
        for (Node i : eventSegmentChildren) {
            i.setManaged(isVisible);
            i.setVisible(isVisible);
        }
        for (Node i : detailSegmentChildren) {
            i.setManaged(isVisible);
            i.setVisible(isVisible);
        }
    }

    /**
     * Sets the content for all controls for InternshipInfoPanel.
     */
    public void setBodyContent(Internship internship, Collection<Event> events) {
        eventPanel.getChildren().clear();
        position.setText(internship.getPosition().positionName);
        company.setText(internship.getCompany().companyName);
        status.setText(internship.getStatus().toString());
        description.setText(internship.getDescription().descriptionMessage);
        eventPanel.getChildren().addAll(events.stream()
                .map(event -> new EventCard(event)
                        .getRoot())
                .collect(Collectors.toList()));
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName));
        //.forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Updates the {@code InternshipInfoPanel} displaying all information of an Internship.
     */
    public void updateInfoPanel(Internship internship, Collection<Event> events) {
        // activate all hidden elements
        displayFullInfoPanel(true);
        // set content of body
        setBodyContent(internship, events);
    }


}
