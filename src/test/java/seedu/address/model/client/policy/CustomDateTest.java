package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CustomDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new CustomDate(invalidDate));
    }

    @Test
    public void isValidDate_invalidDateFormat_throwsDateTimeParseException() {
        assertThrows(NumberFormatException.class, () -> CustomDate.isValidDate("2022/01/01"));
    }

    @Test
    public void isValidDate_validInput_returnsTrue() {
        assertTrue(CustomDate.isValidDate("01.01.2022"));
        assertTrue(CustomDate.isValidDate("31.12.2022"));
        assertTrue(CustomDate.isValidDate("29.02.2024"));
    }

    @Test
    public void isValidDate_invalidInput_returnsFalse() {
        assertFalse(CustomDate.isValidDate("29.02.2021")); // not a leap year
        String date = "31/04/2022";
        assertFalse(date.matches(CustomDate.VALIDATION_REGEX));

    }


}
