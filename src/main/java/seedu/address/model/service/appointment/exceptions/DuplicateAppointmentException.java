package seedu.address.model.service.appointment.exceptions;

/**
 * Signals that the operation will result in duplicate Appointment (Appointments are considered the same if they have
 * the same identity).
 */
public class DuplicateAppointmentException extends RuntimeException {
    public DuplicateAppointmentException() {
        super("Operation would result in duplicate appointment");
    }
}
