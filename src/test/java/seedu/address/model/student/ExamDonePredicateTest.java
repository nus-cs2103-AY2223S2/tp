package seedu.address.model.student;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExamDonePredicateTest {
    private final String done = "done";
    private final String undone = "not done";
    private final ExamDonePredicate validDonePredicate = new ExamDonePredicate(done);

    private final Exam unfinishedExam = new Exam("unfinished", LocalDateTime.MAX, LocalDateTime.MAX,
            null, null);
    private final Exam finishedExam = new Exam("finished", LocalDateTime.MIN, LocalDateTime.MIN,
            null, null);

    @Test
    void constructor_validParams_success() {
        try {
            new ExamDonePredicate(done);
            new ExamDonePredicate(undone);
        } catch (Exception e) {
            assert(false);
        }
    }
    @Test
    void test_checkIfUndoneExamIsDone_returnsFalse() {
        assertFalse(validDonePredicate.test(unfinishedExam));
    }
    @Test
    void test_checkIfDoneExamIsDone_returnsTrue() {
        assertTrue(validDonePredicate.test(finishedExam));
    }
    @Test
    void equals_checkIfEqualsWithItself_returnsTrue() {
        assertEquals(validDonePredicate, validDonePredicate);
    }
}
