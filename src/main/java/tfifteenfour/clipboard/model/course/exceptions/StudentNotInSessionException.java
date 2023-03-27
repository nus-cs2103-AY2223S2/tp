package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that a student is not in the session.
 */
public class StudentNotInSessionException extends RuntimeException {
    public StudentNotInSessionException() {
        super("Can't find such student in current session.");
    }
}
