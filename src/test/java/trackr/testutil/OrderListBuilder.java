package trackr.testutil;

import trackr.model.OrderList;
import trackr.model.order.Order;

//@@author chongweiguan-reused
/**
 * A utility class to help with building OrderList objects.
 */
public class OrderListBuilder {

    private OrderList orderList;

    //@@author chongweiguan-reused
    public OrderListBuilder() {
        orderList = new OrderList();
    }

    //@@author chongweiguan-reused
    public OrderListBuilder(OrderList orderList) {
        this.orderList = orderList;
    }

    //@@author chongweiguan-reused
    /**
     * Adds a new {@code Order} to the {@code OrderList} that we are building.
     */
    public OrderListBuilder withOrder(Order order) {
        orderList.addItem(order);
        return this;
    }

    //@@author chongweiguan-reused
    public OrderList build() {
        return orderList;
    }
}
