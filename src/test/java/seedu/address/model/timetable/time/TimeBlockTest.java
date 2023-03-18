package seedu.address.model.timetable.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_PM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.FIVE_PM;
import static seedu.address.model.timetable.util.TypicalTime.FOUR_PM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_AM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_PM;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.SEVEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.SIX_PM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.THREE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWO_PM;

import org.joda.time.Hours;
import org.junit.jupiter.api.Test;

import seedu.address.model.timetable.exceptions.WrongTimeException;

class TimeBlockTest {

    @Test
    public void initialise_twoHours_success() {
        TimeBlock timeBlock = new TimeBlock(EIGHT_AM, TEN_AM, SchoolDay.MONDAY);
        assertEquals(Hours.TWO, timeBlock.getHoursBetween());
    }

    @Test
    public void initialise_eightHours_success() {
        TimeBlock timeBlock = new TimeBlock(TEN_AM, SIX_PM, SchoolDay.TUESDAY);
        assertEquals(Hours.EIGHT, timeBlock.getHoursBetween());
    }

    @Test
    public void initialise_tenHours_success() {
        TimeBlock timeBlock = new TimeBlock(ELEVEN_AM, NINE_PM, SchoolDay.TUESDAY);
        assertEquals(Hours.hours(10), timeBlock.getHoursBetween());
    }

    @Test
    public void initialise_twelveHours_success() {
        TimeBlock timeBlock = new TimeBlock(TEN_AM, TEN_PM, SchoolDay.TUESDAY);
        assertEquals(Hours.hours(12), timeBlock.getHoursBetween());
    }

    @Test
    public void initialise_endTimeBeforeStartTime_throwsException() {
        assertThrows(WrongTimeException.class, () ->new TimeBlock(ONE_PM, ELEVEN_AM, SchoolDay.WEDNESDAY));
    }

    @Test
    public void mergeTimeBlock_withAnotherConsecutiveTimeBlock_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TEN_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TEN_AM, SIX_PM, SchoolDay.MONDAY);
        TimeBlock mergedTimeBlock = timeBlock1.merge(timeBlock2);
        assertEquals(mergedTimeBlock, new TimeBlock(EIGHT_AM, SIX_PM, SchoolDay.MONDAY));
        assertEquals(Hours.hours(10), mergedTimeBlock.getHoursBetween());
    }

    @Test
    public void mergeTimeBlock_withNonConsecutiveTimeBlockSameDay_throwsException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TEN_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(ELEVEN_AM, SIX_PM, SchoolDay.MONDAY);
        assertTrue(timeBlock1.isSameDay(timeBlock2));
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
    }

    @Test
    public void mergeTimeBlock_withNonConsecutiveTimeBlockDifferentDay_throwsException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TEN_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(ELEVEN_AM, SIX_PM, SchoolDay.FRIDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isSameDay(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeBlock1));
    }

    @Test
    public void mergeTimeBlock_withConsecutiveTimeBlockDifferentDay_throwsException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TEN_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TEN_AM, SIX_PM, SchoolDay.FRIDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isSameDay(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeBlock1));

    }

    @Test
    public void testConsecutiveTimeBlocks_commutativityTest_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TEN_AM, SchoolDay.THURSDAY);
        TimeBlock timeBlock2 = new TimeBlock(TEN_AM, SIX_PM, SchoolDay.THURSDAY);
        assertDoesNotThrow(() -> timeBlock1.merge(timeBlock2));
        assertDoesNotThrow(() -> timeBlock2.merge(timeBlock1));
        TimeBlock timeBlocknew1 = timeBlock1.merge(timeBlock2);
        TimeBlock timeBlocknew2 = timeBlock2.merge(timeBlock1);
        assertEquals(timeBlocknew1, timeBlocknew2);
        assertEquals(Hours.hours(10), timeBlocknew1.getHoursBetween());
        assertEquals(Hours.hours(10), timeBlocknew2.getHoursBetween());
        assertEquals(timeBlocknew1.getStartTime(), EIGHT_AM);
        assertEquals(timeBlocknew2.getStartTime(), EIGHT_AM);
        assertEquals(timeBlocknew1.getEndTime(), SIX_PM);
        assertEquals(timeBlocknew2.getEndTime(), SIX_PM);
    }

    // merge with timeslots
    @Test
    public void mergeTimeSlot_withAnotherConsecutiveTimeBlock_success() {
        TimeSlot timeSlot = new TimeSlot(EIGHT_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock = new TimeBlock(NINE_AM, SIX_PM, SchoolDay.MONDAY);
        TimeBlock mergedTimeBlock = timeSlot.merge(timeBlock);
        assertEquals(mergedTimeBlock, new TimeBlock(EIGHT_AM, SIX_PM, SchoolDay.MONDAY));
        assertEquals(Hours.hours(10), mergedTimeBlock.getHoursBetween());
    }

    @Test
    public void mergeTimeSlot_withNonConsecutiveTimeBlockSameDay_throwsException() {
        TimeSlot timeSlot = new TimeSlot(EIGHT_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock = new TimeBlock(ELEVEN_AM, SIX_PM, SchoolDay.MONDAY);
        assertTrue(timeSlot.isSameDay(timeBlock));
        assertFalse(timeSlot.isConsecutiveWith(timeBlock));
        assertFalse(timeBlock.isConsecutiveWith(timeSlot));
    }

    @Test
    public void mergeTimeSlot_withNonConsecutiveTimeBlockDifferentDay_throwsException() {
        TimeSlot timeSlot = new TimeSlot(FOUR_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock = new TimeBlock(ELEVEN_AM, SIX_PM, SchoolDay.FRIDAY);
        assertFalse(timeSlot.isConsecutiveWith(timeBlock));
        assertFalse(timeBlock.isConsecutiveWith(timeSlot));
        assertFalse(timeSlot.isSameDay(timeBlock));
        assertThrows(WrongTimeException.class, () -> timeSlot.merge(timeBlock));
        assertThrows(WrongTimeException.class, () -> timeBlock.merge(timeSlot));
    }

    @Test
    public void mergeTimeSlot_withConsecutiveTimeBlockDifferentDay_throwsException() {
        TimeSlot timeSlot = new TimeSlot(EIGHT_AM, SchoolDay.MONDAY);
        TimeBlock timeBlock = new TimeBlock(TWO_PM, SIX_PM, SchoolDay.FRIDAY);
        assertFalse(timeSlot.isConsecutiveWith(timeBlock));
        assertFalse(timeBlock.isConsecutiveWith(timeSlot));
        assertFalse(timeSlot.isSameDay(timeBlock));
        assertThrows(WrongTimeException.class, () -> timeSlot.merge(timeBlock));
        assertThrows(WrongTimeException.class, () -> timeBlock.merge(timeSlot));

    }

    @Test
    public void testConsecutiveTimeSlots_commutativityTest_success() {
        TimeSlot timeSlot = new TimeSlot(TWELVE_PM, SchoolDay.THURSDAY);
        TimeBlock timeBlock = new TimeBlock(ONE_PM, SIX_PM, SchoolDay.THURSDAY);
        assertDoesNotThrow(() -> timeSlot.merge(timeBlock));
        assertDoesNotThrow(() -> timeBlock.merge(timeSlot));
        TimeBlock timeBlocknew1 = timeSlot.merge(timeBlock);
        TimeBlock timeBlocknew2 = timeBlock.merge(timeSlot);
        assertEquals(timeBlocknew1, timeBlocknew2);
        assertEquals(Hours.SIX, timeBlocknew1.getHoursBetween());
        assertEquals(Hours.SIX, timeBlocknew2.getHoursBetween());
        assertEquals(timeBlocknew1.getStartTime(), TWELVE_PM);
        assertEquals(timeBlocknew2.getStartTime(), TWELVE_PM);
        assertEquals(timeBlocknew1.getEndTime(), SIX_PM);
        assertEquals(timeBlocknew2.getEndTime(), SIX_PM);
    }

    @Test
    public void chain_consecutive3TimeBlocks_success() {
        TimeBlock timeBlock1 = new TimeBlock(ONE_PM, THREE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(THREE_PM, EIGHT_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, SchoolDay.MONDAY);
        TimeBlock mergedBlock1 = timeBlock1.merge(timeBlock2).merge(timeBlock3);
        TimeBlock mergedBlock2 = timeBlock2.merge(timeBlock1).merge(timeBlock3);
        TimeBlock mergedBlock3 = timeBlock3.merge(timeBlock2).merge(timeBlock1);
        assertEquals(mergedBlock1, mergedBlock2);
        assertEquals(mergedBlock2, mergedBlock3);
        assertEquals(mergedBlock1, mergedBlock3);
        assertEquals(Hours.hours(9), mergedBlock1.getHoursBetween());
        assertEquals(Hours.hours(9), mergedBlock2.getHoursBetween());
        assertEquals(Hours.hours(9), mergedBlock3.getHoursBetween());
    }

    @Test
    public void chainWrongOrder_consecutive3TimeBlocks_success() {
        TimeBlock timeBlock1 = new TimeBlock(ONE_PM, THREE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(THREE_PM, EIGHT_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, SchoolDay.MONDAY);
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock2));
    }

    @Test
    public void chain_nonConsecutive3TimeBlocksSameDay_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(NINE_AM, THREE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, SchoolDay.MONDAY);
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeBlock1).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () ->timeBlock2.merge(timeBlock1).merge(timeBlock3));

    }

    @Test
    public void chain_nonConsecutive3TimeBlocksDifferentDay_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(NINE_AM, THREE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.TUESDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, SchoolDay.WEDNESDAY);
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeBlock1).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () ->timeBlock2.merge(timeBlock1).merge(timeBlock3));

    }

    @Test
    public void chain_consecutive4TimeBlocks_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TWELVE_PM, TWO_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(TWO_PM, FIVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock4 = new TimeBlock(FIVE_PM, TEN_PM, SchoolDay.MONDAY);
        TimeBlock mergedBlock1 = timeBlock1.merge(timeBlock2).merge(timeBlock3).merge(timeBlock4);
        TimeBlock mergedBlock2 = timeBlock2.merge(timeBlock1).merge(timeBlock3).merge(timeBlock4);
        TimeBlock mergedBlock3 = timeBlock3.merge(timeBlock2).merge(timeBlock1).merge(timeBlock4);
        TimeBlock mergedBlock4 = timeBlock4.merge(timeBlock3).merge(timeBlock2).merge(timeBlock1);
        TimeBlock mergedBlock5 = timeBlock2.merge(timeBlock3).merge(timeBlock4).merge(timeBlock1);
        TimeBlock mergedBlock6 = timeBlock3.merge(timeBlock2).merge(timeBlock4).merge(timeBlock1);
        assertEquals(mergedBlock1, mergedBlock2);
        assertEquals(mergedBlock2, mergedBlock3);
        assertEquals(mergedBlock1, mergedBlock3);
        assertEquals(mergedBlock4, mergedBlock2);
        assertEquals(mergedBlock5, mergedBlock1);
        assertEquals(mergedBlock6, mergedBlock3);
        assertEquals(Hours.hours(14), mergedBlock1.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock2.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock3.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock4.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock5.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock6.getHoursBetween());

    }

    @Test
    public void chain_nonConsecutive4TimeBlocks_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(THREE_PM, SEVEN_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(TWO_PM, SIX_PM, SchoolDay.TUESDAY);
        TimeBlock timeBlock4 = new TimeBlock(SIX_PM, EIGHT_PM, SchoolDay.WEDNESDAY);
        //wrong order
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2).merge(timeBlock4));
        assertThrows(WrongTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock4).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () ->timeBlock4.merge(timeBlock1).merge(timeBlock3).merge(timeBlock3));
        //correct order
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeBlock3).merge(timeBlock1).merge(timeBlock4));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeBlock1).merge(timeBlock3).merge(timeBlock4));
        assertThrows(WrongTimeException.class, () -> timeBlock4.merge(timeBlock3).merge(timeBlock2).merge(timeBlock1));
        assertThrows(WrongTimeException.class, () -> timeBlock3.merge(timeBlock4).merge(timeBlock2).merge(timeBlock1));

    }

    @Test
    public void chain_consecutiveAlternateTimeBlocksAndTimeSlots_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeSlot timeSlot1 = new TimeSlot(TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(ONE_PM, FIVE_PM, SchoolDay.MONDAY);
        TimeSlot timeSlot2 = new TimeSlot(FIVE_PM, SchoolDay.MONDAY);
        TimeBlock mergedBlock1 = timeBlock1.merge(timeSlot1).merge(timeBlock2).merge(timeSlot2);
        TimeBlock mergedBlock2 = timeSlot1.merge(timeBlock1).merge(timeBlock2).merge(timeSlot2);
        TimeBlock mergedBlock3 = timeBlock2.merge(timeSlot2).merge(timeSlot1).merge(timeBlock1);
        TimeBlock mergedBlock4 = timeSlot2.merge(timeBlock2).merge(timeSlot1).merge(timeBlock1);
        TimeBlock mergedBlock5 = timeSlot1.merge(timeBlock2).merge(timeSlot2).merge(timeBlock1);
        TimeBlock mergedBlock6 = timeBlock2.merge(timeSlot1).merge(timeSlot2).merge(timeBlock1);
        assertEquals(mergedBlock1, mergedBlock2);
        assertEquals(mergedBlock2, mergedBlock3);
        assertEquals(mergedBlock1, mergedBlock3);
        assertEquals(mergedBlock4, mergedBlock2);
        assertEquals(mergedBlock5, mergedBlock1);
        assertEquals(mergedBlock6, mergedBlock3);
        assertEquals(Hours.hours(10), mergedBlock1.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock2.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock3.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock4.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock5.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock6.getHoursBetween());
    }

    @Test
    public void chain_nonConsecutiveAlternateTimeBlocksAndTimeSlots_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeSlot timeSlot1 = new TimeSlot(TWELVE_PM, SchoolDay.FRIDAY);
        TimeBlock timeBlock2 = new TimeBlock(ONE_PM, FIVE_PM, SchoolDay.WEDNESDAY);
        TimeSlot timeSlot2 = new TimeSlot(FIVE_PM, SchoolDay.TUESDAY);
        // wrong order
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeSlot1).merge(timeBlock2).merge(timeSlot2));
        assertThrows(WrongTimeException.class, () -> timeSlot1.merge(timeBlock1).merge(timeBlock2).merge(timeSlot2));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeSlot2).merge(timeBlock1).merge(timeSlot2));
        assertThrows(WrongTimeException.class, () -> timeSlot1.merge(timeBlock2).merge(timeSlot2).merge(timeBlock1));
        // correct order
        assertThrows(WrongTimeException.class, () -> timeSlot2.merge(timeSlot1).merge(timeBlock1).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeSlot1.merge(timeSlot2).merge(timeBlock1).merge(timeBlock2));
        assertThrows(WrongTimeException.class, () -> timeBlock1.merge(timeBlock2).merge(timeSlot2).merge(timeSlot1));
        assertThrows(WrongTimeException.class, () -> timeBlock2.merge(timeSlot2).merge(timeBlock1).merge(timeSlot1));
    }

    @Test
    public void checkSequence_consecutive2TimeBlocksSameDay_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TWELVE_PM, FIVE_PM, SchoolDay.MONDAY);
        assertTrue(timeBlock1.isConsecutiveWith(timeBlock2));
        assertTrue(timeBlock2.isConsecutiveWith(timeBlock1));
        assertTrue(timeBlock1.isStraightBefore(timeBlock2));
        assertTrue(timeBlock2.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_consecutive1TimeBlock1TimeSlotSameDay_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeSlot timeSlot1 = new TimeSlot(TWELVE_PM, SchoolDay.MONDAY);
        assertTrue(timeBlock1.isConsecutiveWith(timeSlot1));
        assertTrue(timeSlot1.isConsecutiveWith(timeBlock1));
        assertTrue(timeBlock1.isStraightBefore(timeSlot1));
        assertTrue(timeSlot1.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_consecutive2TimeBlocksDifferentDay_failure() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TWELVE_PM, FIVE_PM, SchoolDay.WEDNESDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isStraightBefore(timeBlock2));
        assertFalse(timeBlock2.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_consecutive1TimeBlock1TimeSlotDifferentDay_failure() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeSlot timeSlot1 = new TimeSlot(TWELVE_PM, SchoolDay.FRIDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeSlot1));
        assertFalse(timeSlot1.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isStraightBefore(timeSlot1));
        assertFalse(timeSlot1.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_sameBlock_failure() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isStraightBefore(timeBlock2));
        assertFalse(timeBlock2.isStraightAfter(timeBlock1));
    }

    @Test
    public void equalityCheck_null_notEquals() {
        assertNotEquals(null, new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY));
    }

    @Test
    public void equalityCheck_notTimeBlock_notEquals() {
        assertNotEquals(new TimeSlot(EIGHT_AM, SchoolDay.MONDAY), new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY));
    }

    @Test
    public void equalityCheck_sameObjectReference_equals() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, SchoolDay.MONDAY);
        assertEquals(timeBlock1, timeBlock1);
        assertEquals(timeBlock1.toString(), timeBlock1.toString());
    }
}
