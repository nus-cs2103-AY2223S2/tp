package seedu.address.model.tutee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Tutee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutee {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Subject subject;
    private final Schedule schedule;
    private final StartTime startTime;
    private final EndTime endTime;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Tutee(Name name, Phone phone, Email email, Address address, Remark remark, Subject subject, Schedule schedule
            , StartTime startTime, EndTime endTime, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, remark, subject, schedule, startTime, endTime, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.subject = subject;
        this.schedule = schedule;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags.addAll(tags);
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() { return remark; }

    public Subject getSubject() { return subject; }

    public Schedule getSchedule() { return schedule; }

    public StartTime getStartTime() { return startTime; }

    public EndTime getEndTime() { return endTime; }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Tutee otherTutee) {
        if (otherTutee == this) {
            return true;
        }

        return otherTutee != null
                && otherTutee.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutee)) {
            return false;
        }

        Tutee otherTutee = (Tutee) other;
        return otherTutee.getName().equals(getName())
                && otherTutee.getPhone().equals(getPhone())
                && otherTutee.getEmail().equals(getEmail())
                && otherTutee.getAddress().equals(getAddress())
                && otherTutee.getSubject().equals(getSubject())
                && otherTutee.getStartTime().equals(getStartTime())
                && otherTutee.getEndTime().equals(getEndTime())
                && otherTutee.getTags().equals(getTags())
                ;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, subject, schedule, startTime, endTime, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Subject: ")
                .append(getSubject())
                .append("; Schedule: ")
                .append(getSchedule())
                .append(" Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
