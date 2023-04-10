package seedu.address.model.tutee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.fields.Address;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Email;
import seedu.address.model.tutee.fields.EndTime;
import seedu.address.model.tutee.fields.Lesson;
import seedu.address.model.tutee.fields.Name;
import seedu.address.model.tutee.fields.Phone;
import seedu.address.model.tutee.fields.Remark;
import seedu.address.model.tutee.fields.Schedule;
import seedu.address.model.tutee.fields.StartTime;
import seedu.address.model.tutee.fields.Subject;

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
    private final Attendance attendance;
    private final Remark remark;
    private final Subject subject;
    private final Schedule schedule;
    private final StartTime startTime;
    private final EndTime endTime;
    private final Set<Tag> tags = new HashSet<>();
    private final Lesson lessons;

    /**
     * Every field must be present and not null.
     */
    public Tutee(Name name, Phone phone, Email email, Address address,
        Attendance attendance, Remark remark, Subject subject, Schedule schedule,
        StartTime startTime, EndTime endTime, Set<Tag> tags, Lesson lessons
    ) {
        requireAllNonNull(name, phone, email, attendance, address, remark, subject, schedule, startTime, endTime, tags, lessons);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.attendance = attendance;
        this.address = address;
        this.remark = remark;
        this.subject = subject;
        this.schedule = schedule;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags.addAll(tags);
        this.lessons = lessons;
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

    public Attendance getAttendance() {
        return attendance;
    }

    public Remark getRemark() {
        return remark;
    }

    public Subject getSubject() {
        return subject;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public Lesson getLessons() {
        return lessons;
    }

    /**
     * Returns an immutable tag set, which throws {@link UnsupportedOperationException}
     *     if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     *     This defines a weaker notion of equality between two persons.
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
     *     This defines a stronger notion of equality between two persons.
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
                && otherTutee.getLessons().equals(getLessons())
                && otherTutee.getTags().equals(getTags());
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
                .append("; StartTime: ")
                .append(getStartTime())
                .append("; EndTime: ")
                .append(getEndTime())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Lessons: ")
                .append(getLessons());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
