package wingman.model.pilot.exceptions;

/**
 * Represents an error that the gender specified is not valid.
 */
public class InvalidGenderException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidGenderException} with no detail message.
     */
    public InvalidGenderException() {
        super();
    }

    /**
     * Constructs a new {@code InvalidGenderException} with the specified detail
     * message.
     *
     * @param message The detail message.
     */
    public InvalidGenderException(String message) {
        super(message);
    }
}
