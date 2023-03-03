package seedu.vms.model.appointment;

import static seedu.vms.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.vms.model.patient.Patient;

/**
 * Represents an Appointment in the vaccine management system.
 */
public class Appointment {
    private final Patient patient;
    private LocalDateTime appointmentTime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, LocalDateTime appointmentTime) {
        requireAllNonNull(patient, appointmentTime);
        this.patient = patient;
        this.appointmentTime = appointmentTime;
    }

    public Patient getPatient() {
        return patient;
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
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == patient) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(patient.getName());
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
        return otherAppointment.getPatient().equals(getPatient())
                && otherAppointment.getAppointmentTime().equals(getAppointmentTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, appointmentTime);
    }

    @Override
    public String toString() {
        return patient
                + " has an appointment at"
                + appointmentTime;
    }
}
