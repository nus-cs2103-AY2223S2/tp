package seedu.task.model.task;

import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.task.model.tag.Tag;

/**
 * Represents a Deadline in the task book.
 * Guarantees: details ares present and not null, field values are validated, immutable.
 */
public class Deadline extends Task {

    // Identity fields
    private Name name;
    private Description description;
    private Date deadline;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Deadline(Name name, Description description, Set<Tag> tags, Date deadline) {
        super(name, description, tags);
        requireAllNonNull(deadline);
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    /**
     * Returns true if both deadlines have the same identity and data fields.
     * This defines a stronger notion of equality between two dealines.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherTask = (Deadline) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTags().equals(getTags())
                && otherTask.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
