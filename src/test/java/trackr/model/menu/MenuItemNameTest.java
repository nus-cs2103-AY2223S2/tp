package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ItemName.isValidName(null));

        // invalid name
        assertFalse(ItemName.isValidName(""));
        assertFalse(ItemName.isValidName(" "));
        assertFalse(ItemName.isValidName("^"));
        assertFalse(ItemName.isValidName("cookie*"));

        // valid name
        assertTrue(ItemName.isValidName("chocolate cookies"));
        assertTrue(ItemName.isValidName("Chocolate Cookies"));
        assertTrue(ItemName.isValidName("12345"));
        assertTrue(ItemName.isValidName("2nd Best Burger"));
        assertTrue(ItemName.isValidName("Maggie Goreng with egg and chicken"));
    }

    @Test
    public void equals() {
        ItemName name = new ItemName("Bread");
        ItemName differentName = new ItemName("Pudding");

        assertTrue(name.equals(name));
        assertTrue(name.equals(new ItemName("Bread")));

        assertFalse(name.equals(null));
        assertFalse(name.equals(differentName));
        assertFalse(name.equals(1));
    }
}
