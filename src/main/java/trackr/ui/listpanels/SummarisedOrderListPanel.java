package trackr.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackr.commons.core.LogsCenter;
import trackr.model.order.Order;
import trackr.ui.UiPart;
import trackr.ui.cards.SummarisedOrderCard;

//@@author arkarsg-reused
/**
 * Panel containing the list of summarised orders
 */
public class SummarisedOrderListPanel extends UiPart<Region> {
    private static final String FXML = "SummarisedOrderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @FXML
    private ListView<Order> summarisedOrderListView;

    /**
     * Creates a {@code SummarisedOrderListPanel} with the given {@code ObservableList}.
     */
    public SummarisedOrderListPanel(ObservableList<Order> orderList) {
        super(FXML);
        summarisedOrderListView.setItems(orderList);
        summarisedOrderListView.setCellFactory(listView -> new SummarisedOrderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code SummarisedOrderCard}
     */
    class SummarisedOrderListViewCell extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SummarisedOrderCard(order, getIndex() + 1).getRoot());
            }
        }
    }
}
