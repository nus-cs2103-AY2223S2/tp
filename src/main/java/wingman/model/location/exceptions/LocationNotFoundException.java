package wingman.model.location.exceptions;

/**
 * Signals that the location cannot be found in the list.
 */
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException() {
        super("Location not found in the list. ");
    }
}
