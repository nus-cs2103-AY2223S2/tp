package trackr.testutil;

import trackr.model.OrderList;
import trackr.model.order.Order;

/**
 * A utility class to help with building OrderList objects.
 */
public class OrderListBuilder {

    private OrderList orderList;

    public OrderListBuilder() {
        orderList = new OrderList();
    }

    public OrderListBuilder(OrderList orderList) {
        this.orderList = orderList;
    }

    /**
     * Adds a new {@code Order} to the {@code OrderList} that we are building.
     */
    public OrderListBuilder withOrder(Order order) {
        orderList.addOrder(order);
        return this;
    }

    public OrderList build() {
        return orderList;
    }
}
