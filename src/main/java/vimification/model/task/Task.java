package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Task {

    private String title;
    private LocalDateTime deadline;
    private Status status;
    private Priority priority;
    private Set<String> labels;

    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Every field must be present and not null.
     */
    public Task(String title, LocalDateTime deadline, Status status, Priority priority) {
        requireAllNonNull(title, status, priority);
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.labels = new HashSet<>();
    }

    public Task(String title) {
        // used when creating new tasks
        this(title, null, Status.NOT_DONE, Priority.UNKNOWN);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        requireNonNull(title);
        this.title = title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getDeadlineToString() {
        return dateTimeFormatter.format(deadline);
    }

    public void setDeadline(LocalDateTime deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    public void deleteDeadline() {
        this.deadline = null;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        requireNonNull(status);
        this.status = status;
    }

    public boolean hasStatus(Status status) {
        return this.status.equals(status);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    public boolean hasPriority(Priority priority) {
        return this.priority.equals(priority);
    }

    public boolean containsKeyword(String keyword) {
        return title.contains(keyword);
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void addLabel(String label) {
        requireNonNull(label);
        label = label.toLowerCase();
        if (!labels.add(label)) {
            throw new IllegalArgumentException("Tag already exists");
        }
    }

    public void removeLabel(String label) {
        requireNonNull(label);
        label = label.toLowerCase();
        if (!labels.remove(label)) {
            throw new IllegalArgumentException("Tag does not exist");
        }
    }

    public boolean containsLabel(String label) {
        return labels.contains(label.toLowerCase());
    }

    public boolean isDateBefore(LocalDateTime date) {
        return deadline != null && (deadline.isBefore(date) || deadline.isEqual(date));
    }

    public boolean isDateAfter(LocalDateTime date) {
        return deadline != null && (deadline.isAfter(date) || deadline.isEqual(date));
    }

    public Task clone() {
        Task clonedTask = new Task(title, deadline, status, priority);
        clonedTask.labels.addAll(labels);
        return clonedTask;
    }

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask.title.equals(title)
                && otherTask.deadline.equals(deadline)
                && otherTask.status.equals(status)
                && otherTask.priority.equals(priority)
                && otherTask.labels.equals(labels);
    }

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

    public String forDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        if (deadline != null) {
            sb.append(" by").append(getDeadlineToString());
        }
        sb.append(priority.asEnding());
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Task [title: %s;"
                + " deadline: %s; status: %s;"
                + " priority: %s; labels: %s]",
                title, deadline, status, priority, labels);
    }
}
