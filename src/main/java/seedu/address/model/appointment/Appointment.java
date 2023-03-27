package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.chrono.ChronoLocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.id.AppointmentId;
import seedu.address.model.patient.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details except description are present and not null, field values are validated, immutable.
 */
public class Appointment {
    private final AppointmentId id;
    private final Name patientName;
    private final Timeslot timeslot;
    private final Description description;

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Appointment(AppointmentId id, Name patientName, Timeslot timeslot, Description description,
                       Set<Tag> tags) {
        requireAllNonNull(timeslot, description, patientName, tags);
        this.id = id;
        this.patientName = patientName;
        this.timeslot = timeslot;
        this.description = description;
        this.tags.addAll(tags);
    }

    public AppointmentId getAppointmentId() {
        return id;
    }

    public Name getPatientName() {
        return patientName;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Checks if this timeslot and the provided timeslot overlap.
     *
     * @param otherTimeslot The timeslot to check for overlaps with.
     * @return Whether both timeslots clash.
     */
    public boolean hasOverlap(Timeslot otherTimeslot) {
        return timeslot.hasOverlap(otherTimeslot);
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks if the given time occurs within the timeslot of the appointment
     *
     * @return If the given time occurs within the timeslot of the appointment
     */
    public boolean duringTime(ChronoLocalDateTime<?> time) {
        return time.isAfter(timeslot.startingDateTime) && time.isBefore(timeslot.endingDateTime);
    }

    /**
     * Returns true if both appointments have the same timeslot.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getTimeslot().equals(getTimeslot());
    }

    /**
     * Returns true if both appointments have the same timeslot, description and patient.
     * This defines a stronger notion of equality between two patients.
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
        return otherAppointment.getPatientName().equals(getPatientName())
                && otherAppointment.getTimeslot().equals(getTimeslot())
                && otherAppointment.getDescription().equals(getDescription())
                && otherAppointment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patientName, timeslot, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTimeslot())
                .append("; Patient Name: ")
                .append(getPatientName())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
