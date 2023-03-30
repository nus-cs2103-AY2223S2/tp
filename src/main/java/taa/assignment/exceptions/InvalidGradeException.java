package taa.assignment.exceptions;

/**
 * Grade has marks < 0 or > totalMarks.
 */
public class InvalidGradeException extends AssignmentException {
    public InvalidGradeException(String message) {
        super(message);
    }
}
