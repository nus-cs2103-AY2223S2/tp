package seedu.address.model.time.exceptions;

/**
 * Represents a timing error.
 */
public class WrongTimeException extends RuntimeException {
    public WrongTimeException(String errorMessage) {
        super(errorMessage);
    }
}
