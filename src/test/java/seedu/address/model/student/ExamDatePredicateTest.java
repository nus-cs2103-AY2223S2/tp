package seedu.address.model.student;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExamDatePredicateTest {
    private final LocalDate validLocalDate = LocalDate.now();
    private final ExamDatePredicate validDatePredicate = new ExamDatePredicate(validLocalDate);
    private final Exam validExam = new Exam("valid", LocalDateTime.now(), LocalDateTime.now(), null,
            null);
    private final Exam lateExam = new Exam("unfinished", LocalDateTime.MAX, LocalDateTime.MAX,
            null, null);
    private final Exam earlyExam = new Exam("finished", LocalDateTime.MIN, LocalDateTime.MIN,
            null, null);
    @Test
    void constructor_validParams_success() {
        try {
            new ExamDatePredicate(validLocalDate);
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    void test_checkIfExamMatches_returnsTrue() {
        assertTrue(validDatePredicate.test(validExam));
    }

    @Test
    void test_checkIfExamMatches_returnsFalse() {
        assertFalse(validDatePredicate.test(earlyExam));
        assertFalse(validDatePredicate.test(lateExam));
    }

    @Test
    void equals_checkEqualsItself_returnsTrue() {
        assertEquals(validDatePredicate, validDatePredicate);
    }
}
