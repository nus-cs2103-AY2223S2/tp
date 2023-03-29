package seedu.address.model.tank.exceptions;

/**
 * Signals that the operation will result in duplicate Tanks (Tanks are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTankException extends RuntimeException {
    public DuplicateTankException() {
        super("Operation would result in duplicate Tanks");
    }
}

