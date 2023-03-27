package seedu.address.logic.parser.exceptions;

public class UiInputRequiredException extends RuntimeException {
    public UiInputRequiredException(String commandText) {
        super(commandText);
    }
    public UiInputRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
