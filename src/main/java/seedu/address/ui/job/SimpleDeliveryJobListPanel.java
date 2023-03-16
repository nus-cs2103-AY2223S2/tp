package seedu.address.ui.job;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class SimpleDeliveryJobListPanel extends UiPart<Region> {
    private static final String FXML = "DeliveryJobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeliveryJobListPanel.class);

    @FXML
    private ListView<DeliveryJob> deliveryJobListView;

    /**
     * Creates a {@code DeliveryJobListPanel} with the given {@code ObservableList}.
     */
    public SimpleDeliveryJobListPanel(ObservableList<DeliveryJob> jobList) {
        super(FXML);
        deliveryJobListView.setItems(jobList);
        deliveryJobListView.setCellFactory(listView -> new DeliveryJobListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code job} using a {@code PersonCard}.
     */
    class DeliveryJobListViewCell extends ListCell<DeliveryJob> {
        @Override
        protected void updateItem(DeliveryJob job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeliveryJobCard(job, getIndex() + 1).getRoot());
            }
        }
    }

}