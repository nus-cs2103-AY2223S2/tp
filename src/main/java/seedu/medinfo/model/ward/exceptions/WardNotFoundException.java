package seedu.medinfo.model.ward.exceptions;

/**
 * Signals that the operation is unable to find the specified ward.
 */
public class WardNotFoundException extends RuntimeException {
    public WardNotFoundException(String name) {
        super("Ward " + name + " not found!");
    }
}
