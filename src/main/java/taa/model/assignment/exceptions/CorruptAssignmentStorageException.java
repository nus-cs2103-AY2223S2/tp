package taa.model.assignment.exceptions;

/**
 * Exception when the storage string for assignments is invalid.
 */
public class CorruptAssignmentStorageException extends AssignmentException {
    public CorruptAssignmentStorageException(String message) {
        super(message);
    }
}
