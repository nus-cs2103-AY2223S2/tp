package tfifteenfour.clipboard.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate tasks (tasks are considered duplicates if they
 * are under same Group and have same task name).
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
