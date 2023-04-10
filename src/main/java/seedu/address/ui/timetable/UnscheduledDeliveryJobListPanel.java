package seedu.address.ui.timetable;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;
import seedu.address.ui.jobs.DeliveryJobCard;

/**
 * Panel containing the list of unscheduled jobs.
 */
public class UnscheduledDeliveryJobListPanel extends UiPart<Region> {
    private static final String FXML = "UnscheduledDeliveryJobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(UnscheduledDeliveryJobListPanel.class);

    @FXML
    private ListView<DeliveryJob> deliveryJobListView;

    /**
     * Creates a {@code UnscheduledDeliveryJobListPanel} with the given {@code ObservableList}.
     */
    public UnscheduledDeliveryJobListPanel(ObservableList<DeliveryJob> jobList) {
        super(FXML);
        deliveryJobListView.setItems(jobList);
        deliveryJobListView.setCellFactory(listView -> new DeliveryJobListViewCell());
        logger.info("Updated and showed list of unscheduled jobs");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code job} using a
     * {@code DeliveryJobCard}.
     */
    class DeliveryJobListViewCell extends ListCell<DeliveryJob> {
        @Override
        protected void updateItem(DeliveryJob job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeliveryJobCard(job, getIndex() + 1, (card) -> {}).getRoot());
            }
        }
    }

    /**
     * Returns total number of jobs listed.
     */
    public int size() {
        return deliveryJobListView.getItems().size();
    }

}
