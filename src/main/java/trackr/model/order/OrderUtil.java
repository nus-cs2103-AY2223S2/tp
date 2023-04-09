package trackr.model.order;

import trackr.model.menu.ItemPrice;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;

/**
 * Wrapper class to calculate financials based on Order and MenuItem.
 */
public class OrderUtil {

    /**
     * Calculates the total profit for the given order quantity and menu item.
     *
     * @param qty The order quantity.
     * @param item The menu item.
     * @return The total profit as an ItemProfit object.
     */
    public static ItemProfit getTotalProfit(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double profit = q * item.getItemProfit().getValue();
        return new ItemProfit(profit);
    }

    /**
     * Calculates the total cost for the given order quantity and menu item.
     *
     * @param qty The order quantity.
     * @param item The menu item.
     * @return The total cost as an ItemPrice object.
     */
    public static ItemPrice getTotalCost(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double cost = q * item.getItemCost().getValue();
        return new ItemPrice(cost);
    }

    /**
     * Calculates the total revenue for the given order quantity and menu item.
     *
     * @param qty The order quantity.
     * @param item The menu item.
     * @return The total revenue as an ItemSellingPrice object.
     */
    public static ItemSellingPrice getTotalRevenue(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double revenue = q * item.getItemPrice().getValue();
        return new ItemSellingPrice(revenue);
    }
}
