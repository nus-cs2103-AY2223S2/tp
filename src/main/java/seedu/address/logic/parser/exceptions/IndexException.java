package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser but specifically for an Index.
 */
public class IndexException extends ParseException {
    public IndexException(String errorMessage) {
        super(errorMessage);
    }
}
