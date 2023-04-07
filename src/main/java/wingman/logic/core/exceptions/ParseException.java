package wingman.logic.core.exceptions;

import wingman.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ParseException} that is formatted.
     *
     * @param message the message to be formatted.
     * @param objects the arguments to be formatted.
     * @return the formatted {@code ParseException}.
     */
    public static ParseException formatted(String message, Object... objects) {
        return new ParseException(String.format(
                message,
                objects
        ));
    }
}
