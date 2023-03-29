package vimification.commons.exceptions;

public class NonExistentFileException extends Exception {

    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public NonExistentFileException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public NonExistentFileException(String message, Throwable cause) {
        super(message, cause);
    }

}
