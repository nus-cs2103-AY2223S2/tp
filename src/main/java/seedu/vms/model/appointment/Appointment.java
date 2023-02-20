package seedu.vms.model.appointment;

import seedu.vms.model.appointment.exceptions.DateParseException;
import seedu.vms.model.person.Person;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an Appointment in the vaccine management system.
 */
public class Appointment {
    private final Person person;
    private LocalDate appointmentTime;

    public Appointment(Person person, String appointmentTime) throws DateParseException {
        try {
            this.person = person;
            this.appointmentTime = LocalDate.parse(appointmentTime);
        } catch (DateTimeParseException e) {
            // If given appointment time cannot be parsed to local date
            throw new DateParseException();
        }
    }

    public Person getPerson() {
        return person;
    }

    public LocalDate getAppointmentTime() {
        return appointmentTime;
    }

    public boolean setAppointmentTime(String appointmentTime) {
        try {
            this.appointmentTime = LocalDate.parse(appointmentTime);
            return true;
        } catch (DateTimeParseException e) {
            // If given appointment time cannot be parsed to local date
            return false;
        }
    }

    @Override
    public String toString() {
        return person +
                " has an appointment at" +
                appointmentTime;
    }
}
