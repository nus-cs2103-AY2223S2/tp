package trackr.model.order;

import java.util.Optional;

import trackr.commons.util.CollectionUtil;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

/**
 * Stores the details of an order. Each non-empty field value will replace the corresponding field value of the order.
 */
public class OrderDescriptor {
    private OrderName orderName;
    private OrderDeadline orderDeadline;
    private OrderQuantity orderQuantity;
    private OrderStatus orderStatus;
    private CustomerName customerName;
    private CustomerPhone customerPhone;
    private CustomerAddress customerAddress;

    public OrderDescriptor() {}

    /**
     * Copy constructor.
     */
    public OrderDescriptor(OrderDescriptor toCopy) {
        setOrderName(toCopy.orderName);
        setOrderDeadline(toCopy.orderDeadline);
        setOrderQuantity(toCopy.orderQuantity);
        setOrderStatus(toCopy.orderStatus);
        setCustomerName(toCopy.customerName);
        setCustomerPhone(toCopy.customerPhone);
        setCustomerAddress(toCopy.customerAddress);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(orderDeadline, orderName, orderQuantity,
                orderStatus, customerAddress, customerName, customerPhone);
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Optional<CustomerAddress> getCustomerAddress() {
        return Optional.ofNullable(customerAddress);
    }

    public void setCustomerName(CustomerName customerName) {
        this.customerName = customerName;
    }

    public Optional<CustomerName> getCustomerName() {
        return Optional.ofNullable(customerName);
    }

    public void setCustomerPhone(CustomerPhone customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Optional<CustomerPhone> getCustomerPhone() {
        return Optional.ofNullable(customerPhone);
    }

    public void setOrderDeadline(OrderDeadline orderDeadline) {
        this.orderDeadline = orderDeadline;
    }

    public Optional<OrderDeadline> getOrderDeadline() {
        return Optional.ofNullable(orderDeadline);
    }

    public void setOrderName(OrderName orderName) {
        this.orderName = orderName;
    }

    public Optional<OrderName> getOrderName() {
        return Optional.ofNullable(orderName);
    }

    public void setOrderQuantity(OrderQuantity orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Optional<OrderQuantity> getOrderQuantity() {
        return Optional.ofNullable(orderQuantity);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Optional<OrderStatus> getOrderStatus() {
        return Optional.ofNullable(orderStatus);
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
                && getCustomerAddress().equals(orderDescriptor.getCustomerAddress())
                && getCustomerPhone().equals(orderDescriptor.getCustomerPhone())
                && getCustomerName().equals(orderDescriptor.getCustomerName());
    }

}
