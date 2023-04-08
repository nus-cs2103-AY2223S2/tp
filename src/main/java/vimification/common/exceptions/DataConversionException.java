package vimification.common.exceptions;

/**
 * Represents an error during conversion of data from one format to another.
 */
public class DataConversionException extends Exception {

    /**
     * Creates an instance with the specified cause.
     *
     * @param cause the original cause of this exception
     */
    public DataConversionException(Exception cause) {
        super(cause);
    }
}
