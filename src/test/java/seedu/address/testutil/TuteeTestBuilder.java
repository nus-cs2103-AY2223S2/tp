package seedu.address.testutil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
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
 * Wrapper around {@link TuteeBuilder} used for testing.
 */
public class TuteeTestBuilder {
    private final TuteeBuilder builder;

    public TuteeTestBuilder() {
        builder = new TuteeBuilder();
    }

    public TuteeTestBuilder(Tutee tutee) {
        builder = new TuteeBuilder(tutee);
    }

    /**
     * Change the stored value of the tutee's email field
     * to the given value
     */
    public TuteeTestBuilder withEmail(String email) {
        builder.withEmail(new Email(email));
        return this;
    }

    /**
     * Change the stored value of the tutee's phone field
     * to the given value
     */
    public TuteeTestBuilder withPhone(String phone) {
        builder.withPhone(new Phone(phone));
        return this;
    }

    /**
     * Change the stored value of the tutee's name field
     * to the given value
     */
    public TuteeTestBuilder withName(String name) {
        builder.withName(new Name(name));
        return this;
    }

    /**
     * Change the stored value of the tutee's address field
     * to the given value
     */
    public TuteeTestBuilder withAddress(String address) {
        builder.withAddress(new Address(address));
        return this;
    }

    /**
     * Change the stored value of the tutee's remark field
     * to the given value
     */
    public TuteeTestBuilder withRemark(String remark) {
        builder.withRemark(new Remark(remark));
        return this;
    }

    /**
     * Change the stored value of the tutee's subject field
     * to the given value
     */
    public TuteeTestBuilder withSubject(String subject) {
        builder.withSubject(new Subject(subject));
        return this;
    }

    /**
     * Change the stored value of the tutee's schedule field
     * to the given value
     */
    public TuteeTestBuilder withSchedule(String schedule) {
        builder.withSchedule(new Schedule(schedule));
        return this;
    }

    /**
     * Change the stored value of the tutee's StartTime field
     * to the given value
     */
    public TuteeTestBuilder withStartTime(String startTime) {
        builder.withStartTime(new StartTime(startTime));
        return this;
    }

    /**
     * Change the stored value of the tutee's EndTime field
     * to the given value
     */
    public TuteeTestBuilder withEndTime(String endTime) {
        builder.withEndTime(new EndTime(endTime));
        return this;
    }

    /**
     * Change the stored value of the tutee's tags field
     * to the given value
     */
    public TuteeTestBuilder withTags(Set<String> tags) {
        builder.withTags(
            tags.stream()
            .map(Tag::new)
            .collect(Collectors.toSet())
        );
        return this;
    }

    /**
     * Change the stored value of the tutee's tags field
     * to the given value
     */
    public TuteeTestBuilder withTags(String ...tags) {
        builder.withTags(Arrays.stream(tags).map(Tag::new).collect(Collectors.toSet()));
        return this;
    }

    /**
     * Change the stored value of the tutee's attendance field
     * to the given value
     */
    public TuteeTestBuilder withAttendance(Attendance attendance) {
        builder.withAttendance(attendance);
        return this;
    }

    /**
     * Build the new {@link Tutee} instance. If any one of the fields is missing, a
     * {@link NullPointerException} will be thrown
     * @return A new tutee instance with the given fields
     */
    public Tutee build() {
        return builder.build();
    }
}
