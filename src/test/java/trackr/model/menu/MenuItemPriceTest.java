package trackr.model.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemPriceTest {

    @Test
    public void stringConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemPrice((String) null));
    }

    @Test
    public void doubleConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemPrice((Double) null));
    }

    @Test
    public void constructor_invalidStringItemPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemPrice(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> ItemPrice.isValidPrice(null));

        // invalid price - String
        assertFalse(ItemPrice.isValidPrice(""));
        assertFalse(ItemPrice.isValidPrice(" "));
        assertFalse(ItemPrice.isValidPrice("NaN")); // non-numeric
        assertFalse(ItemPrice.isValidPrice("90p")); // alphabets within digits
        assertFalse(ItemPrice.isValidPrice("10 10")); // contains spaces
        assertFalse(ItemPrice.isValidPrice("10,10")); // comma instead of decimal
        assertFalse(ItemPrice.isValidPrice("10.000")); // more than 2 decimal places
        assertFalse(ItemPrice.isValidPrice("-10.00")); // more than 2 decimal places


        // valid price
        assertTrue(ItemPrice.isValidPrice("1")); // no decimal places
        assertTrue(ItemPrice.isValidPrice("1.0")); // exactly 1 decimal places
        assertTrue(ItemPrice.isValidPrice("1.00")); // exactly 2 decimal places
        assertTrue(ItemPrice.isValidPrice("999993123123123131322131239.00")); //large number
    }

    @Test
    public void equals() {
        ItemPrice itemPrice = new ItemPrice("10.00");
        ItemPrice differentItemPrice = new ItemPrice("21.00");

        assertEquals(itemPrice, itemPrice); // same object

        // same value -> true
        assertEquals(itemPrice, new ItemPrice("10.00"));
        assertEquals(itemPrice, new ItemPrice("10"));
        assertEquals(itemPrice, new ItemPrice("10.0"));

        assertEquals(itemPrice, new ItemPrice(10.00));
        assertEquals(itemPrice, new ItemPrice(10.0));

        // different type -> false
        assertNotEquals(null, itemPrice);
        assertNotEquals(1, itemPrice);

        // different value -> false
        assertNotEquals(itemPrice, differentItemPrice);
    }

    @Test
    public void toStringTest() {
        assertEquals("10.00", new ItemPrice("10.00").toString());
        assertEquals("10.00", new ItemPrice("10.0").toString());
        assertEquals("10.00", new ItemPrice("10").toString());

        assertNotEquals("10", new ItemPrice("10.00").toString());
    }

    @Test
    public void getFormattedValueTest() {
        assertEquals("10.00", new ItemPrice("10.00").getFormattedValue());
        assertEquals("10.00", new ItemPrice("10.0").getFormattedValue());
        assertEquals("10.00", new ItemPrice("10").getFormattedValue());

        assertNotEquals("10", new ItemPrice("10.00").getFormattedValue());
    }

    @Test
    public void hashCode_success() {
        Double val = 10.0;
        int hashCode = Double.hashCode(val);
        assertEquals(new ItemPrice(val).hashCode(), hashCode);
    }

}
