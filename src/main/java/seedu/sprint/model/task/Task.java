package seedu.sprint.model.task;

import static seedu.sprint.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a task of an application in the internship book.
 */
public class Task {
    private final Deadline deadline;
    private final Description description;

    /**
     * Constructs a {@code Task}.
     *
     * @param deadline A valid Deadline.
     * @param description A valid Description.
     */
    public Task(Deadline deadline, Description description) {
        requireAllNonNull(deadline, description);
        this.deadline = deadline;
        this.description = description;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public boolean hasValidDeadline() {
        return !deadline.isOutdated();
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns true if both tasks have the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (this == null && other == null) {
            return true;
        }
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deadline, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Task description: ")
                .append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline())
                .append(".");

        return builder.toString();
    }
}
