package seedu.address.model.recommender.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateRecommendationException extends RuntimeException {
    public DuplicateRecommendationException() {
        super("Operation would result in duplicate persons");
    }
}
