package trackr.model;

import trackr.model.item.ReadOnlyItemList;
import trackr.model.order.Order;

/**
 * Unmodifiable view of a order list.
 */
//@@author liumc-sg-reused
public interface ReadOnlyOrderList extends ReadOnlyItemList<Order> {
}
