package seedu.address.model.tutee;

import java.lang.reflect.Field;
import java.util.HashSet;
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
 * Builder class for {@link Tutee}
 */
public class TuteeBuilder {
    // Identity fields
    private Name name;
    private Phone phone;
    private Email email;

    // Data fields
    private Address address;
    private Attendance attendance;
    private Remark remark;
    private Subject subject;
    private Schedule schedule;
    private StartTime startTime;
    private EndTime endTime;
    private Set<Tag> tags = new HashSet<>();
    private Lesson lessons;

    public TuteeBuilder() {}

    /**
     * Create a TuteeBuilder instance and populate it with the fields of
     * an existing Tutee
     */
    public TuteeBuilder(Tutee existing) {
        this.name = existing.getName();
        this.phone = existing.getPhone();
        this.email = existing.getEmail();
        this.address = existing.getAddress();
        this.attendance = existing.getAttendance();
        this.remark = existing.getRemark();
        this.subject = existing.getSubject();
        this.schedule = existing.getSchedule();
        this.startTime = existing.getStartTime();
        this.endTime = existing.getEndTime();
        this.lessons = existing.getLessons();
        // Need to copy, otherwise modifications to 1 tutee's tags
        // could affect the another's
        this.tags = Set.copyOf(existing.getTags());
    }

    /**
     * Change the stored email in the builder
     */
    public TuteeBuilder withEmail(Email email) {
        this.email = email;
        return this;
    }

    /**
     * Change the stored phone in the builder
     */
    public TuteeBuilder withPhone(Phone phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Change the stored name in the builder
     */
    public TuteeBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    /**
     * Change the stored address in the builder
     */
    public TuteeBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     * Change the stored remark in the builder
     */
    public TuteeBuilder withRemark(Remark remark) {
        this.remark = remark;
        return this;
    }

    /**
     * Change the stored subject in the builder
     */
    public TuteeBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Change the stored schedule in the builder
     */
    public TuteeBuilder withSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    /**
     * Change the stored StartTime in the builder
     */
    public TuteeBuilder withStartTime(StartTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Change the stored EndTime in the builder
     */
    public TuteeBuilder withEndTime(EndTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Change the stored tags in the builder
     */
    public TuteeBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Change the stored tags in the builder
     */
    public TuteeBuilder withTags(Tag ...tags) {
        this.tags = Set.of(tags);
        return this;
    }

    /**
     * Change the stored attendance in the builder
     */
    public TuteeBuilder withAttendance(Attendance attendance) {
        this.attendance = attendance;
        return this;
    }

    /**
     * Change the stored lessons in the builder
     */
    public TuteeBuilder withLessons(Lesson lessons) {
        this.lessons = lessons;
        return this;
    }

    /**
     * Build the new {@link Tutee} instance. If any one of the fields is missing, a
     * {@link NullPointerException} will be thrown
     * @return A new tutee instance with the given fields
     */
    public Tutee build() {
        // Use reflection to make sure that all the fields within the builder have been set
        // to some non null value
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                if (field.get(this) == null) {
                    throw new NullPointerException(String.format("%s was null!", field.getName()));
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to validate fields in TuteeBuilder", e);
            }
        } 

        return new Tutee(name, phone, email, address, attendance, remark, subject, schedule, startTime, endTime, tags, lessons);
    }
}
