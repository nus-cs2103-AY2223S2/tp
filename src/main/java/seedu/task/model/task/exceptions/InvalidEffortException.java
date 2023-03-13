package seedu.task.model.task.exceptions;

/**
 * Signals that the amount of effort user keyed in is invalid.
 */
public class InvalidEffortException extends RuntimeException {

    public InvalidEffortException() {
        super("Effort entered for task must not be negative");
    }
}
