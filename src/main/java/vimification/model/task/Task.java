package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;
import java.util.HashSet;

public abstract class Task {

    private String description;
    private boolean isDone;
    private Set<String> tags;
    private Priority priority;

    /**
     * Every field must be present and not null.
     */
    Task(String description, boolean isDone, Priority priority) {
        requireAllNonNull(description, priority);
        this.description = description;
        this.isDone = isDone;
        this.tags = new HashSet<>();
        this.priority = priority;
    }

    Task(String description, boolean isDone) {
        this(description, isDone, Priority.UNKNOWN);
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public void setPriority(Priority priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    public void setPriority(int level) {
        this.priority = Priority.fromInt(level);
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    public void addTag(String newTag) {
        requireNonNull(newTag);
        newTag = newTag.toLowerCase();
        if (tags.contains(newTag)) {
            throw new IllegalArgumentException("Tag already exists");
        }
        tags.add(newTag);
    }

    public void removeTag(String tag) {
        if (!tags.remove(tag)) {
            throw new IllegalArgumentException("Tag does not exist");
        }
    }

    public boolean checkPriority(Priority priority) {
        return this.priority.equals(priority);
    }

    public boolean checkPriority(int level) {
        return checkPriority(Priority.fromInt(level));
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
