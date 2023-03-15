package trackr.model;

import javafx.collections.ObservableList;
import trackr.model.order.Order;

/**
 * Unmodifiable view of a order list.
 */
public interface ReadOnlyOrderList {

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();
}
