package ezschedule.ui;

import java.util.logging.Logger;

import ezschedule.commons.core.LogsCenter;
import ezschedule.model.event.Event;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of upcoming events.
 */
public class ShowNextPanel extends UiPart<Region> {

    private static final String FXML = "ShowNextPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ShowNextPanel.class);

    @FXML
    private Label showNextLabel;
    @FXML
    private ListView<Event> showNextListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public ShowNextPanel(ObservableList<Event> eventList) {
        super(FXML);
        setUpcomingEvent(eventList);
        eventList.addListener((ListChangeListener<Event>) c -> setUpcomingEvent(eventList));
        showNextListView.setItems(eventList);
        showNextListView.setCellFactory(listView -> new ShowNextPanel.ShowNextViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class ShowNextViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                String displayNumber = String.format("%02d", getIndex() + 1);
                setGraphic(new ShowNextCard(event, displayNumber).getRoot());
            }
        }
    }

    private void setUpcomingEvent(ObservableList<Event> eventList) {
        showNextLabel.setText("Upcoming " + eventList.size() + " Events");
    }
}
