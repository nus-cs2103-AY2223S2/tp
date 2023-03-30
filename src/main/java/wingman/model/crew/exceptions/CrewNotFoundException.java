package wingman.model.crew.exceptions;

/**
 * Signals that the crew cannot be found in the list.
 */
public class CrewNotFoundException extends RuntimeException {
    public CrewNotFoundException() {
        super("Crew not found in the list.");
    }
}
