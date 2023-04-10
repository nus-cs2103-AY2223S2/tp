package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the timetable.
 */
public class TimeSlotListPanel extends UiPart<Region> {
    private static final String FXML = "TimeSlotListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TimeSlotListPanel.class);

    // TODO: Update type accordingly

    @javafx.fxml.FXML
    private ListView<String> timeSlotListView;

    /**
     * Creates a {@code TimeSlotListPanel} with the given {@code ObservableList}.
     */
    public TimeSlotListPanel(ObservableList<String> slotList) {
        super(FXML);
        timeSlotListView.setItems(slotList);
        timeSlotListView.setCellFactory(listView -> new TimeSlotListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TimeSlotListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String timeSlot, boolean empty) {
            super.updateItem(timeSlot, empty);

            if (empty || timeSlot == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TimeSlotCard(timeSlot, getIndex() + 1).getRoot());
            }
        }
    }
}
