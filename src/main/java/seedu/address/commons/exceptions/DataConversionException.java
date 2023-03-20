package seedu.address.commons.exceptions;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {
    /**
     * Constructs a {@code DataConversionException} with the specified cause.
     *
     * @param cause Cause of the exception.
     */
    public DataConversionException(Exception cause) {
        super(cause);
    }

}
