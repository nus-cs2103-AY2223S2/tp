package seedu.address.model.timetable.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_7PM_3HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_8AM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_9AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_10AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_8AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_ANOTHER_8AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_11AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_4PM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_5PM_4HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_9PM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_10AM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_10AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_2PM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_10AM_3HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_2PM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_4PM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_6PM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_A;
import static seedu.address.model.timetable.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_B;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_A;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_B;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_C;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_D;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_E;

import java.util.ArrayList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import seedu.address.model.time.HourBlock;
import seedu.address.model.timetable.Timetable;
import seedu.address.model.timingrecommender.exceptions.CommitmentClashException;

class TimetableTest {

    public void testSlotsTrue(int start, int limit, ArrayList<HourBlock> hourBlocks) {
        IntStream.iterate(start, x -> x + 1)
                .limit(limit)
                .forEach(x -> assertTrue(hourBlocks.get(x).isFree()));
    }

    public void testSlotsFalse(int start, int limit, ArrayList<HourBlock> hourBlocks) {
        IntStream.iterate(start, x -> x + 1)
                .limit(limit)
                .forEach(x -> assertFalse(hourBlocks.get(x).isFree()));
    }

    @Test
    public void test_typicalTimetables_allValid() {
        assertDoesNotThrow(() -> TIMETABLE_A);
        assertDoesNotThrow(() -> TIMETABLE_B);
        assertDoesNotThrow(() -> TIMETABLE_C);
        assertDoesNotThrow(() -> TIMETABLE_D);
        assertDoesNotThrow(() -> TIMETABLE_E);
    }

    @Test
    public void initialise_oneLesson_success() {
        Timetable timetable = new Timetable();
        timetable.addCommitment(MONDAY_8AM_2HR_LESSON);
        // make sure Monday got the lesson
        ArrayList<HourBlock> mondayTime = timetable.getMondayClasses();
        assertFalse(mondayTime.get(0).isFree());
        assertFalse(mondayTime.get(1).isFree());
        for (int i = 2; i < mondayTime.size(); i++) {
            assertTrue(mondayTime.get(i).isFree());
        }
    }

    @Test
    public void initialise_twoLessons_success() {
        Timetable timetable = new Timetable();
        timetable.addCommitment(MONDAY_8AM_2HR_LESSON);
        timetable.addCommitment(FRIDAY_8AM_1HR_LESSON);
        ArrayList<HourBlock> mondayTime = timetable.getMondayClasses();
        ArrayList<HourBlock> fridayTime = timetable.getFridayClasses();
        assertFalse(mondayTime.get(0).isFree());
        assertFalse(mondayTime.get(1).isFree());
        assertFalse(fridayTime.get(0).isFree());
        for (int i = 2; i < mondayTime.size(); i++) {
            assertTrue(mondayTime.get(i).isFree());
        }
        for (int j = 1; j < fridayTime.size(); j++) {
            assertTrue(fridayTime.get(j).isFree());
        }
    }

    @Test
    public void initialise_clashLessons_throwLessonClashException() {
        Timetable timetable = new Timetable();
        timetable.addCommitment(MONDAY_8AM_2HR_LESSON);
        assertThrows(CommitmentClashException.class, () -> timetable.addCommitment(MONDAY_ANOTHER_8AM_2HR_LESSON));
        Timetable anotherTimetable = new Timetable();
        anotherTimetable.addCommitment(MONDAY_ANOTHER_8AM_2HR_LESSON);
        assertThrows(CommitmentClashException.class, () -> timetable.addCommitment(MONDAY_8AM_2HR_LESSON));
    }

    @Test
    public void initialise_consecutiveLessonsSameDay_success() {
        Timetable timetable = new Timetable();
        timetable.addCommitment(MONDAY_8AM_2HR_LESSON);
        timetable.addCommitment(MONDAY_10AM_2HR_LESSON);
        testSlotsFalse(0, 4, timetable.getMondayClasses());
        testSlotsTrue(4, 10, timetable.getMondayClasses());
    }

    @Test
    public void initialise_lessonsDifferentDaysSameTiming_success() {
        Timetable timetable = new Timetable();
        assertEquals(MONDAY_10AM_2HR_LESSON.getStartTime(), TUESDAY_10AM_2HR_LESSON.getStartTime());
        assertEquals(MONDAY_10AM_2HR_LESSON.getEndTime(), TUESDAY_10AM_2HR_LESSON.getEndTime());
        assertNotEquals(MONDAY_10AM_2HR_LESSON.getDay(), TUESDAY_10AM_2HR_LESSON.getDay());
        assertDoesNotThrow(() -> timetable.addCommitment(MONDAY_10AM_2HR_LESSON));
        assertDoesNotThrow(() -> timetable.addCommitment(TUESDAY_10AM_2HR_LESSON));
    }

    @Test
    public void initialise_lessonsSameDaySubsetClash_throwsLessonClashException() {
        Timetable timetable = new Timetable();
        assertDoesNotThrow(() -> timetable.addCommitment(TUESDAY_10AM_2HR_LESSON));
        assertThrows(CommitmentClashException.class, () -> timetable.addCommitment(TUESDAY_10AM_1HR_LESSON));
        ArrayList<HourBlock> tuesdayClasses = timetable.getTuesdayClasses();
        testSlotsFalse(2, 2, tuesdayClasses);
        testSlotsTrue(4, 10, tuesdayClasses);
        testSlotsTrue(0, 2, tuesdayClasses);
    }

    @Test
    public void initialise_lessonsSameDaySupersetClash_throwsLessonClashException() {
        Timetable timetable = new Timetable();
        assertDoesNotThrow(() -> timetable.addCommitment(TUESDAY_10AM_1HR_LESSON));
        assertThrows(CommitmentClashException.class, () -> timetable.addCommitment(TUESDAY_10AM_2HR_LESSON));
        testSlotsFalse(2, 1, timetable.getTuesdayClasses());
        testSlotsTrue(3, 11, timetable.getTuesdayClasses());
        testSlotsTrue(0, 2, timetable.getTuesdayClasses());
    }

    @Test
    public void initialise_lessonsSameDayIntersectionExistDifferentEndpoints_throwsLessonClashException() {
        Timetable timetable = new Timetable();
        timetable.addCommitment(THURSDAY_4PM_2HR_LESSON);
        ArrayList<HourBlock> thursdayClasses = timetable.getThursdayClasses();
        assertThrows(CommitmentClashException.class, () -> timetable.addCommitment(THURSDAY_5PM_4HR_LESSON));
        testSlotsTrue(0, 6, thursdayClasses);
        testSlotsFalse(8, 2, thursdayClasses);
        testSlotsTrue(10, 4, thursdayClasses);
    }

    @Test
    public void equalityCheck_sameTimetable_equals() {
        Timetable timetable1 = new Timetable();
        timetable1.addCommitment(WEDNESDAY_2PM_2HR_LESSON);
        timetable1.addCommitment(FRIDAY_8AM_1HR_LESSON);
        timetable1.addCommitment(THURSDAY_11AM_2HR_LESSON);
        timetable1.addCommitment(THURSDAY_4PM_2HR_LESSON);

        Timetable timetable2 = new Timetable();
        timetable2.addCommitment(THURSDAY_4PM_2HR_LESSON);
        timetable2.addCommitment(WEDNESDAY_2PM_2HR_LESSON);
        timetable2.addCommitment(THURSDAY_11AM_2HR_LESSON);
        timetable2.addCommitment(FRIDAY_8AM_1HR_LESSON);

        assertEquals(timetable1.toString(), timetable2.toString());
        assertEquals(timetable1.getSchedule(), timetable2.getSchedule());
        assertEquals(timetable1.getWednesdayClasses(), timetable2.getWednesdayClasses());
        assertEquals(timetable1.getThursdayClasses(), timetable2.getThursdayClasses());
        assertEquals(timetable1.getFridayClasses(), timetable2.getFridayClasses());
    }

    public boolean arrayListAllEqualsTest(ArrayList<HourBlock> timeSlots1, ArrayList<HourBlock> timeSlots2) {
        for (int i = 0; i < timeSlots1.size(); i++) {
            if (!timeSlots1.get(i).equals(timeSlots2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void equalityCheck_slightlyDifferentTimetable_notEquals() {
        Timetable timetable1 = new Timetable();
        timetable1.addCommitment(WEDNESDAY_2PM_2HR_LESSON);
        timetable1.addCommitment(FRIDAY_7PM_3HR_LESSON);
        timetable1.addCommitment(THURSDAY_11AM_2HR_LESSON);
        timetable1.addCommitment(THURSDAY_4PM_2HR_LESSON);

        Timetable timetable2 = new Timetable();
        timetable2.addCommitment(THURSDAY_4PM_2HR_LESSON);
        timetable2.addCommitment(WEDNESDAY_2PM_2HR_LESSON);
        timetable2.addCommitment(THURSDAY_11AM_2HR_LESSON);
        timetable2.addCommitment(FRIDAY_8AM_1HR_LESSON);

        assertNotEquals(timetable1.getSchedule(), timetable2.getSchedule());
        assertTrue(arrayListAllEqualsTest(timetable1.getWednesdayClasses(), timetable2.getWednesdayClasses()));
        assertTrue(arrayListAllEqualsTest(timetable1.getThursdayClasses(), timetable2.getThursdayClasses()));
        assertFalse(arrayListAllEqualsTest(timetable1.getFridayClasses(), timetable2.getFridayClasses()));

    }

    @Test
    public void merge_zeroConflictTimetables_mergedTimetable() {
        Timetable timeTable = new Timetable();
        timeTable.addCommitment(MONDAY_8AM_2HR_LESSON);
        timeTable.addCommitment(TUESDAY_10AM_2HR_LESSON);
        timeTable.addCommitment(WEDNESDAY_10AM_3HR_LESSON);
        timeTable.addCommitment(WEDNESDAY_6PM_1HR_LESSON);
        timeTable.addCommitment(THURSDAY_4PM_2HR_LESSON);
        timeTable.addCommitment(FRIDAY_7PM_3HR_LESSON);

        Timetable timeTable2 = new Timetable();
        timeTable2.addCommitment(MONDAY_10AM_2HR_LESSON);
        timeTable2.addCommitment(TUESDAY_2PM_2HR_LESSON);
        timeTable2.addCommitment(WEDNESDAY_4PM_1HR_LESSON);
        timeTable2.addCommitment(THURSDAY_9PM_1HR_LESSON);
        timeTable2.addCommitment(FRIDAY_8AM_1HR_LESSON);
        timeTable2.addCommitment(FRIDAY_9AM_2HR_LESSON);

        assertDoesNotThrow(() -> timeTable.mergeTimetable(timeTable2));

        // check free slots or not
        testSlotsFalse(0, 4, timeTable.getMondayClasses());
        testSlotsTrue(4, 10, timeTable.getMondayClasses());

        testSlotsTrue(0, 2, timeTable.getTuesdayClasses());
        testSlotsFalse(2, 2, timeTable.getTuesdayClasses());
        testSlotsTrue(4, 2, timeTable.getTuesdayClasses());
        testSlotsFalse(6, 2, timeTable.getTuesdayClasses());
        testSlotsTrue(8, 6, timeTable.getTuesdayClasses());

        testSlotsTrue(0, 2, timeTable.getWednesdayClasses());
        testSlotsFalse(2, 3, timeTable.getWednesdayClasses());
        testSlotsTrue(5, 3, timeTable.getWednesdayClasses());
        testSlotsFalse(8, 1, timeTable.getWednesdayClasses());
        testSlotsTrue(9, 1, timeTable.getWednesdayClasses());
        testSlotsFalse(10, 1, timeTable.getWednesdayClasses());
        testSlotsTrue(11, 3, timeTable.getWednesdayClasses());

        testSlotsTrue(0, 8, timeTable.getThursdayClasses());
        testSlotsFalse(8, 2, timeTable.getThursdayClasses());
        testSlotsTrue(10, 3, timeTable.getThursdayClasses());
        testSlotsFalse(13, 1, timeTable.getThursdayClasses());

        testSlotsFalse(0, 3, timeTable.getFridayClasses());
        testSlotsTrue(3, 8, timeTable.getFridayClasses());
        testSlotsFalse(11, 3, timeTable.getFridayClasses());
    }

    @Test
    public void merge_zeroConflictDenseTimetables_mergedTimetable() {
        Timetable timeTable = new Timetable();
        Timetable timetable2 = new Timetable();
        assertDoesNotThrow(() -> timeTable.mergeTimetable(timetable2));
    }

    @Test
    public void merge_fullConflictTimetables_assertionThrown() {
        assertThrows(AssertionError.class, () -> FULL_CONFLICT_TIMETABLE_A.mergeTimetable(FULL_CONFLICT_TIMETABLE_B));
    }

}
