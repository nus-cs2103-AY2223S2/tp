package seedu.task.model.task;

import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.task.model.tag.Tag;

/**
 * Represents an Event in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event extends Task {

    // Identity fields
    private Date from;
    private Date to;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, Description description, Set<Tag> tags, Date from, Date to, Effort effort) {
        super(name, description, tags, effort);
        requireAllNonNull(from, to);
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return this.from;
    }

    public Date getTo() {
        return this.to;
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherTask = (Event) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTags().equals(getTags())
                && otherTask.getFrom().equals(getFrom())
                && otherTask.getTo().equals(getTo())
                && otherTask.getEffort().equals(getEffort());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags, from, to, effort);
    }

    /**
     * Compares tasks to get their position in a sorted list.
     * If task is a SimpleTask or a Deadline instance, task should be higher up on the list.
     * If task is an Event instance, order to compare by is as follows: from, to, tags size, name.
     *
     * @param task the object to be compared.
     * @return int priority.
     */
    @Override
    public int compareTo(Task task) {
        if (task instanceof SimpleTask) {
            return 1;
        }

        if (task instanceof Deadline) {
            return 1;
        }

        Event newTask = (Event) task;

        if (!this.from.equals(newTask.from)) {
            // compare to first.
            return this.from.compareTo(newTask.from);
        }

        if (!this.to.equals(newTask.to)) {
            // if from is the same, compare to.
            return this.to.compareTo(newTask.to);
        }

        if (this.tags.size() == newTask.tags.size()) {
            // if number of tags are the same, compare name.
            return this.name.compareTo(newTask.name);
        } else {
            // if number of tags are different, compare size.
            return Integer.compare(this.tags.size(), newTask.tags.size());
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; From: ")
                .append(getFrom())
                .append("; To: ")
                .append(getTo())
                .append("; Effort: ")
                .append(getEffort());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
