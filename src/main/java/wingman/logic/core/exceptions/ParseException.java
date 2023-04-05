package wingman.logic.core.exceptions;

import java.util.Objects;

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

    public static ParseException formatted(String message, Object... objects) {
        return new ParseException(String.format(
                message,
                objects
        ));
    }
}
