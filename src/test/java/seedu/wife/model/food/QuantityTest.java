package seedu.wife.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValid() {
        // null Quantity
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // blank Quantity
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only

        // invalid parts
        assertFalse(Quantity.isValidQuantity("0")); // 0 item should not be recorded
        assertFalse(Quantity.isValidQuantity("-1")); // negative value
        assertFalse(Quantity.isValidQuantity("-13")); // negative value

        // valid Quantity
        assertTrue(Quantity.isValidQuantity("1"));
        assertTrue(Quantity.isValidQuantity("3"));
        assertTrue(Quantity.isValidQuantity("69"));
    }
}
