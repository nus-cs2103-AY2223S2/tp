package trackr.model.order;

import java.util.Optional;

import trackr.commons.util.CollectionUtil;
import trackr.model.item.ItemDescriptor;
import trackr.model.menu.MenuItem;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;

//@@author chongweiguan-reused
/**
 * Stores the details of an order. Each non-empty field value will replace the corresponding field value of the order.
 */
public class OrderDescriptor implements ItemDescriptor<Order> {
    private OrderName orderName;
    private OrderDeadline orderDeadline;
    private OrderQuantity orderQuantity;
    private OrderStatus orderStatus;
    private PersonName customerName;
    private PersonPhone customerPhone;
    private PersonAddress customerAddress;
    private MenuItem orderItem;

    public OrderDescriptor() {}

    //@@author chongweiguan-reused
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

    //@@author chongweiguan-reused
    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(orderDeadline, orderName, orderQuantity,
                orderStatus, customerAddress, customerName, customerPhone);
    }

    //@@author chongweiguan-reused
    public void setCustomerAddress(PersonAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    //@@author chongweiguan-reused
    public Optional<PersonAddress> getCustomerAddress() {
        return Optional.ofNullable(customerAddress);
    }

    //@@author chongweiguan-reused
    public void setCustomerName(PersonName customerName) {
        this.customerName = customerName;
    }

    //@@author chongweiguan-reused
    public Optional<PersonName> getCustomerName() {
        return Optional.ofNullable(customerName);
    }

    //@@author chongweiguan-reused
    public void setCustomerPhone(PersonPhone customerPhone) {
        this.customerPhone = customerPhone;
    }

    //@@author chongweiguan-reused
    public Optional<PersonPhone> getCustomerPhone() {
        return Optional.ofNullable(customerPhone);
    }

    //@@author chongweiguan-reused
    public void setOrderDeadline(OrderDeadline orderDeadline) {
        this.orderDeadline = orderDeadline;
    }

    //@@author chongweiguan-reused
    public Optional<OrderDeadline> getOrderDeadline() {
        return Optional.ofNullable(orderDeadline);
    }

    public void setOrderName(OrderName orderName) {
        this.orderName = orderName;
    }

    //@@author chongweiguan-reused
    public Optional<OrderName> getOrderName() {
        return Optional.ofNullable(orderName);
    }

    //@@author chongweiguan-reused
    public void setOrderQuantity(OrderQuantity orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    //@@author chongweiguan-reused
    public Optional<OrderQuantity> getOrderQuantity() {
        return Optional.ofNullable(orderQuantity);
    }

    //@@author chongweiguan-reused
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    //@@author chongweiguan-reused
    public Optional<OrderStatus> getOrderStatus() {
        return Optional.ofNullable(orderStatus);
    }

    //@@author chongweiguan-reused
    public void setOrderItem(MenuItem orderItem) {
        this.orderItem = orderItem;
    }

    //@@author chongweiguan-reused
    public Optional<MenuItem> getOrderItem() {
        return Optional.ofNullable(orderItem);
    }

    //@@author chongweiguan-reused
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
