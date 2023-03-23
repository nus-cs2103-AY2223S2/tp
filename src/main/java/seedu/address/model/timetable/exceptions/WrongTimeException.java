package seedu.address.model.timetable.exceptions;

/**
 * Represents a timing error.
 */
public class WrongTimeException extends RuntimeException {
    public WrongTimeException(String errorMessage) {
        super(errorMessage);
    }
}
