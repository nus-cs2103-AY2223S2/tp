package trackr.model.order;

import trackr.model.menu.ItemPrice;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;

/**
 * Wrapper class to calculate financials based on Order and MenuItem
 */
public class OrderUtil {
    public static ItemProfit getTotalProfit(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double profit = q * item.getItemProfit().getValue();
        return new ItemProfit(profit);
    }

    public static ItemPrice getTotalCost(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double cost = q * item.getItemCost().getValue();
        return new ItemPrice(cost);
    }

    public static ItemSellingPrice getTotalRevenue(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double revenue = q * item.getItemPrice().getValue();
        return new ItemSellingPrice(revenue);
    }
}
