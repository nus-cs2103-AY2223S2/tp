package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author chongweiguan-reused
public class OrderQuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderQuantity(null));
    }

    @Test
    public void constructor_invalidOrderQuantity_throwsIllegalArgumentException() {
        String invalidOrderQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new OrderQuantity(invalidOrderQuantity));
    }

    @Test
    public void isValidQuantity() {
        OrderQuantity orderQuantity = new OrderQuantity("123");

        assertTrue(Integer.toString(orderQuantity.hashCode()).equals(Integer.toString(orderQuantity.hashCode())));
        assertTrue(orderQuantity.toString().equals("123"));


        // null phone number
        assertThrows(NullPointerException.class, () -> OrderQuantity.isValidQuantity(null));

        // invalid phone numbers
        assertFalse(OrderQuantity.isValidQuantity("")); // empty string
        assertFalse(OrderQuantity.isValidQuantity(" ")); // spaces only
        assertFalse(OrderQuantity.isValidQuantity("9999")); // more than 3 numbers
        assertFalse(OrderQuantity.isValidQuantity("phone")); // non-numeric
        assertFalse(OrderQuantity.isValidQuantity("9po")); // alphabets within digits
        assertFalse(OrderQuantity.isValidQuantity("9 2")); // spaces within digits

        // valid phone numbers
        assertTrue(OrderQuantity.isValidQuantity("123")); // exactly 3 numbers
        assertTrue(OrderQuantity.isValidQuantity("1")); // 1 number
        assertTrue(OrderQuantity.isValidQuantity("12")); // 2 numbers

    }

    @Test
    public void getOrderQuantityTest() {
        OrderQuantity orderQuantity = new OrderQuantity("123");
        assertEquals(123, orderQuantity.getOrderQuantity());
    }
    //@@author

}
