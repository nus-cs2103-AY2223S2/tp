package seedu.address.model.entity.shop.exception;

/**
 * Thrown when quantity is 0 or negative
 */
public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(int qty) {
        super(String.format("Quantity %d is not a valid input", qty));
    }
}
