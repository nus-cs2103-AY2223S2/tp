package seedu.calidr.exception;

/**
 * Represents an exception that can be thrown while interacting with the chatbot.
 */
public class CalidrException extends Exception {

    public CalidrException(String message) {
        super(message);
    }

}
