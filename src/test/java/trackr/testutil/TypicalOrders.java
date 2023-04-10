package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_ADDRESS;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_NAME;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_PHONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2024;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_ONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
import static trackr.testutil.TypicalMenuItems.CARGO_PANTS;
import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;
import static trackr.testutil.TypicalMenuItems.NIKE_CAP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.OrderList;
import trackr.model.order.Order;

//@@author chongweiguan-reused
/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order CHOCOLATE_COOKIES_O = new OrderBuilder().withOrderItem(CHOCOLATE_COOKIE_M)
            .withOrderDeadline("01/01/2024")
            .withOrderStatus("N")
            .withOrderQuantity("3")
            .withCustomerName("John Doe")
            .withCustomerPhone("98765432")
            .withCustomerAddress("123 Main Street").build();

    public static final Order CUPCAKE_O = new OrderBuilder().withOrderItem(CUPCAKE_M)
            .withOrderDeadline("12/12/2024")
            .withOrderStatus("N")
            .withOrderQuantity("10")
            .withCustomerName("Mary Jane")
            .withCustomerPhone("12345678")
            .withCustomerAddress("321 Jurong Street").build();

    public static final Order CARGO_PANTS_O = new OrderBuilder().withOrderItem(CARGO_PANTS)
            .withOrderDeadline("31/12/2024")
            .withOrderStatus("D")
            .withOrderQuantity("100")
            .withCustomerName("Alex Lim")
            .withCustomerPhone("24681357")
            .withCustomerAddress("456 Bedok Street").build();

    public static final Order NIKE_CAP_O = new OrderBuilder().withOrderItem(NIKE_CAP)
            .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
            .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
            .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
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
            orderList.addItem(order);
        }
        return orderList;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(CHOCOLATE_COOKIES_O, CUPCAKE_O, CARGO_PANTS_O, NIKE_CAP_O));
    }
    //@@author

}
