package seedu.address.logic.recommender.timing.exceptions;

/**
 * Represents an error where timing is not suitable for a module to fit into a time slot.
 */
public class CommitmentClashException extends RuntimeException {
    public CommitmentClashException(String errorMessage) {
        super(errorMessage);
    }
}
