package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in invalid end date (DateTimes are considered invalid if they occur before
 * start DateTimes).
 */
public class InvalidEndDateTimeException extends RuntimeException {
    public InvalidEndDateTimeException() {
        super("Operation would result in invalid end dateTimes");
    }
}
