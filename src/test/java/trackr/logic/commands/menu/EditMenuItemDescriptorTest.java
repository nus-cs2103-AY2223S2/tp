package trackr.logic.commands.menu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_CHOCOLATE_COOKIE_M;
import static trackr.logic.commands.CommandTestUtil.DESC_CUPCAKES_M;
import static trackr.logic.commands.CommandTestUtil.VALID_MENU_ITEM_NAME_CUPCAKES;

import org.junit.jupiter.api.Test;

import trackr.model.menu.MenuItemDescriptor;
import trackr.testutil.MenuItemDescriptorBuilder;

public class EditMenuItemDescriptorTest {

    @Test
    public void equals() {
        MenuItemDescriptor descriptorWithSameValues = new MenuItemDescriptor(DESC_CHOCOLATE_COOKIE_M);
        assertTrue(DESC_CHOCOLATE_COOKIE_M.equals(descriptorWithSameValues));

        assertTrue(DESC_CHOCOLATE_COOKIE_M.equals(DESC_CHOCOLATE_COOKIE_M));
        assertFalse(DESC_CHOCOLATE_COOKIE_M.equals(null));
        assertFalse(DESC_CHOCOLATE_COOKIE_M.equals(5));
        assertFalse(DESC_CHOCOLATE_COOKIE_M.equals(DESC_CUPCAKES_M));

        MenuItemDescriptor editedCookies = new MenuItemDescriptorBuilder(DESC_CHOCOLATE_COOKIE_M)
                                                .withName(VALID_MENU_ITEM_NAME_CUPCAKES)
                                                .build();
        assertFalse(DESC_CHOCOLATE_COOKIE_M.equals(editedCookies));

        editedCookies = new MenuItemDescriptorBuilder(DESC_CHOCOLATE_COOKIE_M)
                            .withPrice("20.00")
                            .build();
        assertFalse(DESC_CHOCOLATE_COOKIE_M.equals(editedCookies));

        editedCookies = new MenuItemDescriptorBuilder(DESC_CHOCOLATE_COOKIE_M)
                            .withCost("4.00")
                            .build();
        assertFalse(DESC_CHOCOLATE_COOKIE_M.equals(editedCookies));
    }
}
