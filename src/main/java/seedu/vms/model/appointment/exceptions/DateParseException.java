package seedu.vms.model.appointment.exceptions;

/**
 * Signals that the given appointment time cannot be parsed to local date.
 */
public class DateParseException  extends RuntimeException{
    public DateParseException() {
        super("The given appointment time cannot be parsed to local date.\n"
                + "The appointment time should be in the format <yyyy-mm-dd>.");
    }
}
