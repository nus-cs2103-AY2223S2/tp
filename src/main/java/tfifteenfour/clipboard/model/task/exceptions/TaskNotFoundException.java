package tfifteenfour.clipboard.model.task.exceptions;

/**
 * Signals that the operation is unable to find the specified task.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Can't find such task in current group.");
    }
}
