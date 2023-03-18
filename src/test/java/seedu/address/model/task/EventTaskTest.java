package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEventTasks.FIRST;
import static seedu.address.testutil.TypicalEventTasks.SECOND;
import static seedu.address.testutil.TypicalEventTasks.THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventTaskBuilder;

public class EventTaskTest {


    @Test
    public void isSameEventTask() {
        // same object -> returns true
        assertTrue(FIRST.isSameTask(FIRST));

        // null -> returns false
        assertFalse(FIRST.isSameTask(null));

        // same description, all other attributes different -> returns true
        EventTask editedFirst = new EventTaskBuilder(FIRST).withStartDate("03/04/2024")
                .withEndDate("20/06/2029").build();
        assertTrue(FIRST.isSameTask(editedFirst));

        // different description, all other attributes same -> returns false
        editedFirst = new EventTaskBuilder(FIRST).withTaskDescription("kick rocks").build();
        assertFalse(FIRST.isSameTask(editedFirst));

        // name has trailing spaces, all other attributes same -> returns false
        String taskDescriptionWithTrailingSpaces = "eat berries" + " ";
        EventTask editedSecond = new EventTaskBuilder(SECOND)
                .withTaskDescription(taskDescriptionWithTrailingSpaces).build();
        assertFalse(SECOND.isSameTask(editedSecond));
    }

    @Test
    public void equals() {
        // same values -> returns true
        EventTask firstCopy = new EventTaskBuilder(FIRST).build();
        assertTrue(FIRST.equals(firstCopy));

        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));

        // different type -> returns false
        assertFalse(FIRST.equals(5));

        // different task -> returns false
        assertFalse(FIRST.equals(THIRD));

        // different task description -> returns false
        EventTask editedFirst = new EventTaskBuilder(FIRST).withTaskDescription("kill Mufasa").build();
        assertFalse(FIRST.equals(editedFirst));

        // different start date -> returns false
        editedFirst = new EventTaskBuilder(FIRST).withStartDate("01/06/2015").build();
        assertFalse(FIRST.equals(editedFirst));

        // different end date -> returns false
        editedFirst = new EventTaskBuilder(FIRST).withEndDate("01/06/2015").build();
        assertFalse(FIRST.equals(editedFirst));
    }
}
