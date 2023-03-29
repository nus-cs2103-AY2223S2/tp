package seedu.address.model.recommendation.exceptions;

/**
 * Signals that the operation will result in duplicate Recommendations
 * (Recommendations are considered duplicates if they have the same
 * identity).
 */
public class DuplicateRecommendationException extends RuntimeException {
    public DuplicateRecommendationException() {
        super("Operation would result in duplicate recommendations");
    }
}
