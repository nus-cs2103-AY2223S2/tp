package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.TypicalDeadlines;
import seedu.task.testutil.TypicalEvents;
import seedu.task.testutil.TypicalLocalDates;
import seedu.task.testutil.TypicalTasks;

public class DeadlineListTest {
    @Test
    public void filter_test() {
        DeadlineList list = new DeadlineList();

        // Select SimpleTasks -> return false
        assertFalse(list.isCorrectType(TypicalTasks.ALICE, TypicalLocalDates.LOCAL_DATE_THIRD));

        // Select Deadline, Before due date -> return true
        assertTrue(list.isCorrectType(TypicalDeadlines.PROJECT, TypicalLocalDates.LOCAL_DATE_FIFTH));

        //Select Deadline, after due date -> return false
        assertFalse(list.isCorrectType(TypicalDeadlines.PROJECT, TypicalLocalDates.LOCAL_DATE_THIRD));

        // Select Event -> return false
        assertFalse(list.isCorrectType(TypicalEvents.MEETING, TypicalLocalDates.LOCAL_DATE_THIRD));
    }
}
