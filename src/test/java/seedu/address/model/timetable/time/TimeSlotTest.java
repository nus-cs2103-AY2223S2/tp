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

import seedu.address.model.timetable.exceptions.LessonClashException;
import seedu.address.model.timetable.exceptions.WrongTimeException;

class TimeSlotTest {

    private static final LocalTime MORNING_START = new LocalTime(8, 0);
    private static final LocalTime MORNING_SECOND = new LocalTime(9, 0);
    private static final LocalTime AFTERNOON_FIRST = new LocalTime(12, 0);
    private static final LocalTime NIGHT_FIRST = new LocalTime(20, 0);

    @Test
    public void initialise_morningSlotNoLesson_success() {
        TimeSlot timeSlot = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertTrue(timeSlot.isFree());
        assertEquals(1, timeSlot.getHoursBetween().getHours());
        assertEquals(MORNING_START, timeSlot.getStartTime());
        assertEquals(MORNING_START.plusHours(1), timeSlot.getEndTime());
    }

    @Test
    public void initialise_morningSlotWithLesson_success() {
        TimeSlot timeSlot = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertTrue(timeSlot.canFitLesson(MONDAY_8AM_2HR_LESSON));
        assertDoesNotThrow(() -> timeSlot.setLesson(MONDAY_8AM_2HR_LESSON));
        assertFalse(timeSlot.isFree());
    }

    @Test
    public void initialise_dayMismatchTimeCorrect_failure() {
        TimeSlot timeSlot = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertFalse(timeSlot.canFitLesson(FRIDAY_8AM_1HR_LESSON));
        assertThrows(WrongTimeException.class, () -> timeSlot.setLesson(FRIDAY_8AM_1HR_LESSON));
    }

    @Test
    public void initialise_dayMatchTimeWrong_throwsWrongTimeException() {
        TimeSlot timeSlot = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertFalse(timeSlot.canFitLesson(MONDAY_10AM_2HR_LESSON));
        assertThrows(WrongTimeException.class, () -> timeSlot.setLesson(MONDAY_10AM_2HR_LESSON));
    }

    @Test
    public void initialise_dayMatchLaterHalfLesson_success() {
        TimeSlot timeSlot = new TimeSlot(MORNING_SECOND, SchoolDay.MONDAY);
        assertTrue(timeSlot.isFree());
        assertTrue(timeSlot.canFitLesson(MONDAY_8AM_2HR_LESSON));
        assertDoesNotThrow(() -> timeSlot.setLesson(MONDAY_8AM_2HR_LESSON));
        assertFalse(timeSlot.isFree());
    }

    @Test
    public void initialise_dayMatchMiddleSlot_success() {
        TimeSlot timeSlot = new TimeSlot(new LocalTime(20, 0), SchoolDay.FRIDAY);
        assertTrue(timeSlot.isFree());
        assertTrue(timeSlot.canFitLesson(FRIDAY_7PM_3HR_LESSON));
        assertDoesNotThrow(() -> timeSlot.setLesson(FRIDAY_7PM_3HR_LESSON));
        assertFalse(timeSlot.isFree());
        assertEquals(FRIDAY_7PM_3HR_LESSON, timeSlot.getLesson().get());
    }

    @Test
    public void addLesson_emptyTimeSlot_success() {
        TimeSlot timeSlot = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertTrue(timeSlot.isFree());
        timeSlot.setLesson(MONDAY_8AM_2HR_LESSON);
        assertFalse(timeSlot.isFree());
    }

    @Test
    public void addLesson_occupiedTimeSlot_throwsLessonClashException() {
        TimeSlot timeSlot = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertTrue(timeSlot.isFree());
        timeSlot.setLesson(MONDAY_8AM_2HR_LESSON);
        assertFalse(timeSlot.isFree());
        // check to see if lesson can even fit inside the slot.
        assertTrue(timeSlot.canFitLesson(MONDAY_ANOTHER_8AM_2HR_LESSON));
        assertThrows(LessonClashException.class, () -> timeSlot.setLesson(MONDAY_ANOTHER_8AM_2HR_LESSON));
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsABeforeB_success() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(MORNING_SECOND, SchoolDay.MONDAY);
        TimePeriod mergedTimeSlots = timeSlotA.merge(timeSlotB);
        assertEquals(new LocalTime(8, 0), mergedTimeSlots.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots.getHoursBetween());
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsCommutativeTest_success() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(MORNING_SECOND, SchoolDay.MONDAY);
        TimePeriod mergedTimeSlots1 = timeSlotB.merge(timeSlotA);
        TimePeriod mergedTimeSlots2 = timeSlotA.merge(timeSlotB);
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
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(AFTERNOON_FIRST, SchoolDay.MONDAY);
        assertThrows(WrongTimeException.class, () -> timeSlotA.merge(timeSlotB));
        assertThrows(WrongTimeException.class, () -> timeSlotB.merge(timeSlotA));
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsWrongDay_throwsWrongTimingException() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(AFTERNOON_FIRST, SchoolDay.TUESDAY);
        assertThrows(WrongTimeException.class, () -> timeSlotA.merge(timeSlotB));
        assertThrows(WrongTimeException.class, () -> timeSlotB.merge(timeSlotA));
    }

    @Test
    public void mergeTimeSlot_sameTimeSlot_throwsWrongTimingException() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertThrows(WrongTimeException.class, () -> timeSlotA.merge(timeSlotB));
        assertThrows(WrongTimeException.class, () -> timeSlotB.merge(timeSlotA));
    }

    @Test
    public void testSequence_morningBeforeNight_success() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(MORNING_SECOND, SchoolDay.MONDAY);
        assertTrue(timeSlotA.isStraightBefore(timeSlotB));
        assertTrue(timeSlotB.isStraightAfter(timeSlotA));
        assertTrue(timeSlotA.isConsecutiveWith(timeSlotB));
        assertTrue(timeSlotB.isConsecutiveWith(timeSlotA));
    }

    @Test
    public void testSequence_nonConsecutiveTimeSlots_failure() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(AFTERNOON_FIRST, SchoolDay.MONDAY);
        assertFalse(timeSlotA.isStraightBefore(timeSlotB));
        assertFalse(timeSlotB.isStraightAfter(timeSlotA));
        assertFalse(timeSlotA.isConsecutiveWith(timeSlotB));
        assertFalse(timeSlotB.isConsecutiveWith(timeSlotA));
    }

    @Test
    public void testSequence_consecutiveTimeDifferentDays_failure() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(MORNING_SECOND, SchoolDay.TUESDAY);
        assertFalse(timeSlotA.isStraightBefore(timeSlotB));
        assertFalse(timeSlotB.isStraightAfter(timeSlotA));
        assertFalse(timeSlotA.isConsecutiveWith(timeSlotB));
        assertFalse(timeSlotB.isConsecutiveWith(timeSlotA));
    }

    @Test
    public void copyTimeSlot_checkEquality_sameTimeSlot() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(timeSlotA);
        assertEquals(timeSlotB, timeSlotA);
    }

    @Test
    public void equalityCheck_null_notEqual() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertNotEquals(null, timeSlotA);
    }

    @Test
    public void equalityCheck_notSameTimeSlot_notEqual() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(AFTERNOON_FIRST, SchoolDay.MONDAY);
        assertNotEquals(timeSlotA, timeSlotB);
    }

    @Test
    public void equalityCheck_sameTimeDifferentLesson_notEqual() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeSlot timeSlotB = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        timeSlotA.setLesson(MONDAY_8AM_2HR_LESSON);
        assertNotEquals(timeSlotA, timeSlotB);
    }

    @Test
    public void equalityCheck_sameTimeSlotReference_equal() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        assertEquals(timeSlotA, timeSlotA);
    }

    @Test
    public void equalityCheck_differentObjects_notEqual() {
        TimeSlot timeSlotA = new TimeSlot(MORNING_START, SchoolDay.MONDAY);
        TimeBlock timeBlockA = new TimeBlock(MORNING_START, MORNING_SECOND, SchoolDay.MONDAY);
        assertNotEquals(timeBlockA, timeSlotA);
    }

}
