package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_ADDRESS;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_NAME;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_PHONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.OrderList;
import trackr.model.order.Order;


/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order CHEESE_CAKES = new OrderBuilder().withOrderName("Cheese Cakes")
            .withOrderDeadline("01/01/2024")
            .withOrderStatus("N")
            .withCustomerName("John Doe")
            .withCustomerPhone("98765432")
            .withCustomerAddress("123 Main Street").build();

    public static final Order DONUTS = new OrderBuilder().withOrderName("Donuts")
            .withOrderDeadline("12/12/2024")
            .withOrderStatus("N")
            .withCustomerName("Mary Jane")
            .withCustomerPhone("12345678")
            .withCustomerAddress("321 Jurong Street").build();

    public static final Order VANILLA_CAKE = new OrderBuilder().withOrderName("Vanilla Cake")
            .withOrderDeadline("31/12/2024")
            .withOrderStatus("N")
            .withCustomerName("Alex Lim")
            .withCustomerPhone("24681357")
            .withCustomerAddress("456 Bedok Street").build();

    public static final Order CHOCOLATE_COOKIES = new OrderBuilder().withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
            .withOrderDeadline(VALID_ORDER_DEADLINE_2100)
            .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
            .withCustomerName(VALID_CUSTOMER_NAME)
            .withCustomerPhone(VALID_CUSTOMER_PHONE)
            .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();

    public static final String KEYWORD_MATCHING_BUY = "Buy"; // A keyword that matches BUY

    public TypicalOrders() {} //prevents instantiation

    /**
     * Returns an {@code OrderList} with all the typical orders.
     */
    public static OrderList getTypicalOrderList() {
        OrderList orderList = new OrderList();
        for (Order order : getTypicalOrders()) {
            orderList.addOrder(order);
        }
        return orderList;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(CHEESE_CAKES, DONUTS, VANILLA_CAKE, CHOCOLATE_COOKIES));
    }

}
