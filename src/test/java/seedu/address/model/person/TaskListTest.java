package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void isEmptyTaskList() {
        TaskList tasks = new TaskList();

        // same object -> returns true
        assertTrue(tasks.equals(tasks));

        // same values -> returns true
        TaskList tasksCopy = new TaskList();
        assertTrue(tasks.equals(tasksCopy));

        // different types -> returns false
        assertFalse(tasks.equals(1));

        // null -> returns false
        assertFalse(tasks.equals(null));

        // different task -> returns false
        TaskList differentTasks = new TaskList().add(new Task("bye"));
        assertFalse(tasks.equals(differentTasks));
    }
}
