package vimification.model.task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

public class Task {

    private String title;
    private LocalDateTime deadline;
    private Status status;
    private Priority priority;
    private Set<String> tags;

    /**
     * Every field must be present and not null.
     */
    public Task(String title, LocalDateTime deadline, Status status, Priority priority) { // used
                                                                                          // when
                                                                                          // converting
                                                                                          // from
                                                                                          // storage
        requireAllNonNull(title, priority);
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.tags = new HashSet<>();
    }

    public Task(String title) { // used when creating new tasks
        this(title, null, Status.NOT_DONE, Priority.UNKNOWN);
    }

    Task(String title, LocalDateTime deadline) {
        this(title, deadline, Status.NOT_DONE, Priority.UNKNOWN);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        requireNonNull(title);
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void mark() {
        this.status = Status.COMPLETED;
    }

    public void unmark() {
        this.status = Status.NOT_DONE;
    }

    public boolean isDone() {
        return status == Status.COMPLETED;
    }

    public void setPriority(Priority priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    public void setPriority(int level) {
        this.priority = Priority.fromInt(level);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        requireNonNull(status);
        this.status = status;
    }

    public void setStatus(int level) {
        this.status = Status.fromInt(level);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    public void deleteDeadline() {
        this.deadline = null;
    }

    public boolean containsKeyword(String keyword) {
        return title.contains(keyword);
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

    /**
     * public boolean containsLabel(String label) { return labels.contains(label); }
     */

    public boolean isSamePriority(Priority priority) {
        return this.priority.equals(priority);
    }

    public boolean isSamePriority(int level) {
        return isSamePriority(Priority.fromInt(level));
    }

    public boolean isDateBefore(LocalDateTime date) {
        return deadline != null && (deadline.isBefore(date) || deadline.isEqual(date));
    }

    public boolean isDateAfter(LocalDateTime date) {
        return deadline != null && (deadline.isAfter(date) || deadline.isEqual(date));
    }


    public Task clone() {
        return new Task(getTitle(), getDeadline(), getStatus(), getPriority());
    }

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        boolean isSame = otherTask.title.equals(title) && otherTask.deadline.equals(deadline)
                && otherTask.status.equals(status) && otherTask.priority.equals(priority);
        // && otherTask.labels.equals(labels);
        return isSame;
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
        return otherTask.title.equals(title) && otherTask.status.equals(status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("title: ")
                .append(title)
                .append("; status: ")
                .append(status.toString())
                .append("; priority: ")
                .append(priority.toString());
        return builder.toString();
    }
}
