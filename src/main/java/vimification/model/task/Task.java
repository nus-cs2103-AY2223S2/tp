package vimification.model.task;

import static java.util.Objects.requireNonNull;

public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Every field must be present and not null.
     */
    Task(String description, boolean isDone) {
        requireNonNull(description);
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
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
        return otherTask.description.equals(description) && otherTask.isDone == isDone;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("description: ")
                .append(description)
                .append("; status: ")
                .append(isDone ? "done" : "not done");
        return builder.toString();
    }
}
