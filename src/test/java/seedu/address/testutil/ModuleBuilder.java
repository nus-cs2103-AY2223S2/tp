package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Address;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.Remark;
import seedu.address.model.module.Resource;
import seedu.address.model.module.Teacher;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_NAME = "CS2103T";
    public static final String DEFAULT_RESOURCE = "www.google.com";
    public static final String DEFAULT_TIMESLOT = "Tuesday 12:00 14:00";
    public static final String DEFAULT_ADDRESS = "COM1";
    public static final String DEFAULT_REMARK = "Zoom Class";
    public static final String DEFAULT_DEADLINE = "230223 18:00";
    public static final String DEFAULT_TEACHER = "Prof Tan";
    public static final String DEFAULT_TAG = "Project";

    private Name name;
    private Resource resource;
    private TimeSlot timeSlot;
    private Address address;
    private Set<Tag> tags;
    private Remark remark;
    private Deadline deadline;
    private Teacher teacher;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        name = new Name(DEFAULT_NAME);
        resource = new Resource(DEFAULT_RESOURCE);
        timeSlot = new TimeSlot(DEFAULT_TIMESLOT);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        tags.add(new Tag(DEFAULT_TAG));
        remark = new Remark(DEFAULT_REMARK);
        deadline = new Deadline(DEFAULT_DEADLINE);
        teacher = new Teacher(DEFAULT_TEACHER);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        name = moduleToCopy.getName();
        resource = moduleToCopy.getResource();
        timeSlot = moduleToCopy.getTimeSlot();
        address = moduleToCopy.getAddress();
        tags = new HashSet<>(moduleToCopy.getTags());
        remark = moduleToCopy.getRemark();
        deadline = moduleToCopy.getDeadline();
        teacher = moduleToCopy.getTeacher();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Module} that we are building.
     */
    public ModuleBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Module} that we are building.
     */
    public ModuleBuilder withResource(String type) {
        this.resource = new Resource(type);
        return this;
    }

    /**
     * Sets the {@code TimeSlot} of the {@code Module} that we are building.
     */
    public ModuleBuilder withTimeSlot(String timeSlot) {
        this.timeSlot = new TimeSlot(timeSlot);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Module} that we are building.
     */
    public ModuleBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Module} that we are building.
     */
    public ModuleBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Teacher} of the {@code Module} that we are building.
     */
    public ModuleBuilder withTeacher(String teacher) {
        this.teacher = new Teacher(teacher);
        return this;
    }

    public Module build() {
        return new Module(name, resource, timeSlot, address, tags, remark, deadline, teacher);
    }

}
