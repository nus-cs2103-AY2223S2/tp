package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import vimification.model.task.components.Description;

public abstract class Task {

    private final Description description;
    private final Status status;
    private final TaskType type;

    /**
     * Every field must be present and not null.
     */
    public Task(Description description, Status status, TaskType type) {
        requireAllNonNull(description, status);
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public static Task createTask(TaskType type, String... taskComponents) {
        requireNonNull(type);
        requireNonNull(taskComponents);
        switch (type) {
        case TODO:
            return Todo.createTask(taskComponents);
        case DEADLINE:
            return Deadline.createTask(taskComponents);
        case EVENT:
            return Event.createTask(taskComponents);
        default:
            throw new IllegalArgumentException("Invalid task type");
        }
    }

    public Description getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public TaskType getType() {
        return type;
    }


    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    public void setDone() {
        status.setDone();
    }

    public void setNotDone() {
        status.setNotDone();
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
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getStatus().equals(getStatus());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Description: ")
                .append(getDescription())
                .append("; Status: ")
                .append(getStatus());
        return builder.toString();
    }
}
