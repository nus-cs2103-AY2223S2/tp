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
    private final Timeslot timeslot;
    private final Description description;
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Appointment(AppointmentId id, Timeslot timeslot, Description description, Name name,
                       Set<Tag> tags) {
        requireAllNonNull(timeslot, description, name, tags);
        this.id = id;
        this.timeslot = timeslot;
        this.description = description;
        this.name = name;
        this.tags.addAll(tags);
    }

    public AppointmentId getAppointmentId() {
        return id;
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

    public Name getName() {
        return name;
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
        return otherAppointment.getTimeslot().equals(getTimeslot())
            && otherAppointment.getDescription().equals(getDescription())
            && otherAppointment.getName().equals(getName())
            && otherAppointment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(timeslot, description, name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTimeslot())
            .append("; Description: ")
            .append(getDescription())
            .append("; Patient name: ")
            .append(getName())
            .append("; Appointment ID: ")
            .append(getAppointmentId());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
