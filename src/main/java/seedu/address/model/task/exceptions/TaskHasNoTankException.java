package seedu.address.model.task.exceptions;

import seedu.address.model.task.Task;

/**
 * Signals that a Task instance's Tank is being accessed even if it has no Tank
 */
public class TaskHasNoTankException extends RuntimeException {
    public TaskHasNoTankException(Task task) {
        super("This task has no tank: " + task.toString());
    }
}
