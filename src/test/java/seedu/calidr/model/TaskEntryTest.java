package seedu.calidr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.calidr.model.task.params.Priority;

class TaskEntryTest {

    private final TaskEntry taskEntry = new TaskEntry();

    @Test
    void priority() {
        assertNull(taskEntry.getPriority());
        taskEntry.setPriority(Priority.LOW);
        assertEquals(Priority.LOW, taskEntry.getPriority());
    }

    @Test
    void isDone() {
        assertFalse(taskEntry.getIsDone());
        taskEntry.setIsDone(true);
        assertTrue(taskEntry.getIsDone());
    }

    @Test
    void createRecurrence() {
        TaskEntry recurrence = taskEntry.createRecurrence();
        assertEquals(taskEntry.getPriority(), recurrence.getPriority());
        assertEquals(taskEntry.getIsDone(), recurrence.getIsDone());
    }

    @Test
    void testToString() {
        taskEntry.setTitle("title");
        taskEntry.setPriority(Priority.LOW);
        taskEntry.setIsDone(true);
        assertEquals("Task Entry: title LOW true", taskEntry.toString());
    }
}
