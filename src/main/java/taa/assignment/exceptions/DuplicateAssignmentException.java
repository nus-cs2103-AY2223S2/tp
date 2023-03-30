package taa.assignment.exceptions;

public class DuplicateAssignmentException extends AssignmentException {
    public DuplicateAssignmentException(String assignmentName) {
        super("Duplicate assignment name:  " + assignmentName);
    }
}
