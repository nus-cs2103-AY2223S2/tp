package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class LessonDonePredicateTest {

    private final Lesson lesson1 = new Lesson("Maths", LocalDateTime.of(2022, 1, 1, 12, 0),
        LocalDateTime.of(2022, 1, 1, 13, 0));
    private final Lesson lesson2 = new Lesson("Science", LocalDateTime.of(2023, 9, 15, 9, 0),
        LocalDateTime.of(2023, 9, 15, 10, 0));

    @Test
    public void test_lessonDonePredicate_returnsTrue() {
        // lesson has already started
        LessonDonePredicate predicate = new LessonDonePredicate("done");
        assertTrue(predicate.test(lesson1));

        // lesson has not started yet
        predicate = new LessonDonePredicate("not done");
        assertTrue(predicate.test(lesson2));
    }

    @Test
    public void test_lessonDonePredicate_returnsFalse() {
        // lesson has not started yet
        LessonDonePredicate predicate = new LessonDonePredicate("done");
        assertFalse(predicate.test(lesson2));

        // lesson has already ended
        predicate = new LessonDonePredicate("not done");
        assertFalse(predicate.test(lesson1));
    }
}
