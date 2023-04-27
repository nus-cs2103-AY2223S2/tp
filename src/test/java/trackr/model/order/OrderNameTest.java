package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderNameTest {

    //@@author chongweiguan-reused
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidOrderName = "";
        assertThrows(IllegalArgumentException.class, () -> new OrderName(invalidOrderName));
    }
    //@@author

    @Test
    public void isValidOrderName() {
        //@@author HmuuMyatMoe-reused
        //Reused from AB3 with minor modifications
        // null task name
        assertThrows(NullPointerException.class, () -> OrderName.isValidName(null));

        // invalid task name
        assertFalse(OrderName.isValidName("")); // empty string
        assertFalse(OrderName.isValidName(" ")); // spaces only
        assertFalse(OrderName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(OrderName.isValidName("bake*")); // contains non-alphanumeric characters

        // valid task name
        assertTrue(OrderName.isValidName("Chocolate Chip")); // alphabets only
        assertTrue(OrderName.isValidName("12345")); // numbers only
        assertTrue(OrderName.isValidName("10 Brownies")); // alphanumeric characters
        assertTrue(OrderName.isValidName("Egg Tart")); // with capital letters
        assertTrue(OrderName.isValidName("Brownies with extra corn flakes and chcocolate")); // long names
        //@@author
    }

    //@@author chongweiguan-reused
    @Test
    public void toStringTest() {
        String expectedOrderName = "Chocolate Cookies";
        assertEquals(expectedOrderName, new OrderName("Chocolate Cookies").toString());
    }

    @Test
    public void equals() {
        OrderName orderName = new OrderName("Sort Inventory");
        OrderName differentOrderName = new OrderName("Sort");

        assertTrue(orderName.equals(orderName)); //same object
        assertTrue(orderName.equals(new OrderName("Sort Inventory"))); //same task name

        assertFalse(orderName.equals(null)); //null
        assertFalse(orderName.equals(differentOrderName)); //different task name
        assertFalse(orderName.equals(1)); //different type
    }
    //@@author
}
