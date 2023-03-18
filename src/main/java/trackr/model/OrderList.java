package trackr.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import trackr.model.order.Order;
import trackr.model.order.UniqueOrderList;

/**
 * Wraps all data at the order-list level
 * Duplicates are not allowed (by .isSameOrder comparison)
 */
public class OrderList implements ReadOnlyOrderList {

    private final UniqueOrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        orders = new UniqueOrderList();
    }

    public OrderList() {}

    /**
     * Creates an OrderList using the Orders in the {@code toBeCopied}
     */
    public OrderList(ReadOnlyOrderList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the order list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code OrderList} with {@code newData}.
     */
    public void resetData(ReadOnlyOrderList newData) {
        requireNonNull(newData);

        setOrders(newData.getOrderList());
    }

    //// order-level operations

    /**
     * Returns true if a order with the same identity as {@code order} exists in the order list.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds a order to the order list.
     * The order must not already exist in the order list.
     */
    public void addOrder(Order p) {
        orders.add(p);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the order list.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);
    }

    /**
     * Removes {@code key} from this {@code OrderList}.
     * {@code key} must exist in the order list.
     */
    public void removeOrder(Order key) {
        orders.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return orders.asUnmodifiableObservableList().size() + " orders";
        // TODO: refine later
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderList // instanceof handles nulls
                && orders.equals(((OrderList) other).orders));
    }

    @Override
    public int hashCode() {
        return orders.hashCode();
    }
}
