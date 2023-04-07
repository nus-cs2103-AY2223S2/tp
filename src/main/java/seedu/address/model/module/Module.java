package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.isAllNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Module in the module tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Compulsory fields
    private final Name name; //module name
    private final Resource resource; // module website
    private final TimeSlot timeSlot; // timeslot (should be optional)

    // Data fields
    private final Address address; // venue
    private final Teacher teacher; // teacher (should be optional)
    private final Deadline deadline; // deadline (should be optional)
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>(); // module type (tutorial, lecture, lab, assignment, project)

    /**
     * name, type, timeSlot, address and tags fields are compulsory when inputting a new Module.
     */
    public Module(Name name, Resource resource, TimeSlot timeSlot, Address address, Set<Tag> tags, Remark remark,
                  Deadline deadline, Teacher teacher) {
        requireAllNonNull(name, resource, timeSlot, address, tags, remark, deadline, teacher);
        boolean isAllNonNull = isAllNonNull(name, resource, timeSlot, address, tags, remark, deadline, teacher);
        assert isAllNonNull == true : "Module object is not created properly.";
        this.name = name;
        this.resource = resource;
        this.timeSlot = timeSlot;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
        this.deadline = deadline;
        this.teacher = teacher;
    }

    public Name getName() {
        return name;
    }

    public Resource getResource() {
        return resource;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both modules have the same name and same tag.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getName().equals(getName()) && otherModule.getTags().equals(getTags());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getName().equals(getName())
                && otherModule.getResource().equals(getResource())
                && otherModule.getTimeSlot().equals(getTimeSlot())
                && otherModule.getAddress().equals(getAddress())
                && otherModule.getTags().equals(getTags())
                && otherModule.getRemark().equals(getRemark())
                && otherModule.getDeadline().equals(getDeadline())
                && otherModule.getTeacher().equals(getTeacher());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, resource, timeSlot, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Resource: ")
                .append(getResource())
                .append("; TimeSlot: ")
                .append(getTimeSlot())
                .append("; Address: ")
                .append(getAddress())
                .append("; Remark: ")
                .append(getRemark())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Teacher: ")
                .append(getTeacher());


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Compares TimeSlot of two modules
     * @param otherModule Module to be compared to
     * @return 1 if this module has no TimeSlot else -1 if other module has no TimeSlot else result of comparison
     */
    public int compareTimeSlot(Module otherModule) {
        if (this.timeSlot.getDay() == null) {
            //if this module has no timeslot, this module should be moved to the end of the sorted list.
            return 1;
        } else if (otherModule.timeSlot.getDay() == null) {
            //if other module has no timeslot, other module should be moved to the end of the sorted list.
            return -1;
        }
        return this.timeSlot.compareTo(otherModule.timeSlot);
    }

    /**
     * Compares Deadline of two modules
     * @param otherModule Module to be compared to
     * @return 1 if this module has no Deadline else -1 if other module has no Deadline else result of comparison
     */
    public int compareDeadline(Module otherModule) {
        if (this.deadline.value == null) {
            //if this module has no deadline, this module should be moved to the end of the sorted list.
            return 1;
        } else if (otherModule.deadline.value == null) {
            //if other module has no deadline, other module should be moved to the end of the sorted list.
            return -1;
        }
        return this.deadline.compareTo(otherModule.deadline);
    }

}
