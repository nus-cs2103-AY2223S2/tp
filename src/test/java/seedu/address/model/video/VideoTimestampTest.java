package seedu.address.model.video;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VideoTimestampTest {

    @Test
    public void constructor_validTimestamp_returnsVideoTimestamp() {
        VideoTimestamp timestamp = new VideoTimestamp("99:59:59");

        assertTrue(timestamp.hours == 99);
        assertTrue(timestamp.minutes == 59);
        assertTrue(timestamp.seconds == 59);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VideoTimestamp(null));
    }

    @Test
    public void constructor_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new VideoTimestamp("0"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void constructor_invalidRange_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new VideoTimestamp("00:60:60"),
                VideoTimestamp.MESSAGE_RANGE_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_validTimestamp_noExceptionsThrown() {
        VideoTimestamp.validateTimestamp("99:59:59");
    }

    @Test
    public void validateTimestamp_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VideoTimestamp.validateTimestamp(null),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_emptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp(""),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_notEnoughDigits_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:00:0"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:0:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("0:00:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_tooManyDigits_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("000:00:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:000:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:00:000"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_hasNonDigits_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:00:0a"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:0a:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("0a:00:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_notEnoughGroups_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_tooManyGroups_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:00:00:00"),
                VideoTimestamp.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_minutesExceedRange_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:60:00"),
                VideoTimestamp.MESSAGE_RANGE_CONSTRAINTS);
    }

    @Test
    public void validateTimestamp_secondsExceedRange_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> VideoTimestamp.validateTimestamp("00:00:60"),
                VideoTimestamp.MESSAGE_RANGE_CONSTRAINTS);
    }

    @Test
    public void equals() {
        String timestampStr = "99:59:59";
        VideoTimestamp timestamp = new VideoTimestamp(timestampStr);

        // same values -> returns true
        VideoTimestamp timestampCopy = new VideoTimestamp(timestampStr);

        assertTrue(timestamp.equals(timestampCopy));

        // same object -> returns true
        assertTrue(timestamp.equals(timestamp));

        // null -> returns false
        assertFalse(timestamp.equals(null));

        // different type -> returns false
        assertFalse(timestamp.equals(1));

        // different hours -> returns false
        VideoTimestamp timestampWithDifferentHours = new VideoTimestamp("00:59:59");
        assertFalse(timestamp.equals(timestampWithDifferentHours));

        // different minutes -> returns false
        VideoTimestamp timestampWithDifferentMinutes = new VideoTimestamp("99:00:59");
        assertFalse(timestamp.equals(timestampWithDifferentMinutes));

        // different seconds -> returns false
        VideoTimestamp timestampWithDifferentSeconds = new VideoTimestamp("99:59:00");
        assertFalse(timestamp.equals(timestampWithDifferentSeconds));
    }

}
