package seedu.address.model.crew.exceptions;

/**
 * The exception thrown when the pilot rank is invalid.
 */
public class InvalidCrewRankException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidPilotRankException} with the specified
     * detail {@code rank}.
     *
     * @param rank the rank that is invalid, either an enum or a string.
     */
    public InvalidCrewRankException(Object rank) {
        super("Invalid crew rank" + rank.toString());
    }
}
