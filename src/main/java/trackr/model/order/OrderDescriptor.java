package trackr.model.order;

import java.util.Optional;

import trackr.commons.util.CollectionUtil;
import trackr.model.order.customer.Customer;

public class OrderDescriptor {
    private OrderName orderName;
    private OrderDeadline orderDeadline;
    private OrderStatus orderStatus;
    private OrderQuantity orderQuantity;
    private Customer customer;

    public OrderDescriptor() {}

    /**
     * Copy constructor.
     */
    public OrderDescriptor(OrderDescriptor toCopy) {
        setOrderName(toCopy.orderName);
        setOrderDeadline(toCopy.orderDeadline);
        setOrderStatus(toCopy.orderStatus);
        setOrderQuantity(toCopy.orderQuantity);
        setCustomer(toCopy.customer);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(orderName, orderDeadline, orderStatus, orderQuantity, customer);
    }

    public void setOrderName(OrderName orderName) {
        this.orderName = orderName;
    }

    public Optional<OrderName> getOrderName() {
        return Optional.ofNullable(orderName);
    }

    public void setOrderDeadline(OrderDeadline orderDeadline) {
        this.orderDeadline = orderDeadline;
    }

    public Optional<OrderDeadline> getOrderDeadline() {
        return Optional.ofNullable(orderDeadline);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Optional<OrderStatus> getOrderStatus() {
        return Optional.ofNullable(orderStatus);
    }

    public void setOrderQuantity(OrderQuantity orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Optional<OrderQuantity> getOrderQuantity() {
        return Optional.ofNullable(orderQuantity);
    }
    public Optional<Customer> getCustomer() {
        return Optional.ofNullable(customer);
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderDescriptor)) {
            return false;
        }

        // state check
        OrderDescriptor orderDescriptor = (OrderDescriptor) other;

        return getOrderName().equals(orderDescriptor.getOrderName())
                && getOrderDeadline().equals(orderDescriptor.getOrderDeadline())
                && getOrderStatus().equals(orderDescriptor.getOrderStatus())
                && getOrderQuantity().equals(orderDescriptor.getOrderQuantity())
                && getCustomer().equals(orderDescriptor.getCustomer());


    }
}
