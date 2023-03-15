package seedu.wife.commons.core.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.wife.model.food.Quantity;

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
        // assertThrows(NullPointerException.class, () -> Quantity.isValid(null));

        // blank Quantity
        assertFalse(Quantity.isValid("")); // empty string
        assertFalse(Quantity.isValid(" ")); // spaces only

        // invalid parts
        assertFalse(Quantity.isValid("0")); // 0 item should not be recorded
        assertFalse(Quantity.isValid("-1")); // negative value
        assertFalse(Quantity.isValid("-13")); // negative value

        // valid Quantity
        assertTrue(Quantity.isValid("1"));
        assertTrue(Quantity.isValid("3"));
        assertTrue(Quantity.isValid("69"));
    }
}
