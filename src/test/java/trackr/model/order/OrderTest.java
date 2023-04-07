package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
import static trackr.testutil.TypicalCustomer.AMY;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;
import static trackr.testutil.TypicalOrders.CUPCAKE_O;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import trackr.model.menu.MenuItem;
import trackr.model.person.Customer;
import trackr.testutil.CustomerBuilder;
import trackr.testutil.MenuItemBuilder;
import trackr.testutil.OrderBuilder;

public class OrderTest {

    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(CHOCOLATE_COOKIES_O.isSameItem(CHOCOLATE_COOKIES_O));

        // null -> returns false
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(null));

        // same name, same deadline all other attributes different -> returns true
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                                    .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
        assertTrue(CHOCOLATE_COOKIES_O.isSameItem(editedOrder));

        // different order item, all other attributes same -> returns false
        editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                              .withOrderItem(CUPCAKE_M).build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedOrder));

        // different deadline, all other attributes same -> returns false
        editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                              .withOrderDeadline("02/01/2024").build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedOrder));

        // name differs in case, all other attributes same -> returns false
        Order editedChocolateCookies = new OrderBuilder(CHOCOLATE_COOKIES_O)
                                               .withOrderItem(new MenuItemBuilder()
                                                                      .withItemName("chocolate cookies")
                                                                      .build())
                                               .build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedChocolateCookies));

        // Order item name has trailing spaces, all other attributes same -> returns false
        String orderItemWithTrailingSpaces = VALID_ORDER_NAME_CHOCOLATE_COOKIES + " ";
        editedChocolateCookies = new OrderBuilder(CHOCOLATE_COOKIES_O)
                                         .withOrderItem(new MenuItemBuilder()
                                                                .withItemName(orderItemWithTrailingSpaces)
                                                                .build())
                                         .build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedChocolateCookies));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order aliceCopy = new OrderBuilder(CHOCOLATE_COOKIES_O).build();
        assertEquals(CHOCOLATE_COOKIES_O, aliceCopy);

        // same object -> returns true
        assertEquals(CHOCOLATE_COOKIES_O, CHOCOLATE_COOKIES_O);

        // null -> returns false
        assertNotEquals(null, CHOCOLATE_COOKIES_O);

        // different type -> returns false
        assertNotEquals(5, CHOCOLATE_COOKIES_O);

        // different person -> returns false
        assertNotEquals(CHOCOLATE_COOKIES_O, CUPCAKE_O);

        // different item -> returns false
        Order editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderItem(CUPCAKE_M).build();
        assertNotEquals(CHOCOLATE_COOKIES_O, editedAlice);

        // different deadline -> returns false
        editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderDeadline("02/02/2025").build();
        assertNotEquals(CHOCOLATE_COOKIES_O, editedAlice);

        // different email -> returns false
        editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderQuantity("444").build();
        assertNotEquals(CHOCOLATE_COOKIES_O, editedAlice);

        // different address -> returns false
        editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("I").build();
        assertNotEquals(CHOCOLATE_COOKIES_O, editedAlice);

        assertEquals(CHOCOLATE_COOKIES_O.toString(), CHOCOLATE_COOKIES_O.toString());
    }

    @Test
    public void getTotalProfit() {
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemPrice("10")
                                    .withItemCost("5")
                                    .build();

        Order order = new OrderBuilder().withOrderQuantity("10").withOrderItem(menuItem).build();

        assertEquals(50.0, order.getTotalProfit().getValue());

        order = new OrderBuilder().withOrderQuantity("0").withOrderItem(menuItem).build();

        assertEquals(0.0, order.getTotalProfit().getValue());

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemCost("10")
                                          .build();

        order = new OrderBuilder().withOrderQuantity("10").withOrderItem(editedMenuItem).build();

        assertEquals(0.0, order.getTotalProfit().getValue());
        assertNotEquals(50.0, order.getTotalProfit().getValue());
    }

    @Test
    public void getTotalCost() {
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemCost("5")
                                    .build();

        Order order = new OrderBuilder().withOrderQuantity("10").withOrderItem(menuItem).build();

        assertEquals(50.0, order.getTotalCost().getValue());

        order = new OrderBuilder().withOrderQuantity("0").withOrderItem(menuItem).build();

        assertEquals(0.0, order.getTotalCost().getValue());

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemCost("0")
                                          .build();

        order = new OrderBuilder().withOrderQuantity("10").withOrderItem(editedMenuItem).build();

        assertEquals(0.0, order.getTotalCost().getValue());
        assertNotEquals(50.0, order.getTotalCost().getValue());
    }

    @Test
    public void getTotalRevenue() {
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemPrice("5")
                                    .build();

        Order order = new OrderBuilder().withOrderQuantity("10").withOrderItem(menuItem).build();

        assertEquals(50.0, order.getTotalRevenue().getValue());

        order = new OrderBuilder().withOrderQuantity("0").withOrderItem(menuItem).build();

        assertEquals(0.0, order.getTotalRevenue().getValue());

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemPrice("0")
                                          .build();

        order = new OrderBuilder().withOrderQuantity("10").withOrderItem(editedMenuItem).build();

        assertEquals(0.0, order.getTotalRevenue().getValue());

        assertNotEquals(50.0, order.getTotalRevenue().getValue());
    }

    @Test
    public void totalProfit_equals_totalRevenueMinusCost() {
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemPrice("10")
                                    .withItemCost("5")
                                    .build();

        Order order = new OrderBuilder().withOrderQuantity("10").withOrderItem(menuItem).build();

        double totalRevenue = order.getTotalRevenue().getValue();
        double totalCost = order.getTotalCost().getValue();

        assertEquals(50.0, totalRevenue - totalCost);

        order = new OrderBuilder().withOrderQuantity("0").withOrderItem(menuItem).build();

        totalRevenue = order.getTotalRevenue().getValue();
        totalCost = order.getTotalCost().getValue();

        assertEquals(0.0, totalRevenue - totalCost);

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemCost("10")
                                          .build();

        order = new OrderBuilder().withOrderQuantity("10").withOrderItem(editedMenuItem).build();

        totalRevenue = order.getTotalRevenue().getValue();
        totalCost = order.getTotalCost().getValue();

        assertEquals(0.0, totalRevenue - totalCost);
        assertNotEquals(50.0, totalRevenue - totalCost);
    }

    @Test
    public void hashCode_success() {
        OrderName orderName = new OrderName("Test");
        OrderDeadline orderDeadline = new OrderDeadline("01/01/2023");
        OrderStatus orderStatus = new OrderStatus("D");
        OrderQuantity orderQuantity = new OrderQuantity("10");
        Customer customer = new CustomerBuilder(AMY).build();

        int hashCode = Objects.hash(customer, orderName, orderDeadline, orderStatus, orderQuantity);
        assertEquals(hashCode, new Order(orderName, orderDeadline, orderStatus, orderQuantity, customer).hashCode());
    }

}
