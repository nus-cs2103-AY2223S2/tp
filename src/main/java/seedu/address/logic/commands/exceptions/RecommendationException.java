package seedu.address.logic.commands.exceptions;

/**
 * A custom exception that is used to indicate error when making command recommendations
 */
public class RecommendationException extends Exception {
    /**
     * Constructs a new RecommendationException with null as its detail message.
     */
    public RecommendationException() {
        super();
    }

    /**
     * Constructs a new RecommendationException with the specified detail message.
     *
     * @param msg the detail message
     */
    public RecommendationException(String msg) {
        super(msg);
    }
}
