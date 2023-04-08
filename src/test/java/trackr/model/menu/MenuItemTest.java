package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;
import static trackr.testutil.TypicalSuppliers.BOB;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import trackr.testutil.MenuItemBuilder;

public class MenuItemTest {

    @Test
    public void isSameMenuItem() {
        // same object -> returns true
        assertTrue(CHOCOLATE_COOKIE_M.isSameItem(CHOCOLATE_COOKIE_M));

        // null -> returns false
        assertFalse(CHOCOLATE_COOKIE_M.isSameItem(null));

        // same name, all other attributes different -> returns true
        MenuItem editedChocolateCookie = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                                 .withItemPrice("99.00")
                                                 .withItemCost("99.00")
                                                 .build();
        assertTrue(CHOCOLATE_COOKIE_M.isSameItem(editedChocolateCookie));

        // different name, all other attributes same -> returns false
        editedChocolateCookie = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                        .withItemName("Cupcake")
                                        .build();
        assertFalse(BOB.isSameItem(editedChocolateCookie));
    }

    @Test
    public void equals() {
        // same values -> returns true
        MenuItem chocolateCookieCopy = new MenuItemBuilder(CHOCOLATE_COOKIE_M).build();
        assertEquals(CHOCOLATE_COOKIE_M, chocolateCookieCopy);

        // same object -> returns true
        assertEquals(CHOCOLATE_COOKIE_M, CHOCOLATE_COOKIE_M);

        // null -> returns false
        assertNotEquals(null, CHOCOLATE_COOKIE_M);

        // different type -> returns false
        assertNotEquals(5, CHOCOLATE_COOKIE_M);

        // different supplier -> returns false
        assertNotEquals(CHOCOLATE_COOKIE_M, CUPCAKE_M);

        // different name -> returns false
        MenuItem editedChocolateCookie = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                                 .withItemName("Cupcake")
                                                 .build();
        assertNotEquals(CHOCOLATE_COOKIE_M, editedChocolateCookie);

        // different price -> returns false
        editedChocolateCookie = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                        .withItemPrice("99.00")
                                        .build();
        assertNotEquals(CHOCOLATE_COOKIE_M, editedChocolateCookie);

        // different cost -> returns false
        editedChocolateCookie = new MenuItemBuilder(CHOCOLATE_COOKIE_M)
                                        .withItemCost("99.00")
                                        .build();
        assertNotEquals(CHOCOLATE_COOKIE_M, editedChocolateCookie);
    }

    @Test
    public void hashCode_success() {
        ItemName itemName = new ItemName("Test");
        ItemSellingPrice itemPrice = new ItemSellingPrice("1.00");
        ItemCost itemCost = new ItemCost("1.00");
        int hashCode = Objects.hash(itemName, itemPrice, itemCost);

        assertEquals(hashCode, new MenuItem(itemName, itemPrice, itemCost).hashCode());
    }

}
