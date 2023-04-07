package seedu.internship.ui.pages;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;

/**
 * A {@code Page} to display events as a result of the `event find` command.
 */
public class FindEventResultsPage extends Page {

    private static final String FXML = "FindEventResultsPage.fxml";
    private static final String PAGE_TITLE = "Results";
    private static final String MESSAGE_FOUND_EVENTS = "Here are the events we found.";
    private static final String MESSAGE_NO_EVENTS = "No events found :<";

    private final Logger logger = LogsCenter.getLogger(FindEventResultsPage.class);

    private ObservableList<Event> resultEvents;

    @javafx.fxml.FXML
    private ScrollPane pageContainer;

    @FXML
    private Label pageTitle;
    @FXML
    private Label pageSubtitle;

    @FXML
    private FlowPane resultBox;


    /**
     * Constructor for {@code FindEventResultsPage}.
     *
     * @param events A hashmap of clashing events.
     */
    public FindEventResultsPage(ObservableList<Event> events) {
        super(FXML);
        this.resultEvents = events;
        setHeadContent();
        setBodyContent();
    }

    /**
     * Fills in the content for the page's title and subtitle.
     */
    public void setHeadContent() {
        pageTitle.setText(PAGE_TITLE);
        if (!resultEvents.isEmpty()) {
            pageSubtitle.setText(MESSAGE_FOUND_EVENTS);
        } else {
            pageSubtitle.setText(MESSAGE_NO_EVENTS);
        }
    }


    /**
     * Sets the content for the body of FindEventResultsPage.
     */
    public void setBodyContent() {
        List<Node> eventCards = resultEvents
                .stream()
                .map(event -> EventCard.of(event, false))
                .map(eventCard -> eventCard.getRoot()).collect(Collectors.toList());
        this.resultBox.getChildren().addAll(eventCards);
    }
}
