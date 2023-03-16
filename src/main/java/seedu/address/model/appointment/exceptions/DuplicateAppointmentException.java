package seedu.address.model.appointment.exceptions;

import seedu.address.model.patient.exceptions.DuplicatePatientException;

public class DuplicateAppointmentException extends RuntimeException {
    public DuplicateAppointmentException() {
        super("Operation would result in duplicate appointments");
    }
}
