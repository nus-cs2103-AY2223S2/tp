package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CUPCAKES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.Menu;
import trackr.model.menu.MenuItem;

/**
 * A utility class containing a list of {@code MenuItem} objects to be used in tests.
 */
//@@author changgittyhub
public class TypicalMenuItems {

    public static final MenuItem CHOCOLATE_COOKIE_M = new MenuItemBuilder()
            .withItemName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
            .withItemPrice("5.00")
            .withItemCost("2.00")
            .build();


    public static final MenuItem CUPCAKE_M = new MenuItemBuilder().withItemName(VALID_ORDER_NAME_CUPCAKES)
            .withItemPrice("3.00")
            .withItemCost("1.00")
            .build();

    public static final MenuItem HARLEY_SHIRT_M = new MenuItemBuilder().withItemName("Harley Davidson Shirt")
            .withItemPrice("55.00")
            .withItemCost("15.00")
            .build();


    // Manually added

    public static final MenuItem CARGO_PANTS = new MenuItemBuilder().withItemName("Cargo Pants")
            .withItemPrice("50.00")
            .withItemCost("10.00")
            .build();

    public static final MenuItem NIKE_CAP = new MenuItemBuilder().withItemName("Nike Cap")
            .withItemPrice("45.00")
            .withItemCost("9.00")
            .build();

    private TypicalMenuItems() {} // prevents instantiation

    /**
     * Returns an {@code menu} with all the typical menu items.
     */
    public static Menu getTypicalMenu() {
        Menu menu = new Menu();
        for (MenuItem menuItem : getTypicalMenuItem()) {
            menu.addItem(menuItem);
        }
        return menu;
    }

    public static List<MenuItem> getTypicalMenuItem() {
        return new ArrayList<>(
                Arrays.asList(CHOCOLATE_COOKIE_M, CUPCAKE_M, HARLEY_SHIRT_M));
    }
}
