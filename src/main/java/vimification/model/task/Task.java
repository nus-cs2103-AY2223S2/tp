package vimification.model.task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Task {

    private String description;
    private Status status;
    private Priority priority;
    private Set<String> tags;

    /**
     * Every field must be present and not null.
     */
    Task(String description, Status status, Priority priority) {  //used when converting from storage
        requireAllNonNull(description, priority);
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.tags = new HashSet<>();
    }
    Task(String description) {     //used when creating new tasks
        this(description, Status.NOT_DONE, Priority.UNKNOWN);
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isDone() {
        return status.equals(Status.COMPLETED);
    }

    public void setDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public void setPriority(Priority priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    public void deletePriority() {
        requireNonNull(priority);
        this.priority = Priority.UNKNOWN;
    }

    public void setPriority(int level) {
        this.priority = Priority.fromInt(level);
    }

    public void setStatus(Status status) {
        requireNonNull(status);
        this.status = status;
    }

    public abstract void setDeadline(LocalDateTime date);
    public abstract LocalDateTime getDeadline();
    public void setStatus(int level) {
        this.status = Status.fromInt(level);
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    public boolean containsTag(String tag) {
        return tags.contains(tag);
    }

    public boolean isSamePriority(Priority priority) {
        return this.priority.equals(priority);
    }

    public boolean isSamePriority(int level) {
        return isSamePriority(Priority.fromInt(level));
    }

    public abstract boolean isDeadline();

    public abstract Task clone();

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask.description.equals(description);
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger
     * notion of equality between two persons.
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
        return otherTask.description.equals(description) && otherTask.status.equals(status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("description: ")
                .append(description)
                .append("; status: ")
                .append(status.toString())
                .append("; priority: ")
                .append(priority.toString());
        return builder.toString();
    }
}
