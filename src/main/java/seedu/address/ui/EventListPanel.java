package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
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
    private ListView<Lab> eventListViewMidCol;
    @FXML
    private ListView<Tutorial> eventListViewRightCol;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(List<ObservableList<? extends Event>> eventList) {
        super(FXML);

        eventListViewLeftCol.setItems((ObservableList<Tutorial>) eventList.get(0));
        eventListViewLeftCol.setCellFactory(listView -> new TutorialListViewCell());

        eventListViewMidCol.setItems((ObservableList<Lab>) eventList.get(1));
        eventListViewMidCol.setCellFactory(listView -> new LabListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class TutorialListViewCell extends ListCell<Tutorial> {
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

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class LabListViewCell extends ListCell<Lab> {
        @Override
        protected void updateItem(Lab lab, boolean empty) {
            super.updateItem(lab, empty);

            if (empty || lab == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(lab, getIndex() + 1).getRoot());
            }
        }
    }
}
