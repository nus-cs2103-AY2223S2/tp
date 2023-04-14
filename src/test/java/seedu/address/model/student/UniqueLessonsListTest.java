package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.exceptions.DuplicateLessonException;

public class UniqueLessonsListTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void addLesson_nullLesson_throwsNullPointerException() {
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        assertThrows(NullPointerException.class, () -> uniqueLessonsList.add(null));
    }

    @Test
    public void addLesson_duplicateLesson_throwsDuplicateLessonException() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 12, 0);
        Lesson lesson1 = new Lesson("Maths Lesson", startTime, endTime);
        Lesson lesson2 = new Lesson("Maths Lesson", startTime, endTime);
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        uniqueLessonsList.add(lesson1);
        assertThrows(DuplicateLessonException.class, () -> uniqueLessonsList.add(lesson2));
    }

    @Test
    public void containsLesson_nullLesson_throwsNullPointerException() {
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        assertThrows(NullPointerException.class, () -> uniqueLessonsList.contains(null));
    }

    @Test
    public void containsLesson_lessonNotInList_returnsFalse() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);
        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 6, 13, 0);
        Lesson lesson2 = new Lesson("English Lesson", startTime2, endTime2);
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        uniqueLessonsList.add(lesson1);
        assertFalse(uniqueLessonsList.contains(lesson2));
    }

    @Test
    public void containsLesson_lessonInList_returnsTrue() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);
        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 6, 13, 0);
        Lesson lesson2 = new Lesson("English Lesson", startTime2, endTime2);
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        uniqueLessonsList.add(lesson1);
        assertTrue(uniqueLessonsList.contains(lesson1));
    }

    @Test
    public void removeLesson_validLesson_removesLessonFromList() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        uniqueLessonsList.add(lesson1);
        assertTrue(uniqueLessonsList.contains(lesson1));
        uniqueLessonsList.remove(lesson1);
        assertFalse(uniqueLessonsList.contains(lesson1));
    }

    @Test
    public void size_addAndRemoveLessons_correctSize() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);
        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 6, 13, 0);
        Lesson lesson2 = new Lesson("English Lesson", startTime2, endTime2);
        LocalDateTime startTime3 = LocalDateTime.of(2022, 3, 6, 14, 0);
        LocalDateTime endTime3 = LocalDateTime.of(2022, 3, 6, 15, 0);
        Lesson lesson3 = new Lesson("Science Lesson", startTime3, endTime3);
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        uniqueLessonsList.add(lesson1);
        uniqueLessonsList.add(lesson2);
        uniqueLessonsList.add(lesson3);
        uniqueLessonsList.remove(lesson2);
        assertEquals(2, uniqueLessonsList.size());
    }

    @Test
    public void getSortedLessonsList_addLessons_returnsSortedList() {
        LocalDateTime startTime1 = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2022, 3, 6, 11, 0);
        Lesson lesson1 = new Lesson("Math Lesson", startTime1, endTime1);
        LocalDateTime startTime2 = LocalDateTime.of(2022, 3, 6, 12, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2022, 3, 6, 13, 0);
        Lesson lesson2 = new Lesson("English Lesson", startTime2, endTime2);
        LocalDateTime startTime3 = LocalDateTime.of(2022, 3, 6, 9, 0);
        LocalDateTime endTime3 = LocalDateTime.of(2022, 3, 6, 10, 0);
        Lesson lesson3 = new Lesson("Science Lesson", startTime3, endTime3);
        UniqueLessonsList uniqueLessonsList = new UniqueLessonsList();
        uniqueLessonsList.add(lesson1);
        uniqueLessonsList.add(lesson2);
        uniqueLessonsList.add(lesson3);
        assertEquals(lesson3, uniqueLessonsList.getSortedLessonsList().get(0));
        assertEquals(lesson1, uniqueLessonsList.getSortedLessonsList().get(1));
        assertEquals(lesson2, uniqueLessonsList.getSortedLessonsList().get(2));
    }
}
