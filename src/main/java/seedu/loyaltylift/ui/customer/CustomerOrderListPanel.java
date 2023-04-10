package seedu.loyaltylift.ui.customer;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.ui.UiPart;

/**
 * Panel containing the list of orders that belong to a customer.
 */
public class CustomerOrderListPanel extends UiPart<VBox> {

    private static final String FXML = "Customer/CustomerOrderListPanel.fxml";

    @javafx.fxml.FXML
    private ListView<Order> orderListView;

    /**
     * Creates a {@code CustomerOrderListPanel} with the given {@code orders} that belong to a {@code Customer}.
     * @param orders The list of Orders that belong to the Customer.
     */
    public CustomerOrderListPanel(ObservableList<Order> orders) {
        super(FXML);

        orderListView.setItems(orders);
        orderListView.setCellFactory(order -> new CustomerOrderListViewCell());
    }

    class CustomerOrderListViewCell extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CustomerOrderCard(order, getIndex() + 1).getRoot());
            }
        }
    }

}
