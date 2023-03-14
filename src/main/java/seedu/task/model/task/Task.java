package seedu.task.model.task;

import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.task.model.tag.Tag;

/**
 * Represents a Task in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task implements Comparable<Task> {

    // Identity fields
    protected Name name;
    protected Description description;
    protected Duration alertWindow;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();
    protected Effort effort;

    /**
     * Name, Description and Tag must be present and not null.
     * Effort may or may not be null. If null, default value will be used.
     * alertWindow is generated with default values.
     * plannedDate will always be initialised with a placeholder value, indicating that value is not ready.
     */
    public Task(Name name, Description description, Set<Tag> tags, Effort effort) {
        requireAllNonNull(name, description, tags);
        this.name = name;
        this.description = description;
        this.tags.addAll(tags);
        this.effort = effort;
        this.alertWindow = Duration.ofHours(24);
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Duration getAlertWindow() {
        return alertWindow;
    }

    public void setAlertWindow(Duration newAlertWindow) {
        this.alertWindow = newAlertWindow;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Effort getEffort() {
        return this.effort;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    public abstract boolean isSimpleTask();

    public abstract boolean isDeadline();

    public abstract boolean isEvent();

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTags().equals(getTags())
                && otherTask.getEffort().equals(getEffort());
    }

    /**
     * Returns true if end time of task is before {@code alertTime}.
     */
    public abstract boolean isComingUp();

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags, effort);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public abstract int compareTo(Task task);
}
