package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;

import trackr.model.menu.MenuItem;

/**
 * An additional utility class that creates invalid {@code MenuItem}
 * 
 * @see TypicalMenuItems
 * However, since {@code TypicalMenuItems} is accessed statically, creating invalid menu items
 * in {@code TypicalMenuItems} will result in {@code IllegalArgumentException} 
 * from MenuItem checkArgument and will not be able to access other static methods such as {@see getTypicalMenu}
 */
public class InvalidMenuItem {
    /**
     * Wrapper methods are necessary as IllegalArgumentException should only be thrown from
     * relevant invalid field 
     */

    /**
     * Wrapper method to Create an invalid menu item with invalid name
     * @return invalidMenuItem
     * @throws IllegalArgumentException
     */
    public static MenuItem createInvalidNameItem() throws IllegalArgumentException {
        final MenuItem invalidNameItem = new MenuItemBuilder()
            .withItemName(" ")
            .withItemPrice("5.00")
            .withItemCost("1.00")
            .build();
        return invalidNameItem;
    }
    
    public static MenuItem createInvalidPriceItem() throws IllegalArgumentException {
        final MenuItem invalidPriceItem = new MenuItemBuilder()
            .withItemName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
            .withItemPrice(" ")
            .withItemCost("1.00")
            .build();
        return invalidPriceItem;
    }

    public static MenuItem createInvalidCostItem() throws IllegalArgumentException {
        final MenuItem invalidCostItem = new MenuItemBuilder()
            .withItemName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
            .withItemPrice("5.00")
            .withItemCost(" ")
            .build();
        return invalidCostItem;
    }
}
