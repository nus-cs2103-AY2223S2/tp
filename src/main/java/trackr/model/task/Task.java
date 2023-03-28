package trackr.model.task;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task extends Item {

    // Data fields
    private final TaskName taskName;
    private final TaskDeadline taskDeadline;
    private final TaskStatus taskStatus;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, TaskDeadline taskDeadline, TaskStatus taskStatus) {
        super(ModelEnum.TASK);
        requireAllNonNull(taskName, taskDeadline, taskStatus);
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.taskStatus = taskStatus;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public TaskDeadline getTaskDeadline() {
        return taskDeadline;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     * Compares 2 tasks using their statuses and deadlines.
     * @param otherTask The task to compare with.
     * @return -1 if this task is not done while the other task is done,
     *         or if both have the same status but this task has an earlier deadline.
     *         Returns 1 if this task is done while the other task is not done,
     *         or if both have the same status but this task has a later deadline.
     *         Returns 0 if both tasks have the same statuses and deadlines.
     */
    public int compare(Task otherTask) {
        int compareStatusResult = taskStatus.compare(otherTask.getTaskStatus());

        if (compareStatusResult != 0) {
            return compareStatusResult;
        }
        //both task statuses are equal so compare deadline
        return taskDeadline.compare(otherTask.getTaskDeadline());
    }

    /**
     * Returns true if both tasks have the same name and deadline.
     * This defines a weaker notion of equality between two tasks.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (!(otherItem instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) otherItem;

        return otherTask != null
                && otherTask.getTaskName().equals(getTaskName())
                && otherTask.getTaskDeadline().equals(getTaskDeadline());
    }

    /**
     * Returns true if both tasks have the same name, deadline and status.
     * This defines a stronger notion of equality between two tasks.
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
        return otherTask.getTaskName().equals(getTaskName())
                && otherTask.getTaskDeadline().equals(getTaskDeadline())
                && otherTask.getTaskStatus().equals(getTaskStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, taskDeadline, taskStatus);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskName())
                .append("; Deadline: ")
                .append(getTaskDeadline())
                .append("; Status: ")
                .append(getTaskStatus());
        return builder.toString();
    }
}

