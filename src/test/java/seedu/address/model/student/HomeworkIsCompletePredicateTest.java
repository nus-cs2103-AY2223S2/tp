package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class HomeworkIsCompletePredicateTest {

    @Test
    public void test_isComplete_returnsTrue() {
        HomeworkIsCompletePredicate predicate = new HomeworkIsCompletePredicate(true);
        Homework homework = new Homework("CS2103T Quiz", LocalDateTime.parse("2021-03-31T23:59:00"));
        homework.markAsDone();
        assertTrue(predicate.test(homework));
    }

    @Test
    public void test_isComplete_returnsFalse() {
        HomeworkIsCompletePredicate predicate = new HomeworkIsCompletePredicate(true);
        Homework homework = new Homework("CS2103T Quiz", LocalDateTime.parse("2021-03-31T23:59:00"));
        assertFalse(predicate.test(homework));
    }

    @Test
    public void test_isNotComplete_returnsTrue() {
        HomeworkIsCompletePredicate predicate = new HomeworkIsCompletePredicate(false);
        Homework homework = new Homework("CS2103T Quiz", LocalDateTime.parse("2021-03-31T23:59:00"));
        assertTrue(predicate.test(homework));
    }

    @Test
    public void test_isNotComplete_returnsFalse() {
        HomeworkIsCompletePredicate predicate = new HomeworkIsCompletePredicate(false);
        Homework homework = new Homework("CS2103T Quiz", LocalDateTime.parse("2021-03-31T23:59:00"));
        homework.markAsDone();
        assertFalse(predicate.test(homework));
    }
}
