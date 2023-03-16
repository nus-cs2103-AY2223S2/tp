package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TYPE = "85355255";
    public static final String DEFAULT_TIMESLOT = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "default remark";
    public static final String DEFAULT_DEADLINE = "default deadline";
    public static final String DEFAULT_TEACHER = "default teacher";

    private Name name;
    private Type type;
    private TimeSlot timeSlot;
    private Address address;
    private Set<Tag> tags;
    private Remark remark;
    private Deadline deadline;
    private Teacher teacher;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        type = new Type(DEFAULT_TYPE);
        timeSlot = new TimeSlot(DEFAULT_TIMESLOT);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        remark = new Remark(DEFAULT_REMARK);
        deadline = new Deadline(DEFAULT_DEADLINE);
        teacher = new Teacher(DEFAULT_TEACHER);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        type = personToCopy.getType();
        timeSlot = personToCopy.getTimeSlot();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        remark = personToCopy.getRemark();
        deadline = personToCopy.getDeadline();
        teacher = personToCopy.getTeacher();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Person} that we are building.
     */
    public PersonBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code TimeSlot} of the {@code Person} that we are building.
     */
    public PersonBuilder withTimeSlot(String timeSlot) {
        this.timeSlot = new TimeSlot(timeSlot);
        return this;
    }

    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public PersonBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public PersonBuilder withTeacher(String teacher) {
        this.teacher = new Teacher(teacher);
        return this;
    }

    public Person build() {
        return new Person(name, type, timeSlot, address, tags, remark, deadline, teacher);
    }

}
