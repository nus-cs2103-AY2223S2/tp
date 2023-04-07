package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import trackr.model.menu.MenuItem;
import trackr.testutil.MenuItemBuilder;

public class OrderUtilTest {

    @Test
    public void getTotalProfit() {
        OrderQuantity orderQuantity = new OrderQuantity("10");
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemPrice("10")
                                    .withItemCost("5")
                                    .build();

        assertEquals(50.0, OrderUtil.getTotalProfit(orderQuantity, menuItem).getValue());

        OrderQuantity zeroOrderQuantity = new OrderQuantity("0");

        assertEquals(0.0, OrderUtil.getTotalProfit(zeroOrderQuantity, menuItem).getValue());

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemCost("10")
                                          .build();
        assertEquals(0.0, OrderUtil.getTotalProfit(orderQuantity, editedMenuItem).getValue());

        assertNotEquals(50.0, OrderUtil.getTotalProfit(orderQuantity, editedMenuItem).getValue());
    }

    @Test
    public void getTotalCost() {
        OrderQuantity orderQuantity = new OrderQuantity("10");
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemCost("5")
                                    .build();

        assertEquals(50.0, OrderUtil.getTotalCost(orderQuantity, menuItem).getValue());

        OrderQuantity zeroOrderQuantity = new OrderQuantity("0");

        assertEquals(0.0, OrderUtil.getTotalCost(zeroOrderQuantity, menuItem).getValue());

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemCost("0")
                                          .build();
        assertEquals(0.0, OrderUtil.getTotalCost(orderQuantity, editedMenuItem).getValue());

        assertNotEquals(50.0, OrderUtil.getTotalCost(orderQuantity, editedMenuItem).getValue());
    }

    @Test
    public void getTotalRevenue() {
        OrderQuantity orderQuantity = new OrderQuantity("10");
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemPrice("5")
                                    .build();

        assertEquals(50.0, OrderUtil.getTotalRevenue(orderQuantity, menuItem).getValue());

        OrderQuantity zeroOrderQuantity = new OrderQuantity("0");

        assertEquals(0.0, OrderUtil.getTotalRevenue(zeroOrderQuantity, menuItem).getValue());

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemPrice("0")
                                          .build();
        assertEquals(0.0, OrderUtil.getTotalRevenue(orderQuantity, editedMenuItem).getValue());

        assertNotEquals(50.0, OrderUtil.getTotalRevenue(orderQuantity, editedMenuItem).getValue());
    }

    @Test
    public void totalProfit_equals_totalRevenueMinusCost() {
        OrderQuantity orderQuantity = new OrderQuantity("10");
        MenuItem menuItem = new MenuItemBuilder()
                                    .withItemPrice("10")
                                    .withItemCost("5")
                                    .build();

        double totalRevenue = OrderUtil.getTotalRevenue(orderQuantity, menuItem).getValue();
        double totalCost = OrderUtil.getTotalCost(orderQuantity, menuItem).getValue();

        assertEquals(50.0, totalRevenue - totalCost);

        OrderQuantity zeroOrderQuantity = new OrderQuantity("0");

        totalRevenue = OrderUtil.getTotalRevenue(zeroOrderQuantity, menuItem).getValue();
        totalCost = OrderUtil.getTotalCost(zeroOrderQuantity, menuItem).getValue();

        assertEquals(0.0, totalRevenue - totalCost);

        MenuItem editedMenuItem = new MenuItemBuilder(menuItem)
                                          .withItemCost("10")
                                          .build();

        totalRevenue = OrderUtil.getTotalRevenue(orderQuantity, editedMenuItem).getValue();
        totalCost = OrderUtil.getTotalCost(orderQuantity, editedMenuItem).getValue();

        assertEquals(0.0, totalRevenue - totalCost);

        assertNotEquals(50.0, totalRevenue - totalCost);
    }

}
