package trackr.model.order;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import trackr.model.order.customer.Customer;

/**
 * Represents an Order in the order list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {

    //Data fields
    private final OrderName orderName;
    private final OrderDeadline orderDeadline;
    private final OrderStatus orderStatus;
    private final OrderQuantity orderQuantity;

    //Customer
    private final Customer customer;

    /**
     * Every field must be present and not null
     */
    public Order(OrderName orderName, OrderDeadline orderDeadline, OrderStatus orderStatus,
            OrderQuantity orderQuantity, Customer customer) {
        requireAllNonNull(orderName, orderDeadline, orderStatus, customer);
        this.orderName = orderName;
        this.orderDeadline = orderDeadline;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
        this.customer = customer;
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
    /**
     * Returns true if both orders have the same name, deadline, quantity and customer
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

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
