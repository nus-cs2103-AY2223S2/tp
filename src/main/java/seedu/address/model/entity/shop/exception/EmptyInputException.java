package seedu.address.model.entity.shop.exception;

/**
 * Thrown when empty string is passed as an argument
 */
public class EmptyInputException extends Exception {
    public EmptyInputException(String msg) {
        super(msg);
    }
}
