package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalDeadlineTasks.FIRST;
import static seedu.address.testutil.TypicalDeadlineTasks.SECOND;
import static seedu.address.testutil.TypicalDeadlineTasks.THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeadlineTaskBuilder;

public class DeadlineTaskTest {


    @Test
    public void isSameDeadlineTask() {
        // same object -> returns true
        assertTrue(FIRST.isSameTask(FIRST));

        // null -> returns false
        assertFalse(FIRST.isSameTask(null));

        // same description, all other attributes different -> returns true
        DeadlineTask editedFirst = new DeadlineTaskBuilder(FIRST).withDate("03/04/2024").build();
        assertTrue(FIRST.isSameTask(editedFirst));

        // different description, all other attributes same -> returns false
        editedFirst = new DeadlineTaskBuilder(FIRST).withTaskDescription("kick rocks").build();
        assertFalse(FIRST.isSameTask(editedFirst));

        // name has trailing spaces, all other attributes same -> returns false
        String taskDescriptionWithTrailingSpaces = "eat berries" + " ";
        DeadlineTask editedSecond = new DeadlineTaskBuilder(SECOND)
                .withTaskDescription(taskDescriptionWithTrailingSpaces).build();
        assertFalse(SECOND.isSameTask(editedSecond));
    }

    @Test
    public void equals() {
        // same values -> returns true
        DeadlineTask firstCopy = new DeadlineTaskBuilder(FIRST).build();
        assertTrue(FIRST.equals(firstCopy));

        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));

        // different type -> returns false
        assertFalse(FIRST.equals(5));

        // different person -> returns false
        assertFalse(FIRST.equals(THIRD));

        // different task description -> returns false
        DeadlineTask editedFirst = new DeadlineTaskBuilder(FIRST).withTaskDescription("kill Mufasa").build();
        assertFalse(FIRST.equals(editedFirst));

        // different date -> returns false
        editedFirst = new DeadlineTaskBuilder(FIRST).withDate("01/06/2015").build();
        assertFalse(FIRST.equals(editedFirst));
    }
}
