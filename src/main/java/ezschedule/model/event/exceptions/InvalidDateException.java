package ezschedule.model.event.exceptions;

import java.time.format.DateTimeParseException;

/**
 * Signals that the operation has an invalid Date.
 */
public class InvalidDateException extends DateTimeParseException {
    public InvalidDateException(String message) {
        super(message, message, 0);
    }
}
