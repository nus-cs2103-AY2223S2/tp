package seedu.address.model.ward.exceptions;

/**
 * Signals that the operation will result in an edited Ward with insufficient
 * capacity.
 */
public class EditedWardInsufficientCapacityException extends RuntimeException {
    public EditedWardInsufficientCapacityException() {
        super("Operation would result in the edited ward having insufficient capacity!");
    }
}
