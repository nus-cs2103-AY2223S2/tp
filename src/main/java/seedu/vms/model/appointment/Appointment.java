package seedu.vms.model.appointment;

import static seedu.vms.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.GroupName;

/**
 * Represents an Appointment in the vaccine management system.
 */
public class Appointment implements Comparable<Appointment> {
    private final Index patientId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final GroupName vaccine;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Index patientId, LocalDateTime startTime, LocalDateTime endTime, GroupName vaccine) {
        requireAllNonNull(patientId, startTime, endTime, vaccine);
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vaccine = vaccine;
    }

    public LocalDateTime getAppointmentTime() {
        return startTime;
    }

    public LocalDateTime getAppointmentEndTime() {
        return endTime;
    }

    public GroupName getVaccination() {
        return vaccine;
    }

    public Index getPatient() {
        return patientId;
    }

    @Override
    public int compareTo(Appointment other) {
        return startTime.compareTo(other.startTime);
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
        return otherAppointment.getPatient() == (getPatient())
                && otherAppointment.getAppointmentTime().equals(getAppointmentTime())
                && otherAppointment.getAppointmentEndTime().equals(getAppointmentEndTime())
                && otherAppointment.getVaccination().equals(getVaccination());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patientId, startTime, endTime, vaccine);
    }

    @Override
    public String toString() {
        return getPatient().getOneBased()
                + " has an appointment at "
                + startTime;
    }
}
