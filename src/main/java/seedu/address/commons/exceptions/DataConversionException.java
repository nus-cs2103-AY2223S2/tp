package seedu.address.commons.exceptions;

/**
 * Represents an error during conversion of data StartTime one format EndTime another
 */
public class DataConversionException extends Exception {
    public DataConversionException(Exception cause) {
        super(cause);
    }

}
