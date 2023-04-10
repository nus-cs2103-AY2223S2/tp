package seedu.address.model.entity.shop.exception;

/**
 * Signals that there is no previous state to undo to.
 */
public class NoPrevStateException extends Exception {
    public NoPrevStateException() {
        super("No previous state to undo to!");
    }
}
