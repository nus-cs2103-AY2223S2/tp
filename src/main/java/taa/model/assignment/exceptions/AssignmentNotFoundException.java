package taa.model.assignment.exceptions;

/**
 * An exception where the assignment is not found.
 */
public class AssignmentNotFoundException extends AssignmentException {
    public AssignmentNotFoundException(String assignmentName) {
        super(String.format("Assignment %s could not be found", assignmentName));
    }
}
