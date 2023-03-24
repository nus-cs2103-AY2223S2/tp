package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void equals() {
        Task task = new Task("Hello");

        // same object -> returns true
        assertTrue(task.equals(task));

        // same values -> returns true
        Task taskCopy = new Task(task.value);
        assertTrue(task.equals(taskCopy));

        // different types -> returns false
        assertFalse(task.equals(1));

        // null -> returns false
        assertFalse(task.equals(null));

        // different task -> returns false
        Task differentTask = new Task("Bye");
        assertFalse(task.equals(differentTask));
    }
}
