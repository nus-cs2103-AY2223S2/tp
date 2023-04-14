package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class LessonBelongsToDatePredicateTest {
    private final LessonBelongsToDatePredicate predicate;

    public LessonBelongsToDatePredicateTest() {
        predicate = new LessonBelongsToDatePredicate(LocalDate.of(2022, 12, 31));
    }

    @Test
    public void test_dateMatches_returnsTrue() {
        // Lesson on the same date as target date
        Lesson lesson1 = new Lesson("Math Lesson", LocalDateTime.of(2022, 12, 31,
            8, 0), LocalDateTime.of(2022, 12, 31, 10, 0));
        assertTrue(predicate.test(lesson1));

        // Lesson on a different time on the same date as target date
        Lesson lesson2 = new Lesson("English Lesson", LocalDateTime.of(2022, 12, 31,
            12, 0), LocalDateTime.of(2022, 12, 31, 14, 0));
        assertTrue(predicate.test(lesson2));
    }

    @Test
    public void test_dateNotMatches_returnsFalse() {
        // Lesson on a different date
        Lesson lesson1 = new Lesson("Math Lesson", LocalDateTime.of(2023, 1, 1,
            8, 0), LocalDateTime.of(2023, 1, 1, 10, 0));
        assertFalse(predicate.test(lesson1));

        // Lesson on a different time on a different date
        Lesson lesson2 = new Lesson("English Lesson", LocalDateTime.of(2022, 12, 30,
            12, 0), LocalDateTime.of(2022, 12, 30, 14, 0));
        assertFalse(predicate.test(lesson2));
    }
}

