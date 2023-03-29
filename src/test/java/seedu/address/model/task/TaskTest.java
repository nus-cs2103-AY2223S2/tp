package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.VALID_COMPLETE_TASK;
import static seedu.address.testutil.TypicalTasks.VALID_LATE_TASK;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_1;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void firstConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void secondConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, null));
    }

    @Test
    public void thirdConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, null, null));
    }

    @Test
    public void markTaskAsComplete() {
        Task task = new TaskBuilder(VALID_TASK_1).build();
        task.markTaskAsComplete();
        assertTrue(task.getStatus().equals(TaskStatus.COMPLETE));
    }

    @Test
    public void markTaskAsLate() {
        Task task = new TaskBuilder(VALID_TASK_1).build();
        task.markTaskAsLate();
        assertTrue(task.getStatus().equals(TaskStatus.LATE));
    }

    @Test
    public void markTaskAsInProgress() {
        Task task = new TaskBuilder(VALID_LATE_TASK).build();
        task.markTaskAsInProgress();
        assertTrue(task.getStatus().equals(TaskStatus.INPROGRESS));
    }

    @Test
    public void isValidTaskName() {
        // null task name
        assertThrows(NullPointerException.class, () -> Task.isValidTaskName(null));

        // invalid task names
        assertFalse(Task.isValidTaskName("")); // empty string
        assertFalse(Task.isValidTaskName("  ")); // spaces only
        assertFalse(Task.isValidTaskName("...")); // only non-alphanumeric characters
        assertFalse(Task.isValidTaskName("Do Exercise 1.1")); // contains non-alphanumeric characters

        // valid task names
        assertTrue(Task.isValidTaskName("Complete Math 123")); // alphanumeric characters
        assertTrue(Task.isValidTaskName("Complete Math Exercise")); // alphabets only

    }

    @Test
    public void equals() {
        // same values -> returns true
        Task task1Copy = new TaskBuilder(VALID_TASK_1).build();
        assertTrue(VALID_TASK_1.equals(task1Copy));

        // same object -> returns true
        assertTrue(VALID_TASK_1.equals(VALID_TASK_1));

        // null -> returns false
        assertFalse(VALID_TASK_1.equals(null));

        // different type -> returns false
        assertFalse(VALID_TASK_1.equals(5));

        // different student -> returns false
        assertFalse(VALID_TASK_1.equals(VALID_TASK_2));

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(VALID_TASK_1).withName(VALID_TASK_NAME_2).build();
        assertFalse(VALID_TASK_1.equals(editedTask1));
    }

    @Test
    public void compareTo() {
        // a INPROGRESS task has higher priority than LATE task
        assertTrue(VALID_TASK_1.compareTo(VALID_LATE_TASK) < 0);

        // a LATE task has higher priority than COMPLETE task
        assertTrue(VALID_LATE_TASK.compareTo(VALID_COMPLETE_TASK) < 0);

        // a INPROGRESS task has higher priority than COMPLETE task
        assertTrue(VALID_TASK_1.compareTo(VALID_COMPLETE_TASK) < 0);
    }

    @Test
    public void testToString() {
        String expectedString = "Complete E Math Paper 1; Status: INPROGRESS";
        assertTrue(VALID_TASK_1.toString().equals(expectedString));
    }
}
