package trackr.model.order;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import trackr.model.ModelEnum;
import trackr.model.item.Item;
import trackr.model.person.Customer;

/**
 * Represents an Order in the order list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order extends Item {

    //Data fields
    private final OrderName orderName;
    private final OrderDeadline orderDeadline;
    private final OrderStatus orderStatus;
    private final OrderQuantity orderQuantity;
    private final LocalDateTime timeAdded;

    //Customer
    private final Customer customer;

    /**
     * Every field must be present and not null
     */
    public Order(OrderName orderName, OrderDeadline orderDeadline, OrderStatus orderStatus,
                 OrderQuantity orderQuantity, Customer customer) {
        super(ModelEnum.ORDER);
        requireAllNonNull(orderName, orderDeadline, orderStatus, customer);
        this.orderName = orderName;
        this.orderDeadline = orderDeadline;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
        this.customer = customer;
        timeAdded = LocalDateTime.now();
    }

    /**
     * Every field must be present and not null
     */
    public Order(OrderName orderName, OrderDeadline orderDeadline, OrderStatus orderStatus,
                 OrderQuantity orderQuantity, Customer customer, LocalDateTime timeAdded) {
        super(ModelEnum.ORDER);
        requireAllNonNull(orderName, orderDeadline, orderStatus, customer);
        this.orderName = orderName;
        this.orderDeadline = orderDeadline;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
        this.customer = customer;
        this.timeAdded = timeAdded;
    }


    public OrderName getOrderName() {
        return orderName;
    }

    public OrderDeadline getOrderDeadline() {
        return orderDeadline;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderQuantity getOrderQuantity() {
        return orderQuantity;
    }

    public Customer getCustomer() {
        return customer;
    }
    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    /**
     * Compares 2 tasks using their time added.
     * @param otherOrder The order to compare with.
     * @return -1 if this order was added earlier than the other order.
     *         Returns 1 if this order was added later than the other order
     *         Returns 0 if both orders were added at the same time.
     */
    public int compareTimeAdded(Order otherOrder) {
        return timeAdded.compareTo(otherOrder.timeAdded);
    }

    /**
     * Compares 2 orders using their names (ignoring case).
     * @param otherOrder The order to compare with.
     * @return 1 if this name is lexicographically larger (ignoring case) than the other name,
     *        -1 if this name is lexicographically smaller (ignoring case) than the other name,
     *         0 if both names are lexicographically (ignoring case) equal.
     */
    public int compareName(Order otherOrder) {
        return orderName.compare(otherOrder.getOrderName());
    }

    /**
     * Compares 2 orders using their deadlines.
     * @param otherOrder The order to compare with.
     * @return -1 if this order has an earlier deadline than the other order.
     *         Returns 1 if this order has a later deadline than the other order
     *         Returns 0 if both orders have the same deadlines.
     */
    public int compareDeadline(Order otherOrder) {
        return orderDeadline.compare(otherOrder.getOrderDeadline());
    }

    /**
     * Compares 2 orders using their statuses.
     * @param otherOrder The order to compare with.
     * @return 1 if this order's status has lower a sorting priority than the other order's status,
     *         returns -1 if this order's status has higher a sorting priority than the other order's status,
     *         returns 0 if both tasks have the same statuses.
     *         (The sorting priority is Not Delivered > In Progress > Delivered)
     */
    public int compareStatus(Order otherOrder) {
        return orderStatus.compare(otherOrder.getOrderStatus());
    }

    /**
     * Compares 2 orders using their statuses and deadlines.
     * @param otherOrder The order to compare with.
     * @return -1 if this order's status has higher a sorting priority than the other order's status,
     *         or if both have the same status but this order has an earlier deadline.
     *         Returns 1 if this order's status has lower a sorting priority than the other order's status,
     *         or if both have the same status but this order has a later deadline.
     *         Returns 0 if both tasks have the same statuses and deadlines.
     *         (The sorting priority for order status is Not Delivered > In Progress > Delivered)
     */
    public int compareStatusAndDeadline(Order otherOrder) {
        int compareStatusResult = orderStatus.compare(otherOrder.getOrderStatus());

        if (compareStatusResult != 0) {
            return compareStatusResult;
        }
        //both order statuses are equal so compare deadline
        return orderDeadline.compare(otherOrder.getOrderDeadline());
    }

    /**
     * Returns true if both orders have the same name, deadline, quantity and customer
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (!(otherItem instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) otherItem;

        return otherOrder != null
                && otherOrder.getCustomer().equals(this.getCustomer())
                && otherOrder.getOrderName().equals(this.getOrderName())
                && otherOrder.getOrderDeadline().equals(this.getOrderDeadline())
                && otherOrder.getOrderQuantity().equals(this.getOrderQuantity());
    }

    /**
     * Returns true if both orders have the same name, deadline, quantity, customer and status.
     * This defines a stronger notion of equality between two orders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;

        return otherOrder != null
                && otherOrder.getCustomer().equals(this.getCustomer())
                && otherOrder.getOrderName().equals(this.getOrderName())
                && otherOrder.getOrderDeadline().equals(this.getOrderDeadline())
                && otherOrder.getOrderStatus().equals(this.getOrderStatus())
                && otherOrder.getOrderQuantity().equals(this.getOrderQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(customer, orderName, orderDeadline, orderStatus);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getOrderName())
                .append("; Quantity: ")
                .append(getOrderQuantity())
                .append("; Deadline: ")
                .append(getOrderDeadline())
                .append("; Status: ")
                .append(getOrderStatus())
                .append("; Customer: ")
                .append(getCustomer());
        return builder.toString();
    }

}
