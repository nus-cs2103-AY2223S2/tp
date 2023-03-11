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

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<Event> eventListViewLeftCol;
    @FXML
    private ListView<Event> eventListViewMidCol;
    @FXML
    private ListView<Event> eventListViewRightCol;

    private List<ListView<Event>> listOfEventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(List<ObservableList<Event>> eventList) {
        super(FXML);

        listOfEventListView = Arrays.asList(eventListViewLeftCol, eventListViewMidCol, eventListViewRightCol);
        for (int i = 0; i < eventList.size(); i++) {
            ObservableList<Event> filteredEventList = eventList.get(i);
            listOfEventListView.get(i).setItems(filteredEventList);
            listOfEventListView.get(i).setCellFactory(listView -> new EventListViewCell());
        }
        //bindListViewsScroll();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
            }
        }
    }
}
