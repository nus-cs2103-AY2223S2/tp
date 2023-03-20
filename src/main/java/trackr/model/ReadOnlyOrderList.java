package trackr.model;

import trackr.model.item.ReadOnlyItemList;
import trackr.model.order.Order;

/**
 * Unmodifiable view of a order list.
 */
public interface ReadOnlyOrderList extends ReadOnlyItemList<Order> {
}
