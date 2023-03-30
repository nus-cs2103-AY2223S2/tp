package seedu.address.model.exceptions;

/**
 * Represents a failure to undo or redo InputHistory command inputs, due to their absence.
 */
public class InputHistoryTimelineException extends Exception {

    public InputHistoryTimelineException(String message) {
        super(message);
    }
}
