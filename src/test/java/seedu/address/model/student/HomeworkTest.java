package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class HomeworkTest {
    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        assertThrows(NullPointerException.class, () -> new Homework(null, deadline));
    }

    @Test
    public void constructor_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Homework("Math homework", null));
    }

    @Test
    public void getDescription_validDescription_returnsDescription() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        assertEquals("Math homework", homework.getDescription());
    }

    @Test
    public void getDeadline_validDeadline_returnsDeadline() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        assertEquals(deadline, homework.getDeadline());
    }

    @Test
    public void getStatus_defaultStatus_returnsPending() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        assertEquals(Homework.Status.PENDING, homework.getStatus());
    }

    @Test
    public void isCompleted_completedStatus_returnsTrue() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        homework.markAsDone();
        assertTrue(homework.isCompleted());
    }

    @Test
    public void markAsDone_setStatusToCompleted_statusIsCompleted() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        homework.markAsDone();
        assertEquals(Homework.Status.COMPLETED, homework.getStatus());
    }

    @Test
    public void markAsNotDone_setStatusToPending_statusIsPending() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        homework.markAsDone();
        homework.markAsUndone();
        assertEquals(Homework.Status.PENDING, homework.getStatus());
    }

    @Test
    public void getStatusTag_pendingStatus_returnsOpenBracket() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        assertEquals("[ ]", homework.getStatusTag());
    }

    @Test
    public void getStatusTag_completedStatus_returnsClosedBracket() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 31, 23, 59);
        Homework homework = new Homework("Math homework", deadline);
        homework.markAsDone();
        assertEquals("[X]", homework.getStatusTag());
    }

    @Test
    public void equals_sameDescriptionDifferentDeadline_returnsFalse() {
        LocalDateTime deadline1 = LocalDateTime.of(2023, 3, 15, 23, 59);
        LocalDateTime deadline2 = LocalDateTime.of(2023, 3, 16, 23, 59);
        Homework homework1 = new Homework("Complete Math Homework", deadline1);
        Homework homework2 = new Homework("Complete Math Homework", deadline2);
        assertFalse(homework1.equals(homework2));
    }

    @Test
    public void isSameHomework_sameObject_returnsTrue() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 15, 23, 59);
        Homework homework = new Homework("Complete Math Homework", deadline);
        assertTrue(homework.isSameHomework(homework));
    }

    @Test
    public void isCompleted_homeworkMarkedAsDone_returnsTrue() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 15, 23, 59);
        Homework homework = new Homework("Complete Math Homework", deadline);
        homework.markAsDone();
        assertTrue(homework.isCompleted());
    }

    @Test
    public void markAsNotDone_homeworkMarkedAsDoneThenMarkedAsNotDone_returnsFalse() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 15, 23, 59);
        Homework homework = new Homework("Complete Math Homework", deadline);
        homework.markAsDone();
        homework.markAsUndone();
        assertFalse(homework.isCompleted());
    }

    @Test
    public void getStatusTag_homeworkCompleted_returnsX() {
        LocalDateTime deadline = LocalDateTime.of(2023, 3, 15, 23, 59);
        Homework homework = new Homework("Complete Math Homework", deadline);
        homework.markAsDone();
        assertEquals(homework.getStatusTag(), "[X]");
    }
}
