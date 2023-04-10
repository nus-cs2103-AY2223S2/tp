package seedu.address.model.entity.shop.exception;

/**
 * Thrown when there are not enough parts for attempted modification
 */
public class InsufficientPartException extends Exception {
    public InsufficientPartException(String partName, int qty) {
        super(String.format("Part %s only has %d quantity", partName, qty));
    }
}
