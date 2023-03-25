package seedu.loyaltylift.ui.order;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.loyaltylift.model.order.Status;
import seedu.loyaltylift.model.order.StatusUpdate;
import seedu.loyaltylift.ui.UiPart;

/**
 * Panel containing the list of status history that belong to an order.
 */
public class OrderHistoryPanel extends UiPart<Region> {

    private static final String FXML = "Order/OrderHistoryPanel.fxml";

    @javafx.fxml.FXML
    private ListView<StatusUpdate> orderHistoryListView;

    /**
     * Creates a {@code OrderHistoryPanel} with the given {@code Status} that belong to an {@code Order}.
     * @param status The status of the Order.
     */
    public OrderHistoryPanel(Status status) {
        super(FXML);

        // Inverse the order of status
        ArrayList<StatusUpdate> statusUpdateList = new ArrayList<>(status.getStatusUpdates());
        statusUpdateList.sort(Collections.reverseOrder());
        orderHistoryListView.setItems(FXCollections.observableArrayList(statusUpdateList));
        orderHistoryListView.setCellFactory(orderHistory -> new OrderHistoryListViewCell());
    }

    class OrderHistoryListViewCell extends ListCell<StatusUpdate> {
        @Override
        protected void updateItem(StatusUpdate statusUpdate, boolean empty) {
            super.updateItem(statusUpdate, empty);

            if (empty || statusUpdate == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderHistoryCard(statusUpdate, getIndex() + 1).getRoot());
            }
        }
    }
}
