package seedu.address.model.timetable.time.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import static seedu.address.model.timetable.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_A;
import static seedu.address.model.timetable.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_B;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_A;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_B;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_C;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_D;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_E;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.scheduler.Timetable;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtils;


class TimeUtilsTest {

    @Test
    public void getCommonIntervals_twoTimetablesMonday_oneTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        // expecting 12pm - 10pm
        assertEquals(new TimeBlock(TWELVE_PM, TEN_PM, Day.MONDAY), mergedIntervals.get(0));
    }

    @Test
    public void getCommonIntervals_twoTimetablesTuesday_success() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        //expecting 8-10am, 12-4pm, 5pm - 10pm
        assertEquals(3, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{new TimeBlock(EIGHT_AM, TEN_AM, Day.TUESDAY),
            new TimeBlock(TWELVE_PM, FOUR_PM, Day.TUESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.TUESDAY)}, mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_twoTimetablesWednesday_success() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        //expecting 8am-6pm, 7-10pm
        assertEquals(2, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[] { new TimeBlock(EIGHT_AM, SIX_PM, Day.WEDNESDAY),
            new TimeBlock(SEVEN_PM, TEN_PM, Day.WEDNESDAY)}, mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_twoTimetablesThursday_success() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.THURSDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        //expecting 8-4pm, 9-10pm
        assertEquals(2, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[] { new TimeBlock(EIGHT_AM, FOUR_PM, Day.THURSDAY),
            new TimeBlock(NINE_PM, TEN_PM, Day.THURSDAY)}, mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_twoTimetablesFriday_success() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.FRIDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        //expecting 11am-7pm
        assertEquals(1, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[] { new TimeBlock(ELEVEN_AM, SEVEN_PM, Day.FRIDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_noCommonSlotAvailable_success() {
        List<Timetable> timetables = List.of(FULL_CONFLICT_TIMETABLE_A, FULL_CONFLICT_TIMETABLE_B);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.THURSDAY, timetables);
        assertEquals(0, intervals.size());
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        //expecting 11am-7pm
        assertEquals(0, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{}, mergedIntervals.toArray());

    }

    @Test
    public void getCommonIntervals_emptyList_success() {
        List<Timetable> timetables = List.of();
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.THURSDAY, timetables);
        assertEquals(0, intervals.size());
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        //expecting 11am-7pm
        assertEquals(0, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{}, mergedIntervals.toArray());

    }

    @Test
    public void getCommonIntervals_threeTimetablesMonday_oneTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B, TIMETABLE_C);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        // expecting 12pm - 10pm
        assertEquals(new TimeBlock(TWELVE_PM, TEN_PM, Day.MONDAY), mergedIntervals.get(0));
    }

    @Test
    public void getCommonIntervals_threeTimetablesWednesday_oneTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B, TIMETABLE_C);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-10am, 1pm-6pm, 7-10pm
        assertArrayEquals(new TimeBlock[]{new TimeBlock(EIGHT_AM, TEN_AM, Day.WEDNESDAY),
            new TimeBlock(ONE_PM, SIX_PM, Day.WEDNESDAY),
            new TimeBlock(SEVEN_PM, TEN_PM, Day.WEDNESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Monday_oneTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        // expecting 12noon-2pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(TWELVE_PM, TEN_PM, Day.MONDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Tuesday_twoTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_D, TIMETABLE_C, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 12noon-2am, 5-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(TWELVE_PM, TWO_PM, Day.TUESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.TUESDAY)},
                mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Wednesday_oneTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-10am, 1pm-4pm, 5pm-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, TEN_AM, Day.WEDNESDAY),
            new TimeBlock(ONE_PM, FOUR_PM, Day.WEDNESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.WEDNESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Thursday_twoTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.THURSDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8am-11am, 1pm-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, ELEVEN_AM, Day.THURSDAY),
            new TimeBlock(ONE_PM, TEN_PM, Day.THURSDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Friday_twoTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_C, TIMETABLE_E, TIMETABLE_D);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.FRIDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8am-9am, 11am-7pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, NINE_AM, Day.FRIDAY),
            new TimeBlock(ELEVEN_AM, SEVEN_PM, Day.FRIDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Monday_twoTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_B, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8-10am, 12-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, TEN_AM, Day.MONDAY),
            new TimeBlock(TWELVE_PM, TEN_PM, Day.MONDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Tuesday_threeTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_D, TIMETABLE_B, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8-10am, 12-2pm, 5-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, TEN_AM, Day.TUESDAY),
            new TimeBlock(TWELVE_PM, TWO_PM, Day.TUESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.TUESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Wednesday_threeTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_B, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-4pm, 5pm-6pm, 7pm-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, FOUR_PM, Day.WEDNESDAY),
            new TimeBlock(FIVE_PM, SIX_PM, Day.WEDNESDAY),
            new TimeBlock(SEVEN_PM, TEN_PM, Day.WEDNESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Thursday_threeTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_B, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.THURSDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-11am, 1pm-5pm, 9-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, ELEVEN_AM, Day.THURSDAY),
            new TimeBlock(ONE_PM, FIVE_PM, Day.THURSDAY),
            new TimeBlock(NINE_PM, TEN_PM, Day.THURSDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Friday_twoTimeBlock() {
        List<Timetable> timetables = List.of(TIMETABLE_B, TIMETABLE_E, TIMETABLE_D);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.FRIDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8am-9am, 11am-7pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, NINE_AM, Day.FRIDAY),
            new TimeBlock(ELEVEN_AM, SEVEN_PM, Day.FRIDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_fiveTimeTablesTuesday_success() {
        List<Timetable> timetables = List.of(TIMETABLE_A, TIMETABLE_B, TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = TimeUtils.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(intervals);
        assertFalse(mergedIntervals.isEmpty());
        //expecting 12-2pm, 5pm - 10pm
        assertEquals(2, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(TWELVE_PM, TWO_PM, Day.TUESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.TUESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getIntervals_nineDisjointIntervalsNonIntersecting_threeTimeBlocks() {
        Day schoolDay = Day.MONDAY;
        List<HourBlock> hourBlocks = List.of(new HourBlock(EIGHT_AM, schoolDay),
            new HourBlock(NINE_AM, schoolDay), new HourBlock(ONE_PM, schoolDay), new HourBlock(TWO_PM, schoolDay),
            new HourBlock(THREE_PM, schoolDay), new HourBlock(FIVE_PM, schoolDay), new HourBlock(SIX_PM, schoolDay),
            new HourBlock(SEVEN_PM, schoolDay), new HourBlock(EIGHT_PM, schoolDay));
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(hourBlocks);
        //expecting 8am-10am, 1pm-4pm, 5pm-9pm
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{new TimeBlock(EIGHT_AM, TEN_AM, schoolDay),
            new TimeBlock(ONE_PM, FOUR_PM, schoolDay), new TimeBlock(FIVE_PM, NINE_PM, schoolDay)},
            mergedIntervals.toArray());
    }

    @Test
    public void getIntervals_thirteenDisjointIntervalIntersecting_oneTimeBlock() {
        Day day = Day.MONDAY;
        List<HourBlock> hourBlocks = List.of(new HourBlock(EIGHT_AM, day),
            new HourBlock(NINE_AM, day), new HourBlock(TEN_AM, day),
            new HourBlock(ELEVEN_AM, day), new HourBlock(TWELVE_PM, day),
            new HourBlock(ONE_PM, day), new HourBlock(TWO_PM, day),
            new HourBlock(THREE_PM, day), new HourBlock(FOUR_PM, day),
            new HourBlock(FIVE_PM, day), new HourBlock(SIX_PM, day),
            new HourBlock(SEVEN_PM, day), new HourBlock(EIGHT_PM, day));
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(hourBlocks);
        //expecting 8am-9pm
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{new TimeBlock(EIGHT_AM, NINE_PM, day)},
                mergedIntervals.toArray());
    }

    @Test
    public void testIntervals_emptyTimeSlots_emptyMerge() {
        List<HourBlock> hourBlocks = List.of();
        List<TimePeriod> mergedIntervals = TimeUtils.mergeTimeSlots(hourBlocks);
        assertTrue(mergedIntervals.isEmpty());
    }

    @Test
    public void testFormating_success() {
        assertEquals("8 AM", TimeUtils.formatLocalTime(EIGHT_AM));
        assertEquals("9 AM", TimeUtils.formatLocalTime(NINE_AM));
        assertEquals("10 AM", TimeUtils.formatLocalTime(TEN_AM));
        assertEquals("11 AM", TimeUtils.formatLocalTime(ELEVEN_AM));
        assertEquals("12 PM", TimeUtils.formatLocalTime(TWELVE_PM));
        assertEquals("1 PM", TimeUtils.formatLocalTime(ONE_PM));
        assertEquals("2 PM", TimeUtils.formatLocalTime(TWO_PM));
        assertEquals("3 PM", TimeUtils.formatLocalTime(THREE_PM));
        assertEquals("4 PM", TimeUtils.formatLocalTime(FOUR_PM));
        assertEquals("5 PM", TimeUtils.formatLocalTime(FIVE_PM));
        assertEquals("6 PM", TimeUtils.formatLocalTime(SIX_PM));
        assertEquals("7 PM", TimeUtils.formatLocalTime(SEVEN_PM));
        assertEquals("8 PM", TimeUtils.formatLocalTime(EIGHT_PM));
        assertEquals("9 PM", TimeUtils.formatLocalTime(NINE_PM));
        assertEquals("10 PM", TimeUtils.formatLocalTime(TEN_PM));
    }
}
