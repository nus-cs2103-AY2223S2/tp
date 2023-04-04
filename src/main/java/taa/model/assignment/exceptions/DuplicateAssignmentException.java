package taa.model.assignment.exceptions;

/**
 * Signals that this operation will result in a duplicate Assignment being added.
 */
public class DuplicateAssignmentException extends AssignmentException {
    public DuplicateAssignmentException(String assignmentName) {
        super("Duplicate assignment name:  " + assignmentName);
    }
}
