package trackr.model.order;

import trackr.model.ModelEnum;
import trackr.model.item.Item;
import trackr.model.item.UniqueItemList;

/**
 * A list of orders that enforces uniqueness between its elements and does not allow nulls.
 * an order is considered unique by comparing using {@code Order#isSameItem(Item)}. As such, adding and updating of
 * orders uses Order#isSameItem(Item) for equality to ensure that the Order being added or updated is unique
 * in terms of identity in the UniqueOrderList. However, the removal of an Order uses Order#equals(Object) to
 * ensure that the Order with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Order#isSameItem(Item)
 */
public class UniqueOrderList extends UniqueItemList<Order> {

    public UniqueOrderList() {
        super(ModelEnum.TASK);
    }
}
