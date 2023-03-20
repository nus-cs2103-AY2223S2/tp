package seedu.address.model.tutee;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.fields.Address;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Email;
import seedu.address.model.tutee.fields.EndTime;
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

    public TuteeBuilder() {}

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
        // Need to copy, otherwise modifications to 1 tutee's tags
        // could affect the another's
        this.tags = Set.copyOf(existing.getTags());
    }

    public TuteeBuilder withEmail(Email email) {
        this.email = email;
        return this;
    }
    public TuteeBuilder withPhone(Phone phone) {
        this.phone = phone;
        return this;
    }
    public TuteeBuilder withName(Name name) {
        this.name = name;
        return this;
    }
    public TuteeBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }
    public TuteeBuilder withRemark(Remark remark) {
        this.remark = remark;
        return this;
    }
    public TuteeBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }
    public TuteeBuilder withSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }
    public TuteeBuilder withStartTime(StartTime startTime) {
        this.startTime = startTime;
        return this;
    }
    public TuteeBuilder withEndTime(EndTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public TuteeBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public TuteeBuilder withTags(Tag ...tags) {
        this.tags = Set.of(tags);
        return this;
    }

    public TuteeBuilder withAttendance(Attendance attendance) {
        this.attendance = attendance;
        return this;
    }

    /**
     * Build the new {@link Tutee} instance. If any one of the fields is missing, a
     * {@link NullPointerException} will be thrown
     * @return A new tutee instance with the given fields
     */
    public Tutee build() {
        return new Tutee(name, phone, email, address, attendance, remark, subject, schedule, startTime, endTime, tags);
    }
}
