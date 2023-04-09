package seedu.address.model.commitment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.location.util.TypicalLocation.BARTLEY;
import static seedu.address.model.location.util.TypicalLocation.BRADDELL;
import static seedu.address.model.location.util.TypicalLocation.PUNGGOL;
import static seedu.address.model.location.util.TypicalLocation.SENGKANG;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;

import org.junit.jupiter.api.Test;

import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimeBlock;

public class CommitmentTest {

    private static final HourBlock HOUR_BLOCK = new HourBlock(TEN_AM, Day.TUESDAY);
    private static final Commitment COMMITMENT = new Commitment(BARTLEY, HOUR_BLOCK);

    @Test
    void constructor_validArgs_success() {
        assertDoesNotThrow(() -> new Commitment(PUNGGOL, HOUR_BLOCK));
    }

    @Test
    void constructor_timeBlock_success() {
        assertDoesNotThrow(() -> new Commitment(PUNGGOL,
                new TimeBlock(TEN_AM, TWELVE_PM, Day.WEDNESDAY)));
    }

    @Test
    void constructor_hourBlock_success() {
        assertDoesNotThrow(() -> new Commitment(PUNGGOL,
                new HourBlock(TWELVE_PM, Day.WEDNESDAY)));
    }

    @Test
    void constructor_locationNull_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> new Commitment(null, HOUR_BLOCK));
    }

    @Test
    void constructor_timePeriodNull_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> new Commitment(SENGKANG, null));
    }

    @Test
    void constructor_allNull_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> new Commitment(null, null));
    }

    @Test
    void equals_sameObject_true() {
        assertEquals(COMMITMENT, COMMITMENT);
    }

    @Test
    void equals_sameValues_true() {
        assertEquals(COMMITMENT, new Commitment(BARTLEY, HOUR_BLOCK));
    }

    @Test
    void equals_differentLocation_false() {
        assertNotEquals(COMMITMENT, new Commitment(BRADDELL, HOUR_BLOCK));
    }

    @Test
    void equals_differentTimePeriods_false() {
        assertNotEquals(COMMITMENT,
                new Commitment(BARTLEY, new HourBlock(ELEVEN_AM, Day.TUESDAY)));
    }

    @Test
    void equals_differentType_false() {
        assertNotEquals(COMMITMENT, 5);
    }

    @Test
    void toString_validLocation_containsLocationName() {
        assertTrue(COMMITMENT.toString().contains(BARTLEY.getName()));
    }

    @Test
    void toString_validTimePeriod_containsTimePeriod() {
        assertTrue(COMMITMENT.toString().contains(HOUR_BLOCK.toString()));
    }

    @Test
    void getLocation_validLocation_success() {
        assertEquals(COMMITMENT.getLocation(), BARTLEY);
    }

    @Test
    void getTimePeriod_validTimePeriod_success() {
        assertEquals(COMMITMENT.getTimePeriod(), HOUR_BLOCK);
    }

    @Test
    void getDay_validTimePeriod_success() {
        assertEquals(COMMITMENT.getDay(), Day.TUESDAY);
    }

    @Test
    void getStartTime_validTimePeriod_success() {
        assertEquals(COMMITMENT.getStartTime(), TEN_AM);
    }

    @Test
    void getEndTime_validTimePeriod_success() {
        assertEquals(COMMITMENT.getEndTime(), ELEVEN_AM);
    }
}
