package trackr.testutil;

import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderDescriptor;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;


/**
 * A utility class to help with building orderDescriptor objects.
 */
public class OrderDescriptorBuilder {

    private OrderDescriptor orderDescriptor;

    public OrderDescriptorBuilder() {
        orderDescriptor = new OrderDescriptor();
    }

    public OrderDescriptorBuilder(OrderDescriptor orderDescriptor) {
        this.orderDescriptor = new OrderDescriptor(orderDescriptor);
    }

    /**
     * Returns an {@code orderDescriptor} with fields containing {@code order}'s details
     */
    public OrderDescriptorBuilder(Order order) {
        orderDescriptor = new OrderDescriptor();
        orderDescriptor.setOrderName(order.getOrderName());
        orderDescriptor.setOrderDeadline(order.getOrderDeadline());
        orderDescriptor.setOrderStatus(order.getOrderStatus());
        orderDescriptor.setOrderQuantity(order.getOrderQuantity());
        orderDescriptor.setCustomerName(order.getCustomer().getCustomerName());
        orderDescriptor.setCustomerPhone(order.getCustomer().getCustomerPhone());
        orderDescriptor.setCustomerAddress(order.getCustomer().getCustomerAddress());
    }

    /**
     * Sets the {@code orderName} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withOrderName(String orderName) {
        orderDescriptor.setOrderName(new OrderName(orderName));
        return this;
    }

    /**
     * Sets the {@code orderDeadline} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withOrderDeadline(String orderDeadline) {
        orderDescriptor.setOrderDeadline(new OrderDeadline(orderDeadline));
        return this;
    }

    /**
     * Sets the {@code orderStatus} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withOrderStatus(String orderStatus) {
        orderDescriptor.setOrderStatus(new OrderStatus(orderStatus));
        return this;
    }

    /**
     * Sets the {@code orderQuantity} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withOrderQuantity(String orderQuantity) {
        orderDescriptor.setOrderQuantity(new OrderQuantity(orderQuantity));
        return this;
    }


    /**
     * Sets the {@code customerName} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withCustomerName(String customerName) {
        orderDescriptor.setCustomerName(new CustomerName(customerName));
        return this;
    }

    /**
     * Sets the {@code customerPhone} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withCustomerPhone(String customerPhone) {
        orderDescriptor.setCustomerPhone(new CustomerPhone(customerPhone));
        return this;
    }

    /**
     * Sets the {@code customerAddress} of the {@code orderDescriptor} that we are building.
     */
    public OrderDescriptorBuilder withCustomerAddress(String customerAddress) {
        orderDescriptor.setCustomerAddress(new CustomerAddress(customerAddress));
        return this;
    }

    public OrderDescriptor build() {
        return orderDescriptor;
    }
}
