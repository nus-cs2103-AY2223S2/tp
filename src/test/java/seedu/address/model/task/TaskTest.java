package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.ONE;
import static seedu.address.testutil.TypicalTasks.THREE;
import static seedu.address.testutil.TypicalTasks.TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {


    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ONE.isSameTask(ONE));

        // null -> returns false
        assertFalse(ONE.isSameTask(null));

        // name has trailing spaces, all other attributes same -> returns false
        String taskDescriptionWithTrailingSpaces = "eat berries" + " ";
        Task editedSecond = new TaskBuilder(TWO)
                .withTaskDescription(taskDescriptionWithTrailingSpaces).build();
        assertFalse(TWO.isSameTask(editedSecond));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task firstCopy = new TaskBuilder(ONE).build();
        assertTrue(ONE.equals(firstCopy));

        // same object -> returns true
        assertTrue(ONE.equals(ONE));

        // null -> returns false
        assertFalse(ONE.equals(null));

        // different type -> returns false
        assertFalse(ONE.equals(5));

        // different person -> returns false
        assertFalse(ONE.equals(THREE));

        // different task description -> returns false
        Task editedFirst = new TaskBuilder(ONE).withTaskDescription("kill Mufasa").build();
        assertFalse(ONE.equals(editedFirst));

    }
}

