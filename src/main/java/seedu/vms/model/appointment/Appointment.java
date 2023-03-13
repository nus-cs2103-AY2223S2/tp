package seedu.vms.model.appointment;

import static seedu.vms.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Appointment in the vaccine management system.
 */
public class Appointment implements Comparable<Appointment> {
    private LocalDateTime appointmentTime;
    private final String patientId;

    /**
     * Every field must be present and not null.
     */
    public Appointment(LocalDateTime appointmentTime, String patient) {
        requireAllNonNull(appointmentTime, patient);
        this.appointmentTime = appointmentTime;
        this.patientId = patient;
    }

    public String getPatient() {
        return patientId;
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
    public boolean isSamePatient(String otherPatientId) {
        if (otherPatientId == patientId) {
            return true;
        }

        return otherPatientId != null && otherPatientId.equals(patientId);
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
        return Objects.hash(appointmentTime, patientId);
    }

    @Override
    public int compareTo(Appointment other) {
        return patientId.compareTo(other.patientId);
    }

    @Override
    public String toString() {
        return patientId
                + " has an appointment at "
                + appointmentTime;
    }
}
