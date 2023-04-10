package trackr.testutil;

import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;

/**
 * A utility class to help with building Task objects.
 */
//@@author changgittyhub
public class MenuItemBuilder {

    public static final String DEFAULT_ITEM_NAME = "Chocolate cookies";
    public static final String DEFAULT_ITEM_PRICE = "5.00";
    public static final String DEFAULT_ITEM_COST = "1.00";

    private ItemName itemName;
    private ItemSellingPrice itemPrice;
    private ItemCost itemCost;
    private ItemProfit itemProfit;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public MenuItemBuilder() {
        itemName = new ItemName(DEFAULT_ITEM_NAME);
        itemPrice = new ItemSellingPrice(DEFAULT_ITEM_PRICE);
        itemCost = new ItemCost(DEFAULT_ITEM_COST);
        itemProfit = new ItemProfit(itemPrice, itemCost);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public MenuItemBuilder(MenuItem menuItemToCopy) {
        itemName = menuItemToCopy.getItemName();
        itemPrice = menuItemToCopy.getItemPrice();
        itemCost = menuItemToCopy.getItemCost();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public MenuItemBuilder withItemName(String itemName) {
        this.itemName = new ItemName(itemName);
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code Task} that we are building.
     */
    public MenuItemBuilder withItemCost(String itemCost) {
        this.itemCost = new ItemCost(itemCost);
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code Task} that we are building.
     */
    public MenuItemBuilder withItemPrice(String itemPrice) {
        this.itemPrice = new ItemSellingPrice(itemPrice);
        return this;
    }

    public MenuItem build() {
        return new MenuItem(itemName, itemPrice, itemCost);
    }

}
