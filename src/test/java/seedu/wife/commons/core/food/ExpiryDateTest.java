package seedu.wife.commons.core.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.wife.model.food.ExpiryDate;

public class ExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(invalidExpiryDate));
    }

    @Test
    public void isValid() {
        // null ExpiryDate
        assertThrows(NullPointerException.class, () -> ExpiryDate.isValid(null));

        // invalid ExpiryDatees
        assertFalse(ExpiryDate.isValid("")); // empty string
        assertFalse(ExpiryDate.isValid(" ")); // spaces only

        // valid ExpiryDatees
        assertTrue(ExpiryDate.isValid("13-11-2024"));
        assertTrue(ExpiryDate.isValid("13-11-2026")); // long ExpiryDate
    }
}
