package seedu.address.model.task.exceptions;

/**
 * Signals that the operation is unable to find the specified {@code Task}.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task not found");
    }
}
