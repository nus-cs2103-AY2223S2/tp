package trackr.model.order;

import trackr.model.menu.MenuItem;
import trackr.model.menu.ItemPrice;

public class OrderUtil { 
    public static ItemPrice getTotalProfit(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double profit =  q * item.getItemProfit().getValue();
        return new ItemPrice(profit);
    }

    public static ItemPrice getTotalCost(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double cost =  q * item.getItemCost().getValue();
        return new ItemPrice(cost);
    }

    public static ItemPrice getTotalRevenue(OrderQuantity qty, MenuItem item) {
        int q = qty.getOrderQuantity();
        Double revenue =  q * item.getItemPrice().getValue();
        return new ItemPrice(revenue);
    }
}
