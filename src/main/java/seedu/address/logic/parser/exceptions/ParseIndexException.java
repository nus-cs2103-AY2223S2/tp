package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error caused by negative indices and indices that are too large.
 */
public class ParseIndexException extends ParseException {

    public ParseIndexException(String message) {
        super(message);
    }

    public ParseIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
