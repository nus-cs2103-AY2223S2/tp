package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.TypicalDeadlines;
import seedu.task.testutil.TypicalEvents;
import seedu.task.testutil.TypicalLocalDates;
import seedu.task.testutil.TypicalTasks;

public class EventListTest {
    @Test
    public void filter_test() {
        EventList list = new EventList();

        // Select SimpleTasks -> return false
        assertFalse(list.isCorrectType(TypicalTasks.ALICE, TypicalLocalDates.LOCAL_DATE_THIRD));

        // Select Deadline -> return false
        assertFalse(list.isCorrectType(TypicalDeadlines.PROJECT, TypicalLocalDates.LOCAL_DATE_FIFTH));

        // Select Event, date is first/last day of event -> return true
        assertTrue(list.isCorrectType(TypicalEvents.SLEEPOVER, TypicalLocalDates.APR30));
        assertTrue(list.isCorrectType(TypicalEvents.EXAM, TypicalLocalDates.APR26));

        // Select Event, date is outside of event dates -> return false
        assertFalse(list.isCorrectType(TypicalEvents.REST, TypicalLocalDates.APR30));
    }
}
