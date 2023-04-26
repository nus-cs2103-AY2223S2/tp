package taa.logic.parser.exceptions;

import taa.commons.exceptions.IllegalValueException;

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
     * Consumer that might throw a {@link ParseException}.
     *
     * @param <E> input type.
     */
    public interface Consumer<E> {
        void accept(E e) throws ParseException;
    }
}
