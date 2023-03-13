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
    public EventListPanel(List<ObservableList<Tutorial>> eventList) {
        super(FXML);

        listOfEventListView = Arrays.asList(eventListViewLeftCol, eventListViewMidCol, eventListViewRightCol);
        for (int i = 0; i < eventList.size(); i++) {
            ObservableList<Tutorial> filteredEventList = eventList.get(i);
            listOfEventListView.get(i).setItems(filteredEventList);
            listOfEventListView.get(i).setCellFactory(listView -> new EventListViewCell());
        }
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
