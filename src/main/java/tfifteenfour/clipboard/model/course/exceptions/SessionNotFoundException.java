package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that the operation is unable to find the specified session.
 */
public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException() {
        super("Can't find such session in current group.");
    }
}
