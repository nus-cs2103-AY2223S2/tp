package seedu.address.model.task.exceptions;

import seedu.address.model.task.Task;

/**
 * Signals that a Task instance's Tank is being accessed even if it has no Tank
 */
public class TaskHasNoPriorityException extends RuntimeException {
    public TaskHasNoPriorityException(Task task) {
        super("This task has no priority: " + task.toString());
    }
}
