package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.application.Application;

import java.util.Objects;

/**
 * Represents a task of an application in the internship book.
 */
public class Task {
    private Application application;
    private final Deadline deadline;
    private final Description description;

    /**
     * Every field must be present and not null.
     */
    public Task(Application application, Deadline deadline, Description description) {
        requireAllNonNull(application, deadline, description);
        this.application = application;
        this.deadline = deadline;
        this.description = description;
    }

    public Application getApplication() {
        return this.application;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns true if both tasks have the same fields.
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
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getDeadline().equals((getDeadline()))
                && otherTask.getApplication().equals(getApplication()); // not sure if this should be included
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, deadline, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Task description: ")
                .append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline());

        return builder.toString();
    }
}
