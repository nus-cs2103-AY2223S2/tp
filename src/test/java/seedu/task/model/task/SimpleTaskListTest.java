package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.TypicalDeadlines;
import seedu.task.testutil.TypicalEvents;
import seedu.task.testutil.TypicalLocalDates;
import seedu.task.testutil.TypicalTasks;

public class SimpleTaskListTest {

    @Test
    public void filter_test() {
        SimpleTaskList list = new SimpleTaskList();

        // Select SimpleTasks -> return true
        assertTrue(list.isCorrectType(TypicalTasks.ALICE, TypicalLocalDates.LOCAL_DATE_THIRD));

        // Select Deadline -> return false
        assertFalse(list.isCorrectType(TypicalDeadlines.PROJECT, TypicalLocalDates.LOCAL_DATE_THIRD));

        // Select Event -> return false
        assertFalse(list.isCorrectType(TypicalEvents.MEETING, TypicalLocalDates.LOCAL_DATE_THIRD));
    }
}
