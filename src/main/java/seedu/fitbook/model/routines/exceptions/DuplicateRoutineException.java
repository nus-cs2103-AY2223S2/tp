package seedu.fitbook.model.routines.exceptions;

/**
 * Signals that the operation will result in duplicate Routines (Routines are considered duplicates
 * if they have the same identity).
 */
public class DuplicateRoutineException extends RuntimeException {
    public DuplicateRoutineException() {
        super("Operation would result in duplicate routines");
    }
}
