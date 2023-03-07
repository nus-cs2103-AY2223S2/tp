package seedu.address.model.score.exceptions;

/**
 * Signals that the operation is unable to parse the date due to incorrect format.
 */
public class BadDateException extends RuntimeException {
    public BadDateException() {
        super("Date is not in the correct format!");
    }
}
