package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that the operation will result in duplicate Sessions (Sessions are considered duplicates if they
 * are under same Group and have same session name).
 */
public class DuplicateSessionException extends RuntimeException {
    public DuplicateSessionException() {
        super("Operation would result in duplicate Sessions");
    }
}
