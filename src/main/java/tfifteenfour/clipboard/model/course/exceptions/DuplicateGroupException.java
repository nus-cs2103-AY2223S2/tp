package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that the operation will result in duplicate Students (Students are considered duplicates if they
 * have the same identity).
 */
public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Operation would result in duplicate groups");
    }
}
