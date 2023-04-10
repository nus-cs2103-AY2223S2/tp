package wingman.model.pilot.exceptions;

/**
 * Signals that the pilot cannot be found in the list.
 */
public class PilotNotFoundException extends RuntimeException {
    public PilotNotFoundException() {
        super("Plane not found in the list.");
    }
}
