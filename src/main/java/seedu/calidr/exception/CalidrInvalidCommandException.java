package seedu.calidr.exception;

/**
 * Represents an exception that is thrown when an invalid command is entered.
 */
public class CalidrInvalidCommandException extends CalidrException {

    public CalidrInvalidCommandException(String message) {
        super(message);
    }

}
