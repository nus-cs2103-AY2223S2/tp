package seedu.address.logic.parser.exceptions;

/**
 * Represents an error encountered by the parser where additional input from Ui is required.
 */
public class UiInputRequiredException extends RuntimeException {
    public UiInputRequiredException(String commandText) {
        super(commandText);
    }
    public UiInputRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
