package wingman.logic.core.exceptions;

/**
 * Represents a parse error encountered by a parser when parsing a number.
 */
public class NumberParseException extends ParseException {
    /**
     * Constructs a new {@code NumberParseException} with the specified
     * detail {@code message}.
     *
     * @param message the detail message.
     */
    public NumberParseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code NumberParseException} with the specified
     * detail {@code message} and {@code cause}.
     *
     * @param message the detail message.
     * @param args    the arguments to be formatted.
     * @return the formatted {@code NumberParseException}.
     */
    public static NumberParseException formatted(
            String message,
            Object... args
    ) {
        return new NumberParseException(String.format(message, args));
    }
}
