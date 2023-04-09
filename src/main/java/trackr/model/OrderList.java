package trackr.model;

import trackr.model.item.ItemList;
import trackr.model.order.Order;

/**
 * Wraps all data at the order-list level.
 * Duplicates are not allowed (by .isSameOrder comparison).
 */
public class OrderList extends ItemList<Order> implements ReadOnlyOrderList {

    public OrderList() {
        super();
    }

    /**
     * Creates a OrderList using the Orders in the {@code toBeCopied}.
     */
    public OrderList(ReadOnlyOrderList toBeCopied) {
        super(toBeCopied);
    }
}
