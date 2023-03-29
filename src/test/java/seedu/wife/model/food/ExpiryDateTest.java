package seedu.wife.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.model.food.foodvalidator.ExpiryDateValidator.isValidDateFormat;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertThrows(NullPointerException.class, () -> isValidDateFormat(null));

        // invalid ExpiryDatees
        assertFalse(isValidDateFormat("")); // empty string
        assertFalse(isValidDateFormat(" ")); // spaces only

        // valid ExpiryDatees
        assertTrue(isValidDateFormat("13-11-2024"));
        assertTrue(isValidDateFormat("13-11-2026")); // long ExpiryDate
    }
}
