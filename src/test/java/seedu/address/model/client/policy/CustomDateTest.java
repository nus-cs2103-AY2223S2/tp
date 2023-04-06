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
        assertFalse(CustomDate.isValidDate("01/01/2022"));
    }

    @Test
    public void isValidDate_validInput_returnsTrue() {
        assertTrue(CustomDate.isValidDate("01.01.2022"));
        assertTrue(CustomDate.isValidDate("31.12.2022"));
        assertTrue(CustomDate.isValidDate("29.02.2024"));
    }
}
