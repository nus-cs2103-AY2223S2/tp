package seedu.address.model.appointment.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super("Did not find the appointment");
    }
}
