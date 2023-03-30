package seedu.address.model.recommendation.exceptions;

/**
 * Signals that the operation is unable to find the meet up.
 */
public class DuplicateRecommendationException extends RuntimeException {
    public DuplicateRecommendationException() {
        super("Operation would result in duplicate recommendations");
    }
}
