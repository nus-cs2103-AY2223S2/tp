package seedu.address.model.scheduler.time.exceptions;

/**
 * Represents a timing error.
 */
public class WrongTimeException extends RuntimeException {
    public WrongTimeException(String errorMessage) {
        super(errorMessage);
    }
}
