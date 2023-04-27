package seedu.address.model.event.exceptions;

/**
 * Ensures added note has illegal length.
 */
public class NoteLengthException extends RuntimeException {
    public NoteLengthException(int limit, int length) {
        super(String.format("You have longer than limit: %1$s note length: %2$s!", limit, length));
    }
}
