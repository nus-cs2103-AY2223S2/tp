package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class LessonTest {

    @Test
    public void equals() {
        LocalDateTime start1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime start2 = LocalDateTime.of(2022, 3, 6, 11, 0);
        LocalDateTime end2 = LocalDateTime.of(2022, 3, 6, 13, 0);

        Lesson lesson1 = new Lesson("Maths Lesson", start1, end1);
        Lesson lesson2 = new Lesson("Science Lesson", start1, end1);
        Lesson lesson3 = new Lesson("Maths Lesson", start2, end2);
        Lesson lesson4 = new Lesson("Maths Lesson", start1, end1);

        // same object -> returns true
        assertTrue(lesson1.equals(lesson1));

        // same values -> returns true
        assertTrue(lesson1.equals(lesson4));

        // different types -> returns false
        assertFalse(lesson1.equals(1));

        // null -> returns false
        assertFalse(lesson1.equals(null));

        // different titles -> returns false
        assertFalse(lesson1.equals(lesson2));

        // different start times -> returns false
        assertFalse(lesson1.equals(lesson3));
    }

    @Test
    public void isSameTimeLesson() {
        LocalDateTime start1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime start2 = LocalDateTime.of(2022, 3, 6, 11, 0);
        LocalDateTime end2 = LocalDateTime.of(2022, 3, 6, 13, 0);
        LocalDateTime start3 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime end3 = LocalDateTime.of(2022, 3, 6, 14, 0);
        LocalDateTime start4 = LocalDateTime.of(2022, 3, 6, 8, 0);
        LocalDateTime end4 = LocalDateTime.of(2022, 3, 6, 10, 0);

        Lesson lesson1 = new Lesson("Maths Lesson", start1, end1);
        Lesson lesson2 = new Lesson("Science Lesson", start2, end2);
        Lesson lesson3 = new Lesson("History Lesson", start3, end3);
        Lesson lesson4 = new Lesson("Geography Lesson", start4, end4);

        // same object -> returns true
        assertTrue(lesson1.isSameTimeLesson(lesson1));

        // null -> returns false
        assertFalse(lesson1.isSameTimeLesson(null));

        // lessons with different timeslots -> returns false
        assertFalse(lesson1.isSameTimeLesson(lesson3));
        assertFalse(lesson1.isSameTimeLesson(lesson4));

        // lessons with overlapping timeslots -> returns true
        assertTrue(lesson1.isSameTimeLesson(lesson2));
        assertTrue(lesson1.isSameTimeLesson(new Lesson("Maths Lesson", start2, end3)));
        assertTrue(lesson1.isSameTimeLesson(new Lesson("Maths Lesson", start4, end1)));
    }

    @Test
    public void constructor_nullTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null, LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    public void constructor_nullStartTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson("Math Lesson", null, LocalDateTime.now()));
    }

    @Test
    public void constructor_nullEndTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson("Math Lesson", LocalDateTime.now(), null));
    }

    @Test
    public void isSameTimeLesson_sameLesson_returnsTrue() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime, endTime);

        assertTrue(lesson1.isSameTimeLesson(lesson1));
    }

    @Test
    public void isSameTimeLesson_differentLessonSameTime_returnsTrue() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime, endTime);
        Lesson lesson2 = new Lesson("English Lesson", startTime, endTime);

        assertTrue(lesson1.isSameTimeLesson(lesson2));
        assertTrue(lesson2.isSameTimeLesson(lesson1));
    }

    @Test
    public void isSameTimeLesson_differentLessonDifferentTime_returnsFalse() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);

        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 6, 13, 0);
        Lesson lesson2 = new Lesson("English Lesson", startTime2, endTime2);

        assertFalse(lesson1.isSameTimeLesson(lesson2));
        assertFalse(lesson2.isSameTimeLesson(lesson1));
    }

    @Test
    public void equals_sameLesson_returnsTrue() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime, endTime);
        Lesson lesson2 = new Lesson("Math Lesson", startTime, endTime);

        assertEquals(lesson1, lesson2);
        assertEquals(lesson2, lesson1);
    }
    @Test
    public void equals_null_returnsFalse() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime, endTime);

        assertFalse(lesson1.equals(null));
    }

    @Test
    public void equals_differentTitle_returnsFalse() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime, endTime);
        Lesson lesson2 = new Lesson("Science Lesson", startTime, endTime);

        assertFalse(lesson1.equals(lesson2));
        assertFalse(lesson2.equals(lesson1));
    }

    @Test
    public void equals_differentStartTime_returnsFalse() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);

        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 7, 10, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 7, 11, 0);
        Lesson lesson2 = new Lesson("Math Lesson", startTime2, endTime2);

        assertFalse(lesson1.equals(lesson2));
        assertFalse(lesson2.equals(lesson1));
    }

    @Test
    public void equals_differentEndTime_returnsFalse() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);

        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 6, 12, 0);
        Lesson lesson2 = new Lesson("Math Lesson", startTime2, endTime2);

        assertFalse(lesson1.equals(lesson2));
        assertFalse(lesson2.equals(lesson1));
    }

    @Test
    public void toString_validLesson_returnsExpectedString() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson = new Lesson("Math Lesson", startTime, endTime);

        String expectedString = "Math Lesson, starts: 2022-03-06 10:00, ends: 2022-03-06 11:00";
        assertEquals(expectedString, lesson.toString());
    }

    @Test
    public void equals_dameEndTime_returnTrue() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime, endTime);
        Lesson lesson2 = new Lesson("Math Lesson", startTime, endTime);

        assertEquals(lesson1, lesson2);
        assertEquals(lesson2, lesson1);
    }

}
