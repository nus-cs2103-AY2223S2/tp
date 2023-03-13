package trackr.testutil;

import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.Customer;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final String DEFAULT_ORDER_NAME = "Chocolate Cookies";
    public static final String DEFAULT_ORDER_DEADLINE = "01/01/2027";
    public static final String DEFAULT_ORDER_STATUS = "N";
    public static final String DEFAULT_CUSTOMER_NAME = "John Doe";
    public static final String DEFAULT_CUSTOMER_PHONE = "98765432";
    public static final String DEFAULT_CUSTOMER_ADDRESS = "123 Main Street";

    private OrderName orderName;
    private OrderDeadline orderDeadline;
    private OrderStatus orderStatus;
    private CustomerName customerName;
    private CustomerPhone customerPhone;
    private CustomerAddress customerAddress;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        orderName = new OrderName(DEFAULT_ORDER_NAME);
        orderDeadline = new OrderDeadline(DEFAULT_ORDER_DEADLINE);
        orderStatus = new OrderStatus(DEFAULT_ORDER_STATUS);
        customerName = new CustomerName(DEFAULT_CUSTOMER_NAME);
        customerPhone = new CustomerPhone(DEFAULT_CUSTOMER_PHONE);
        customerAddress = new CustomerAddress(DEFAULT_CUSTOMER_ADDRESS);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        orderName = orderToCopy.getOrderName();
        orderDeadline = orderToCopy.getOrderDeadline();
        orderStatus = orderToCopy.getOrderStatus();
        customerName = orderToCopy.getCustomer().getCustomerName();
        customerPhone = orderToCopy.getCustomer().getCustomerPhone();
        customerAddress = orderToCopy.getCustomer().getCustomerAddress();
    }

    /**
     * Sets the {@code OrderName} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderName(String orderName) {
        this.orderName = new OrderName(orderName);
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
        this.customerName = new CustomerName(customerName);
        return this;
    }

    /**
     * Sets the {@code CustomerPhone} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomerPhone(String customerPhone) {
        this.customerPhone = new CustomerPhone(customerPhone);
        return this;
    }

    /**
     * Sets the {@code CustomerAddress} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomerAddress(String customerAddress) {
        this.customerAddress = new CustomerAddress(customerAddress);
        return this;
    }

    /**
     * builds the order
     */
    public Order build() {
        Customer c = new Customer(customerName, customerPhone, customerAddress);
        return new Order(orderName, orderDeadline, orderStatus, c);
    }

}
