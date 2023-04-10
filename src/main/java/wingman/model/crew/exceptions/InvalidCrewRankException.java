package wingman.model.crew.exceptions;

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
        super(String.format(
                "%s is an invalid crew rank.\n"
                        + "Please try 0 for a Cabin Service Director, "
                        + "1 for a Senior Flight Attendant,\n"
                        + "2 for a Flight Attendant, "
                        + "or 3 for a Trainee.",
                rank.toString()));
    }
}
