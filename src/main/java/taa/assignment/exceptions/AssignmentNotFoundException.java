package taa.assignment.exceptions;

/**
 * Assignment is not found.
 */
public class AssignmentNotFoundException extends AssignmentException {
    public AssignmentNotFoundException(String assignmentName) {
        super(String.format("Assignment %s could not be found", assignmentName));
    }
}
