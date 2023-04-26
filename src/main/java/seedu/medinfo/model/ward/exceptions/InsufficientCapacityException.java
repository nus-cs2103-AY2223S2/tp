package seedu.medinfo.model.ward.exceptions;

/**
 * Signals that the operation will result in an edited Ward with insufficient
 * capacity.
 */
public class InsufficientCapacityException extends RuntimeException {
    public InsufficientCapacityException() {
        super("Operation would result in the edited ward having insufficient capacity!");
    }
}
