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
        assertThrows(NullPointerException.class, () -> Quantity.isValid(null));

        // blank Quantity
        assertFalse(Quantity.isValid("")); // empty string
        assertFalse(Quantity.isValid(" ")); // spaces only

        // invalid parts
        assertFalse(Quantity.isValid("0")); // invalid domain name
        assertFalse(Quantity.isValid("-1")); // underscore in domain name
        assertFalse(Quantity.isValid("-13")); // spaces in local part

        // valid Quantity
        assertTrue(Quantity.isValid("1")); // underscore in local part
        assertTrue(Quantity.isValid("3")); // period in local part
        assertTrue(Quantity.isValid("69")); // '+' symbol in local part
    }
}
