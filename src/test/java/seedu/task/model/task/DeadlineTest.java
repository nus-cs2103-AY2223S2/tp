package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDeadlines.ASSIGNMENT;
import static seedu.task.testutil.TypicalDeadlines.RETURN_BOOK;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.DeadlineBuilder;

public class DeadlineTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new DeadlineBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameDeadline() {
        // same object -> returns true
        assertTrue(RETURN_BOOK.isSameTask(RETURN_BOOK));

        // null -> returns false
        assertFalse(RETURN_BOOK.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedReturn = new DeadlineBuilder(RETURN_BOOK).withDescription("Return book description 2")
                .withTags("Reminder2").withDate("2023-01-01 1800").build();
        assertTrue(RETURN_BOOK.isSameTask(editedReturn));

        // different name, all other attributes same -> returns false
        editedReturn = new DeadlineBuilder(RETURN_BOOK).withName("Assignment").build();
        assertFalse(RETURN_BOOK.isSameTask(editedReturn));

        // name differs in case, all other attributes same -> returns false
        Task editedAssignment = new DeadlineBuilder(ASSIGNMENT).withName("Assignment".toLowerCase()).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "Assignment" + " ";
        editedAssignment = new DeadlineBuilder(ASSIGNMENT).withName(nameWithTrailingSpaces).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task returnCopy = new DeadlineBuilder(RETURN_BOOK).build();
        assertTrue(RETURN_BOOK.equals(returnCopy));

        // same object -> returns true
        assertTrue(RETURN_BOOK.equals(RETURN_BOOK));

        // null -> returns false
        assertFalse(RETURN_BOOK.equals(null));

        // different type -> returns false
        assertFalse(RETURN_BOOK.equals(5));

        // different task -> returns false
        assertFalse(RETURN_BOOK.equals(ASSIGNMENT));

        // different name -> returns false
        Task editedReturn = new DeadlineBuilder(RETURN_BOOK).withName("Assignment").build();
        assertFalse(RETURN_BOOK.equals(editedReturn));

        // different description -> returns false
        editedReturn = new DeadlineBuilder(RETURN_BOOK).withDescription("Assignment description").build();
        assertFalse(RETURN_BOOK.equals(editedReturn));

        // different tags -> returns false
        editedReturn = new DeadlineBuilder(RETURN_BOOK).withTags("Important").build();
        assertFalse(RETURN_BOOK.equals(editedReturn));
    }
}
