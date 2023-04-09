package seedu.internship.ui.pages;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;
import seedu.internship.ui.UiPart;

/**
 * A component for the {@code ClashInfoPage}.
 */
public class ClashInfoItem extends UiPart<Region> {
    private static final String FXML = "ClashInfoItem.fxml";
    private final Logger logger = LogsCenter.getLogger(ClashInfoPage.class);

    private List<Event> clashingEvents;

    @FXML
    private ListView<Event> clashList;

    /**
     * Constructor for {@code ClashInfoItem}
     *
     * @param clashingEvents The {@code Event}s that clash with the main {@code Event}
     */
    public ClashInfoItem(List<Event> clashingEvents) {
        super(FXML);
        this.clashingEvents = clashingEvents;
        //setEventInfoBox();
        clashList.setItems(FXCollections.observableList(clashingEvents));
        clashList.setCellFactory(listView -> new EventListViewCell());
        clashList.setMinHeight(350);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Internship} using a {@code InternshipCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(EventCard.of(event, false).getRoot());
            }
        }
    }

}
