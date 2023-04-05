package seedu.task.model.task.exceptions;

/**
 * Signals that the amount of effort user keyed in is invalid.
 */
public class InvalidEffortException extends RuntimeException {

    public static final String INVALID_EFFORT_MESSAGE = "Effort entered for task must not be negative";

    public InvalidEffortException() {
        super(INVALID_EFFORT_MESSAGE);
    }
}
