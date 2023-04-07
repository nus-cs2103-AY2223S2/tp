package seedu.address.model.entity.shop.exception;

/**
 * Signals that there is no next state to redo to.
 */
public class NoNextStateException extends Exception {
    public NoNextStateException() {
        super("No next state to redo to!");
    }
}
