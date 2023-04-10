package tfifteenfour.clipboard.model.student.exceptions;

/**
 * Signals that the operation is unable to find the specified student.
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Can't find such student in current group.");
    }
}
