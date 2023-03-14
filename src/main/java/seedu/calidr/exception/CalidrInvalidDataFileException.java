package seedu.calidr.exception;

/**
 * Represents an exception that is thrown when the arguments given for a command are invalid.
 */
public class CalidrInvalidDataFileException extends CalidrException {

    public CalidrInvalidDataFileException(String message) {
        super(message);
    }

}
