package seedu.address.model.score.exceptions;

/**
 * Signals that the score value is not a valid score value.
 */
public class BadScoreValueException extends IllegalArgumentException {

    public BadScoreValueException() {
        super("Score value should be a number between 0 and 100.");
    }
}
