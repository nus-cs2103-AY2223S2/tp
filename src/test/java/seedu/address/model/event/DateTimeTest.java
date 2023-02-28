package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null date time
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid date time
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("2020-02-02 22:20")); // wrong sequence
        assertFalse(DateTime.isValidDateTime("02-21-2020 22:20")); // wrong sequence
        assertFalse(DateTime.isValidDateTime("02-02-2020 59:20")); // wrong sequence
        assertFalse(DateTime.isValidDateTime("02-50-2020 59:70")); // values out of range

        // valid date time
        assertTrue(DateTime.isValidDateTime("03-02-2020 20:40"));
    }
}
