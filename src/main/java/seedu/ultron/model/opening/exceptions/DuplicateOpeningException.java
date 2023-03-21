package seedu.ultron.model.opening.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateOpeningException extends RuntimeException {
    public DuplicateOpeningException() {
        super("Operation would result in duplicate openings");
    }
}
