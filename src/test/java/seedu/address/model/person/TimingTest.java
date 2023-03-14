package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimingTest {
    @Test
    public void constructor_bothNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timing(null, null));
    }

    @Test
    public void constructor_startNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timing("12-03-2023 12:00", null));
    }

    public void constructor_endNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timing(null, "12-03-2023 12:00"));
    }

    @Test
    public void constructor_invalidTimeStart_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Timing(invalidTime, "12-03-2023 12:00"));
    }

    @Test
    public void constructor_invalidTimeEnd_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Timing("12-03-2023 12:00", invalidTime));
    }

    @Test
    public void isValidTiming() {
        // null time(s)
        assertFalse(Timing.isValidTiming(null, null));
        assertFalse(Timing.isValidTiming("12-03-2023 12:00", null));
        assertFalse(Timing.isValidTiming(null, "12-03-2023 12:00"));

        // invalid times
        // empty string
        assertFalse(Timing.isValidTiming("", "12-03-2023 12:00"));
        assertFalse(Timing.isValidTiming("12-03-2023 12:00", ""));

        // spaces only
        assertFalse(Timing.isValidTiming("12-03-2023 12:00", " "));
        assertFalse(Timing.isValidTiming(" ", "12-03-2023 12:00"));

        // different symbols
        assertFalse(Timing.isValidTiming("12-03-2023 12:00", "12:03:2023 12-00"));
        assertFalse(Timing.isValidTiming("12:03:2023 12-00", "12-03-2023 12:00"));

        // non-existent times
        assertFalse(Timing.isValidTiming("31-02-2023 12:00", "12-03-2023 12:00"));
        assertFalse(Timing.isValidTiming("12-01-2023 12:00", "31-02-2023 12:00"));
        assertFalse(Timing.isValidTiming("15-02-2023 25:00", "12-03-2023 12:00"));
        assertFalse(Timing.isValidTiming("12-01-2023 12:00", "15-02-2023 25:00"));

        // valid times
        assertTrue(Timing.isValidTiming("12-03-2023 12:00", "12-03-2023 12:00"));
    }
}
