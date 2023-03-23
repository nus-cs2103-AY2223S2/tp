package seedu.address.model.scheduler.exceptions;

/**
 * Represents an error where timing is not suitable for a module to fit into a time slot.
 */
public class CommitmentClashException extends RuntimeException {
    public CommitmentClashException(String errorMessage) {
        super(errorMessage);
    }
}
