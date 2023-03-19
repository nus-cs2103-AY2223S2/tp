package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Compulsory fields
    private final Name name; //module name
    private final Type type; // module type (tutorial, lecture, lab, assignment, project)
    private final TimeSlot timeSlot; // timeslot (should be optional)

    // Data fields
    private final Address address; // venue
    private final Teacher teacher; // teacher (should be optional)
    private final Deadline deadline; // deadline (should be optional)
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * name, type, timeSlot, address and tags fields are compulsory when inputting a new Module.
     */
    public Module(Name name, Type type, TimeSlot timeSlot, Address address, Set<Tag> tags, Remark remark,
                  Deadline deadline, Teacher teacher) {
        requireAllNonNull(name, type, timeSlot, address, tags);
        this.name = name;
        this.type = type;
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

    public Type getType() {
        return type;
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
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getName().equals(getName()) && otherModule.getType().equals(getType());
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
                && otherModule.getType().equals(getType())
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
        return Objects.hash(name, type, timeSlot, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Type: ")
                .append(getType())
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

}
