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
//@@author chongweiguan-reused
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

    public void setCustomerAddress(PersonAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Optional<PersonAddress> getCustomerAddress() {
        return Optional.ofNullable(customerAddress);
    }

    public void setCustomerName(PersonName customerName) {
        this.customerName = customerName;
    }

    public Optional<PersonName> getCustomerName() {
        return Optional.ofNullable(customerName);
    }

    public void setCustomerPhone(PersonPhone customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Optional<PersonPhone> getCustomerPhone() {
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

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    public void setOrderItem(MenuItem orderItem) {
        this.orderItem = orderItem;
    }

    public Optional<MenuItem> getOrderItem() {
        return Optional.ofNullable(orderItem);
    }
    //@@author

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
    //@@author
}
