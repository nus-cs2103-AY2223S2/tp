package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CUPCAKES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.model.Menu;
import trackr.model.menu.MenuItem;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalMenuItems {

    public static final MenuItem CHOCOLATE_COOKIE_M = new MenuItemBuilder()
            .withItemName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
            .withItemPrice("5")
            .withItemCost("2")
            .build();


    public static final MenuItem CUPCAKE_M = new MenuItemBuilder().withItemName(VALID_ORDER_NAME_CUPCAKES)
            .withItemPrice("3")
            .withItemCost("1")
            .build();

    public static final MenuItem HARLEY_SHIRT_M = new MenuItemBuilder().withItemName("Harley Davidson Shirt")
            .withItemPrice("55")
            .withItemCost("15")
            .build();


    // Manually added

    public static final MenuItem CARGO_PANTS = new MenuItemBuilder().withItemName("Cargo Pants")
            .withItemPrice("50")
            .withItemCost("10")
            .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final MenuItem NIKE_CAP = new MenuItemBuilder().withItemName("Nike Cap")
            .withItemPrice("45")
            .withItemCost("9")
            .build();

    public static final MenuItem INVALID_M = new MenuItemBuilder().withItemName("Invalid Itme")
            .withItemPrice(null)
            .withItemCost(null)
            .build();

    public static final String KEYWORD_MATCHING_BUY = "Buy"; // A keyword that matches BUY

    private TypicalMenuItems() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical tasks.
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
