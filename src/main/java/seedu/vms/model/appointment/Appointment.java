package seedu.vms.model.appointment;

import static seedu.vms.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.vms.model.person.Person;

/**
 * Represents an Appointment in the vaccine management system.
 */
public class Appointment {
    private final Person person;
    private LocalDateTime appointmentTime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Person person, LocalDateTime appointmentTime) {
        requireAllNonNull(person, appointmentTime);
        this.person = person;
        this.appointmentTime = appointmentTime;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public boolean setAppointmentTime(LocalDateTime appointmentTime) {
        requireAllNonNull(appointmentTime);
        this.appointmentTime = appointmentTime;
        return true;
    }

    /**
     * Returns true if both appointments have the same patient.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Person otherPerson) {
        if (otherPerson == person) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(person.getName());
    }

    /**
     * Returns true if both appointments have the same patient and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getPerson().equals(getPerson())
                && otherAppointment.getAppointmentTime().equals(getAppointmentTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(person, appointmentTime);
    }

    @Override
    public String toString() {
        return person
                + " has an appointment at"
                + appointmentTime;
    }
}
