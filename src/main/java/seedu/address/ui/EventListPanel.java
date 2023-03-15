package seedu.address.ui;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.event.Tutorial;

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<Tutorial> eventListViewLeftCol;
    @FXML
    private ListView<Tutorial> eventListViewMidCol;
    @FXML
    private ListView<Tutorial> eventListViewRightCol;

    private List<ListView<Tutorial>> listOfEventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(List<ObservableList<? extends Event>> eventList) {
        super(FXML);

        listOfEventListView = Arrays.asList(eventListViewLeftCol, eventListViewMidCol, eventListViewRightCol);
        listOfEventListView.get(0).setItems((ObservableList<Tutorial>) eventList.get(0));
        listOfEventListView.get(0).setCellFactory(listView -> new EventListViewCell());
        //bindListViewsScroll();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Tutorial> {
        @Override
        protected void updateItem(Tutorial tutorial, boolean empty) {
            super.updateItem(tutorial, empty);

            if (empty || tutorial == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(tutorial, getIndex() + 1).getRoot());
            }
        }
    }
}
