package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that the operation will result in duplicate Groups (Groups are considered duplicates if they
 * have the same group name).
 */
public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Operation would result in duplicate groups");
    }
}
