package vimification.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import vimification.common.util.CollectionUtil;
import vimification.common.util.StringUtil;

/**
 * This class is the model of a task in the application.
 * <p>
 *
 * A task contains:
 *
 * <ul>
 * <li>A title (stored as a {@code String}, cannot be empty)</li>
 * <li>A deadline (stored as a {@code LocalDateTime}, can be null to represent the lack of
 * deadline)</li>
 * <li>A status (stored as an enum, cannot be null)</li>
 * <li>A priority (stored as an enum, cannot be null)</li>
 * <li>Multiple labels (stored as a {@code Set<String>})</li>
 * </ul>
 *
 * Defensive coding is applied for methods that modify the interal state of instances.
 */
public class Task {

    private String title;
    private LocalDateTime deadline;
    private Status status;
    private Priority priority;
    private Set<String> labels;

    private static final DateTimeFormatter DEADLINE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Creates a new instance of {@code Task}.
     *
     * @param title the title of the task, cannot be null
     * @param deadline the deadline of the task, can be null
     * @param status the status of the task
     * @param priority the priority of the task
     */
    public Task(String title, LocalDateTime deadline, Status status, Priority priority) {
        CollectionUtil.requireAllNonNull(title, status, priority);
        StringUtil.requireNonEmpty(title);
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.labels = new HashSet<>();
    }

    /**
     * Creates a new instance of {@code Task}, where some fields are set with default values.
     *
     * @param title the title of the task, cannot be null
     */
    public Task(String title) {
        this(title, null, Status.NOT_DONE, Priority.UNKNOWN);
    }

    ///////////
    // TITLE //
    ///////////

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Objects.requireNonNull(title);
        StringUtil.requireNonEmpty(title);
        this.title = title;
    }

    //////////////
    // DEADLINE //
    //////////////

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        Objects.requireNonNull(deadline);
        this.deadline = deadline;
    }

    public void deleteDeadline() {
        this.deadline = null;
    }

    /**
     * Returns the deadline of this task as a string. If this task has no deadline, a special string
     * will be returned instead.
     *
     * @return the deadline of this task as a string
     */
    public String getDeadlineAsString() {
        return deadline == null ? "-" : DEADLINE_FORMATTER.format(deadline);
    }

    ////////////
    // STATUS //
    ////////////

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        Objects.requireNonNull(status);
        this.status = status;
    }

    public boolean hasStatus(Status status) {
        return this.status.equals(status);
    }

    //////////////
    // PRIORITY //
    //////////////

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        Objects.requireNonNull(priority);
        this.priority = priority;
    }

    public boolean hasPriority(Priority priority) {
        return this.priority.equals(priority);
    }

    ////////////
    // LABELS //
    ////////////

    public Set<String> getLabels() {
        return Collections.unmodifiableSet(labels);
    }

    public void addLabel(String label) {
        Objects.requireNonNull(label);
        StringUtil.requireNonEmpty(label);
        label = label.toLowerCase();
        if (!labels.add(label)) {
            throw new IllegalArgumentException("Label already exists");
        }
    }

    public void removeLabel(String label) {
        Objects.requireNonNull(label);
        StringUtil.requireNonEmpty(label);
        label = label.toLowerCase();
        if (!labels.remove(label)) {
            throw new IllegalArgumentException("Label does not exist");
        }
    }

    public boolean containsLabel(String label) {
        return labels.contains(label.toLowerCase());
    }

    ///////////////
    // UTILITIES //
    ///////////////

    public boolean containsKeyword(String keyword) {
        return title.contains(keyword);
    }

    public boolean deadlineIsBefore(LocalDateTime date) {
        return deadline != null && !deadline.isAfter(date);
    }

    public boolean deadlineIsAfter(LocalDateTime date) {
        return deadline != null && !deadline.isBefore(date);
    }

    public Task clone() {
        Task clonedTask = new Task(title, deadline, status, priority);
        clonedTask.labels.addAll(labels);
        return clonedTask;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        if (deadline != null) {
            sb.append("; by: ");
            sb.append(getDeadlineAsString());
        }
        return sb.toString();
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
        return Objects.equals(title, otherTask.title)
                && Objects.equals(deadline, otherTask.deadline)
                && Objects.equals(status, otherTask.status)
                && Objects.equals(priority, otherTask.priority)
                && Objects.equals(labels, otherTask.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, deadline, status, priority, labels);
    }

    @Override
    public String toString() {
        return "Task [title=" + title + ", deadline=" + deadline + ", status=" + status
                + ", priority=" + priority + ", labels=" + labels + "]";
    }
}
