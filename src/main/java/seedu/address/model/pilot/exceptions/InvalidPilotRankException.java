package seedu.address.model.pilot.exceptions;

/**
 * The exception thrown when the pilot rank is invalid.
 */
public class InvalidPilotRankException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidPilotRankException} with the specified
     * detail {@code rank}.
     *
     * @param rank the rank that is invalid, either an enum or a string.
     */
    public InvalidPilotRankException(Object rank) {
        super("Invalid pilot rank" + rank.toString());
    }
}
