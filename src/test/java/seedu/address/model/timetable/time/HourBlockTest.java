package seedu.address.model.timetable.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_7PM_3HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_8AM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_10AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_8AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_ANOTHER_8AM_2HR_LESSON;

import org.joda.time.Hours;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import seedu.address.logic.recommender.timing.exceptions.CommitmentClashException;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.exceptions.WrongTimeException;

class HourBlockTest {

    private static final LocalTime MORNING_START = new LocalTime(8, 0);
    private static final LocalTime MORNING_SECOND = new LocalTime(9, 0);
    private static final LocalTime AFTERNOON_FIRST = new LocalTime(12, 0);
    private static final LocalTime NIGHT_FIRST = new LocalTime(20, 0);

    @Test
    public void initialise_morningSlotNoLesson_success() {
        HourBlock hourBlock = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(hourBlock.isFree());
        assertEquals(1, hourBlock.getHoursBetween().getHours());
        assertEquals(MORNING_START, hourBlock.getStartTime());
        assertEquals(MORNING_START.plusHours(1), hourBlock.getEndTime());
    }

    @Test
    public void initialise_morningSlotWithLesson_success() {
        HourBlock hourBlock = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(hourBlock.canFitCommitment(MONDAY_8AM_2HR_LESSON));
        assertDoesNotThrow(() -> hourBlock.setCommitment(MONDAY_8AM_2HR_LESSON));
        assertFalse(hourBlock.isFree());
    }

    @Test
    public void initialise_dayMismatchTimeCorrect_failure() {
        HourBlock hourBlock = new HourBlock(MORNING_START, Day.MONDAY);
        assertFalse(hourBlock.canFitCommitment(FRIDAY_8AM_1HR_LESSON));
        assertThrows(WrongTimeException.class, () -> hourBlock.setCommitment(FRIDAY_8AM_1HR_LESSON));
    }

    @Test
    public void initialise_dayMatchTimeWrong_throwsWrongTimeException() {
        HourBlock hourBlock = new HourBlock(MORNING_START, Day.MONDAY);
        assertFalse(hourBlock.canFitCommitment(MONDAY_10AM_2HR_LESSON));
        assertThrows(WrongTimeException.class, () -> hourBlock.setCommitment(MONDAY_10AM_2HR_LESSON));
    }

    @Test
    public void initialise_dayMatchLaterHalfLesson_success() {
        HourBlock hourBlock = new HourBlock(MORNING_SECOND, Day.MONDAY);
        assertTrue(hourBlock.isFree());
        assertTrue(hourBlock.canFitCommitment(MONDAY_8AM_2HR_LESSON));
        assertDoesNotThrow(() -> hourBlock.setCommitment(MONDAY_8AM_2HR_LESSON));
        assertFalse(hourBlock.isFree());
    }

    @Test
    public void initialise_dayMatchMiddleSlot_success() {
        HourBlock hourBlock = new HourBlock(new LocalTime(20, 0), Day.FRIDAY);
        assertTrue(hourBlock.isFree());
        assertTrue(hourBlock.canFitCommitment(FRIDAY_7PM_3HR_LESSON));
        assertDoesNotThrow(() -> hourBlock.setCommitment(FRIDAY_7PM_3HR_LESSON));
        assertFalse(hourBlock.isFree());
        assertEquals(FRIDAY_7PM_3HR_LESSON, hourBlock.getCommitment().get());
    }

    @Test
    public void addLesson_emptyTimeSlot_success() {
        HourBlock hourBlock = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(hourBlock.isFree());
        hourBlock.setCommitment(MONDAY_8AM_2HR_LESSON);
        assertFalse(hourBlock.isFree());
    }

    @Test
    public void addLesson_occupiedTimeSlot_throwsLessonClashException() {
        HourBlock hourBlock = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(hourBlock.isFree());
        hourBlock.setCommitment(MONDAY_8AM_2HR_LESSON);
        assertFalse(hourBlock.isFree());
        // check to see if lesson can even fit inside the slot.
        assertTrue(hourBlock.canFitCommitment(MONDAY_ANOTHER_8AM_2HR_LESSON));
        assertThrows(CommitmentClashException.class, () -> hourBlock.setCommitment(MONDAY_ANOTHER_8AM_2HR_LESSON));
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsABeforeB_success() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(MORNING_SECOND, Day.MONDAY);
        TimePeriod mergedTimeSlots = hourBlockA.merge(hourBlockB);
        assertEquals(new LocalTime(8, 0), mergedTimeSlots.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots.getHoursBetween());
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsCommutativeTest_success() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(MORNING_SECOND, Day.MONDAY);
        TimePeriod mergedTimeSlots1 = hourBlockB.merge(hourBlockA);
        TimePeriod mergedTimeSlots2 = hourBlockA.merge(hourBlockB);
        assertEquals(new LocalTime(8, 0), mergedTimeSlots1.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots1.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots1.getHoursBetween());
        assertEquals(new LocalTime(8, 0), mergedTimeSlots2.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots2.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots2.getHoursBetween());

        assertEquals(mergedTimeSlots1, mergedTimeSlots2);

    }

    @Test
    public void mergeTimeSlot_nonConsecutiveTimeSlots_throwsWrongTimingException() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(AFTERNOON_FIRST, Day.MONDAY);
        assertThrows(WrongTimeException.class, () -> hourBlockA.merge(hourBlockB));
        assertThrows(WrongTimeException.class, () -> hourBlockB.merge(hourBlockA));
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsWrongDay_throwsWrongTimingException() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(AFTERNOON_FIRST, Day.TUESDAY);
        assertThrows(WrongTimeException.class, () -> hourBlockA.merge(hourBlockB));
        assertThrows(WrongTimeException.class, () -> hourBlockB.merge(hourBlockA));
    }

    @Test
    public void mergeTimeSlot_sameTimeSlot_throwsWrongTimingException() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(MORNING_START, Day.MONDAY);
        assertThrows(WrongTimeException.class, () -> hourBlockA.merge(hourBlockB));
        assertThrows(WrongTimeException.class, () -> hourBlockB.merge(hourBlockA));
    }

    @Test
    public void testSequence_morningBeforeNight_success() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(MORNING_SECOND, Day.MONDAY);
        assertTrue(hourBlockA.isStraightBefore(hourBlockB));
        assertTrue(hourBlockB.isStraightAfter(hourBlockA));
        assertTrue(hourBlockA.isConsecutiveWith(hourBlockB));
        assertTrue(hourBlockB.isConsecutiveWith(hourBlockA));
    }

    @Test
    public void testSequence_nonConsecutiveTimeSlots_failure() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(AFTERNOON_FIRST, Day.MONDAY);
        assertFalse(hourBlockA.isStraightBefore(hourBlockB));
        assertFalse(hourBlockB.isStraightAfter(hourBlockA));
        assertFalse(hourBlockA.isConsecutiveWith(hourBlockB));
        assertFalse(hourBlockB.isConsecutiveWith(hourBlockA));
    }

    @Test
    public void testSequence_consecutiveTimeDifferentDays_failure() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(MORNING_SECOND, Day.TUESDAY);
        assertFalse(hourBlockA.isStraightBefore(hourBlockB));
        assertFalse(hourBlockB.isStraightAfter(hourBlockA));
        assertFalse(hourBlockA.isConsecutiveWith(hourBlockB));
        assertFalse(hourBlockB.isConsecutiveWith(hourBlockA));
    }

    @Test
    public void copyTimeSlot_checkEquality_sameTimeSlot() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(hourBlockA);
        assertEquals(hourBlockB, hourBlockA);
    }

    @Test
    public void equalityCheck_null_notEqual() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        assertNotEquals(null, hourBlockA);
    }

    @Test
    public void equalityCheck_notSameTimeSlot_notEqual() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(AFTERNOON_FIRST, Day.MONDAY);
        assertNotEquals(hourBlockA, hourBlockB);
    }

    @Test
    public void equalityCheck_sameTimeDifferentLesson_notEqual() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock hourBlockB = new HourBlock(MORNING_START, Day.MONDAY);
        hourBlockA.setCommitment(MONDAY_8AM_2HR_LESSON);
        assertNotEquals(hourBlockA, hourBlockB);
    }

    @Test
    public void equalityCheck_sameTimeSlotReference_equal() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        assertEquals(hourBlockA, hourBlockA);
    }

    @Test
    public void equalityCheck_differentObjects_notEqual() {
        HourBlock hourBlockA = new HourBlock(MORNING_START, Day.MONDAY);
        TimeBlock timeBlockA = new TimeBlock(MORNING_START, MORNING_SECOND, Day.MONDAY);
        assertNotEquals(timeBlockA, hourBlockA);
    }

}
