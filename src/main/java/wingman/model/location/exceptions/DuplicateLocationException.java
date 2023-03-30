package wingman.model.location.exceptions;

/**
 * Signals that the operation would result in duplicate locations.
 */
public class DuplicateLocationException extends RuntimeException {
    public DuplicateLocationException() {
        super("Operation would result in duplicate locations");
    }
}
