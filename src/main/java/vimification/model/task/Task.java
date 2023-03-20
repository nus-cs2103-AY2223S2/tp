package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Task {

    private String description;
    private boolean isDone;
    private final TaskType type;

    /**
     * Every field must be present and not null.
     */
    Task(String description, boolean isDone, TaskType type) {
        requireNonNull(description);
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskType getType() {
        return type;
    }

    public void setDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public abstract Task clone();

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
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
        return (otherTask.getDescription().equals(getDescription())
                && otherTask.isDone == isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Description: ")
                .append(getDescription())
                .append("; Status: ")
                .append(isDone ? "Done" : "Not done");
        return builder.toString();
    }
}
