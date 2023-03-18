package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.id.AppointmentId;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details except description are present and not null, field values are validated, immutable.
 */
public class Appointment {
    private final Timeslot timeslot;
    private final Description description;
    private final Patient patient;
    private final Set<Tag> tags = new HashSet<>();
    private final AppointmentId id;

    /**
     * Every field must be present and not null.
     */
    public Appointment(AppointmentId id, Timeslot timeslot, Description description, Patient patient, Set<Tag> tags) {
        requireAllNonNull(timeslot, description, patient, tags);
        this.id = id;
        this.timeslot = timeslot;
        this.description = description;
        this.patient = patient;
        this.tags.addAll(tags);
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Description getDescription() {
        return description;
    }

    public Patient getPatient() {
        return patient;
    }

    public AppointmentId getId() {
        return id;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
            && otherAppointment.getPatient().equals(getPatient())
            && otherAppointment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(timeslot, description, patient, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTimeslot())
            .append("; Description: ")
            .append(getDescription())
            .append("; Patient: ")
            .append(getPatient());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
