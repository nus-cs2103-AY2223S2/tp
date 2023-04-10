package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ExamPredicateTest {
    private final String validExamName = "valid name";
    private final ExamPredicate validPredicate = new ExamPredicate(validExamName);
    private final Exam validExam = new Exam(validExamName, LocalDateTime.now(), LocalDateTime.now(), null,
            null);
    private final Exam anotherExam = new Exam("another name", LocalDateTime.now(), LocalDateTime.now(),
            null, null);

    @Test
    public void constructor_validParams_success() throws Exception {
        try {
            new ExamPredicate(validExamName);
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    void test_checkIfExamMatches_returnsTrue() {
        assertTrue(validPredicate.test(validExam));
    }
    @Test
    void test_checkIfDifferentExamMatches_returnsFalse() {
        assertFalse(validPredicate.test(anotherExam));
    }

    @Test
    void equals_checkEqualsItself_returnsTrue() {
        assertEquals(validPredicate, validPredicate);
    }
}
