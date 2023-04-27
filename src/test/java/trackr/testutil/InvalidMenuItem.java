package trackr.testutil;

import trackr.model.menu.MenuItem;

//@@author arkarsg-unused
/**
 * An additional utility class that creates invalid {@code MenuItem}
 *
 * However, since {@code TypicalMenuItems} is accessed statically, creating invalid menu items
 * in {@code TypicalMenuItems} will result in {@code IllegalArgumentException}
 * from MenuItem checkArgument and will not be able to access other static methods such as {@see getTypicalMenu}
 * Wrapper methods are necessary as IllegalArgumentException should only be thrown from relevant invalid field
 * @see TypicalMenuItems
 */
public class InvalidMenuItem {
    /**
     * Wrapper method to Create an invalid menu item with invalid name
     * @return invalidMenuItem
     * @throws IllegalArgumentException
     */
    public static MenuItem createInvalidNameItem() throws IllegalArgumentException {
        final MenuItem invalidNameItem = new MenuItemBuilder()
            .withItemName(" ")
            .build();
        return invalidNameItem;
    }

    /**
     * Wrapper method to Create an invalid menu item with invalid price
     * @return invalidMenuItem
     * @throws IllegalArgumentException
     */
    public static MenuItem createInvalidPriceItem() throws IllegalArgumentException {
        final MenuItem invalidPriceItem = new MenuItemBuilder()
            .withItemPrice(" ")
            .build();
        return invalidPriceItem;
    }

    /**
     * Wrapper method to Create an invalid menu item with invalid cost
     * @return invalidMenuItem
     * @throws IllegalArgumentException
     */
    public static MenuItem createInvalidCostItem() throws IllegalArgumentException {
        final MenuItem invalidCostItem = new MenuItemBuilder()
            .withItemCost(" ")
            .build();
        return invalidCostItem;
    }
}
