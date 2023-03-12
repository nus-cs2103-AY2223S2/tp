package seedu.calidr.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.calidr.testutil.TypicalTasks.EVENT1;
import static seedu.calidr.testutil.TypicalTasks.TODO1;

import org.junit.jupiter.api.Test;

import seedu.calidr.model.TaskEntry;

class TaskEntryUtilTest {
    @Test
    void convert() {
        TaskEntry todoEntry = TaskEntryUtil.convert(TODO1);

        assertEquals(todoEntry.getTitle(), TODO1.getDescription());
        assertEquals(todoEntry.getPriority(), TODO1.getPriority());
        assertEquals(todoEntry.getIsDone(), TODO1.isDone());

        TaskEntry deadlineEntry = TaskEntryUtil.convert(TODO1);

        assertEquals(deadlineEntry.getTitle(), TODO1.getDescription());
        assertEquals(deadlineEntry.getInterval().getStartDateTime(), TODO1.getBy());
        assertEquals(deadlineEntry.getInterval().getEndDateTime(), TODO1.getBy().plusHours(1));
        assertEquals(deadlineEntry.getPriority(), TODO1.getPriority());
        assertEquals(deadlineEntry.getIsDone(), TODO1.isDone());

        TaskEntry eventEntry = TaskEntryUtil.convert(EVENT1);

        assertEquals(eventEntry.getTitle(), EVENT1.getDescription());
        assertEquals(eventEntry.getInterval().getStartDateTime(), EVENT1.getFrom());
        assertEquals(eventEntry.getInterval().getEndDateTime(), EVENT1.getTo());
        assertEquals(eventEntry.getPriority(), EVENT1.getPriority());
        assertEquals(eventEntry.getIsDone(), EVENT1.isDone());
    }
}
