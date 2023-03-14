package seedu.calidr.exception;

/**
 * Represents an exception that is thrown when the arguments given for a command are invalid.
 */
public class CalidrInvalidArgumentException extends CalidrException {

    public CalidrInvalidArgumentException(String message) {
        super(message);
    }

}
