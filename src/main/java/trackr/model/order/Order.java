package trackr.model.order;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import trackr.model.order.customer.Customer;

public class Order {

    //Data fields
    private final OrderName orderName;
    private final OrderDeadline orderDeadline;
    private final OrderStatus orderStatus;

    //Customer
    private final Customer customer;

    public Order(OrderName orderName, OrderDeadline orderDeadline, OrderStatus orderStatus , Customer customer) {
        requireAllNonNull(orderName, orderDeadline, orderStatus, customer);
        this.orderName = orderName;
        this.orderDeadline = orderDeadline;
        this.orderStatus = orderStatus;
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

    public Customer getCustomer() {
        return customer;
    }

    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder!= null
                && otherOrder.getCustomer().equals(this.getCustomer())
                && otherOrder.getOrderName().equals(this.getOrderName())
                && otherOrder.getOrderDeadline().equals(this.getOrderDeadline());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;

        return otherOrder!= null
                && otherOrder.getCustomer().equals(this.getCustomer())
                && otherOrder.getOrderName().equals(this.getOrderName())
                && otherOrder.getOrderDeadline().equals(this.getOrderDeadline())
                && otherOrder.getOrderStatus().equals(this.getOrderStatus());
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
                .append("; Deadline")
                .append(getOrderDeadline())
                .append("; Status:")
                .append(getOrderStatus())
                .append("; Customer: ")
                .append(getCustomer());
        return builder.toString();
    }
    
}
