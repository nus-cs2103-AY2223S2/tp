package trackr.testutil;

import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;

import java.time.LocalDateTime;

import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.Customer;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final MenuItem DEFAULT_ORDER_ITEM = CHOCOLATE_COOKIE_M;
    public static final String DEFAULT_ORDER_DEADLINE = "01/01/2027";
    public static final String DEFAULT_ORDER_QUANTITY = "3";
    public static final String DEFAULT_ORDER_STATUS = "N";
    public static final String DEFAULT_CUSTOMER_NAME = "John Doe";
    public static final String DEFAULT_CUSTOMER_PHONE = "98765432";
    public static final String DEFAULT_CUSTOMER_ADDRESS = "123 Main Street";
    public static final String DEFAULT_TIME_ADDED = "2007-12-03T10:15:30";

    private MenuItem orderItem;
    private OrderQuantity orderQuantity;
    private OrderDeadline orderDeadline;
    private OrderStatus orderStatus;
    private PersonName customerName;
    private PersonPhone customerPhone;
    private PersonAddress customerAddress;
    private LocalDateTime timeAdded;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        orderItem = CHOCOLATE_COOKIE_M;
        orderQuantity = new OrderQuantity(DEFAULT_ORDER_QUANTITY);
        orderDeadline = new OrderDeadline(DEFAULT_ORDER_DEADLINE);
        orderStatus = new OrderStatus(DEFAULT_ORDER_STATUS);
        customerName = new PersonName(DEFAULT_CUSTOMER_NAME);
        customerPhone = new PersonPhone(DEFAULT_CUSTOMER_PHONE);
        customerAddress = new PersonAddress(DEFAULT_CUSTOMER_ADDRESS);
        timeAdded = LocalDateTime.parse(DEFAULT_TIME_ADDED);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        orderItem = orderToCopy.getOrderItem();
        orderQuantity = orderToCopy.getOrderQuantity();
        orderDeadline = orderToCopy.getOrderDeadline();
        orderStatus = orderToCopy.getOrderStatus();
        customerName = orderToCopy.getCustomer().getCustomerName();
        customerPhone = orderToCopy.getCustomer().getCustomerPhone();
        customerAddress = orderToCopy.getCustomer().getCustomerAddress();
        timeAdded = orderToCopy.getTimeAdded();
    }

    /**
     * Sets the {@code OrderName} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderItem(MenuItem orderItem) {
        this.orderItem = orderItem;
        return this;
    }

    /**
     * Sets the {@code OrderQuantity} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderQuantity(String orderQuantity) {
        this.orderQuantity = new OrderQuantity(orderQuantity);
        return this;
    }

    /**
     * Sets the {@code OrderDeadline} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderDeadline(String orderDeadline) {
        this.orderDeadline = new OrderDeadline(orderDeadline);
        return this;
    }

    /**
     * Sets the {@code OrderStatus} of the {@code Order} that we are building (default case).
     */
    public OrderBuilder withOrderStatus() {
        this.orderStatus = new OrderStatus("N");
        return this;
    }

    /**
     * Sets the {@code OrderStatus} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderStatus(String orderStatus) {
        this.orderStatus = new OrderStatus(orderStatus);
        return this;
    }

    /**
     * Sets the {@code CustomerName} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomerName(String customerName) {
        this.customerName = new PersonName(customerName);
        return this;
    }

    /**
     * Sets the {@code CustomerPhone} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomerPhone(String customerPhone) {
        this.customerPhone = new PersonPhone(customerPhone);
        return this;
    }

    /**
     * Sets the {@code CustomerAddress} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomerAddress(String customerAddress) {
        this.customerAddress = new PersonAddress(customerAddress);
        return this;
    }

    /**
     * Sets the {@code TimeAdded} of the {@code Order} that we are building.
     */
    public OrderBuilder withTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
        return this;
    }

    /**
     * builds the order
     */
    public Order build() {
        Customer c = new Customer(customerName, customerPhone, customerAddress);
        return new Order(orderItem, orderDeadline, orderStatus, orderQuantity, c, timeAdded);
    }

}
