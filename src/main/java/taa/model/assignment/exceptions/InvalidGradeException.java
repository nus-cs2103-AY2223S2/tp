package taa.model.assignment.exceptions;

/**
 * Exception where the grade has marks < 0 or > totalMarks.
 */
public class InvalidGradeException extends AssignmentException {
    public InvalidGradeException(String message) {
        super(message);
    }
}
