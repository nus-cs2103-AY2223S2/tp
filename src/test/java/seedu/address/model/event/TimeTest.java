package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time(s)
        assertFalse(Time.isValidTime(null));

        // invalid time
        // empty string
        assertFalse(Time.isValidTime(""));

        // spaces only
        assertFalse(Time.isValidTime(" "));

        // different symbols
        assertFalse(Time.isValidTime("12:03:2023 12-00"));

        // non-existent times
        assertFalse(Time.isValidTime("31-02-2023 12:00"));
        assertFalse(Time.isValidTime("15-02-2023 25:00"));

        // valid times
        assertTrue(Time.isValidTime("12-03-2023 12:00"));
    }
}
