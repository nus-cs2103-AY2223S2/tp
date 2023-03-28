package tfifteenfour.clipboard.model.task.exceptions;

/**
 * Signals that a student is not in the tasks student page.
 */
public class StudentNotInPageException extends RuntimeException {
    public StudentNotInPageException() {
        super("Can't find such student in current page.");
    }
}
