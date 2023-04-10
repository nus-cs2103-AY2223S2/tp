package seedu.address.model.tank.readings.exceptions;

/**
 * Signals that the operation will result in duplicate Readings (Readings are considered duplicates if they have
 * the same identity).
 */
public class DuplicateReadingException extends RuntimeException {
    public DuplicateReadingException() {
        super("Operation would result in Readings on the same day");
    }
}
