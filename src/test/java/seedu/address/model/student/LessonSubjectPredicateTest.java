package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class LessonSubjectPredicateTest {

    @Test
    public void testLessonSubjectPredicate_matchesSubject_returnsTrue() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 12, 0);
        // Set up test data
        Lesson lesson = new Lesson("Maths Lesson", startTime, endTime);
        LessonSubjectPredicate predicate = new LessonSubjectPredicate("Maths");

        // Test predicate
        boolean result = predicate.test(lesson);

        // Verify result
        assertTrue(result);
    }

    @Test
    public void testLessonSubjectPredicate_doesNotMatchSubject_returnsFalse() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 12, 0);
        // Set up test data
        Lesson lesson = new Lesson("Science Lesson", startTime, endTime);
        LessonSubjectPredicate predicate = new LessonSubjectPredicate("Maths");

        // Test predicate
        boolean result = predicate.test(lesson);

        // Verify result
        assertFalse(result);
    }

    @Test
    public void testLessonSubjectPredicate_partialMatch_returnsTrue() {
        LocalDateTime startTime = LocalDateTime.of(2022, 3, 6, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 3, 6, 12, 0);
        // Set up test data
        Lesson lesson = new Lesson("Maths 101 Lesson", startTime, endTime);
        LessonSubjectPredicate predicate = new LessonSubjectPredicate("Maths");

        // Test predicate
        boolean result = predicate.test(lesson);

        // Verify result
        assertTrue(result);
    }

}

