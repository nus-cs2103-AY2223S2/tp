package seedu.task.model.task;

import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import seedu.task.model.tag.Tag;

/**
 * Represents a Deadline in the task book.
 * Guarantees: details ares present and not null, field values are validated, immutable.
 */
public class Deadline extends Task {

    // Identity fields
    private Date deadline;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Name name, Description description, Set<Tag> tags, Date deadline, Effort effort) {
        super(name, description, tags, effort);
        requireAllNonNull(deadline);
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    /**
     * Returns true if both deadlines have the same identity and data fields.
     * This defines a stronger notion of equality between two deadlines.
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
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getEffort().equals(getEffort());
    }

    /**
     * Returns true if {@code deadline} is within the {@code alertWindow} from the current time.
     */
    @Override
    public boolean isComingUp() {
        boolean isAfterNow = this.deadline.value.isAfter(LocalDateTime.now());
        boolean isBeforeAlert = this.deadline.value.minus(alertWindow).isBefore(LocalDateTime.now());
        return isAfterNow && isBeforeAlert;
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags, deadline);
    }

    /**
     * Compares tasks to get their position in a sorted list.
     * If task is a SimpleTask instance, task should be higher up on the list.
     * If task is an Event instance, this instance should be on higher up on the list.
     * If task is a Deadline instance, order to compare by is as follows: deadline, tags size, name.
     *
     * @param task the object to be compared.
     * @return int priority.
     */
    @Override
    public int compareTo(Task task) {
        if (task instanceof SimpleTask) {
            return 1;
        }

        if (task instanceof Event) {
            return -1;
        }

        Deadline newTask = (Deadline) task;

        if (!this.deadline.equals(newTask.deadline)) {
            // if deadline is different, compare deadline.
            return this.deadline.compareTo(newTask.deadline);
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
                .append("; Deadline: ")
                .append(getDeadline())
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
