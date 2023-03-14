package trackr.model.task;

import java.util.Optional;

import trackr.commons.util.CollectionUtil;

/**
 * Stores the details of a task. Each non-empty field value will replace the corresponding field value of the task.
 */
public class TaskDescriptor {
    private TaskName taskName;
    private TaskDeadline taskDeadline;
    private TaskStatus taskStatus;

    public TaskDescriptor() {}

    /**
     * Copy constructor.
     */
    public TaskDescriptor(TaskDescriptor toCopy) {
        setTaskName(toCopy.taskName);
        setTaskDeadline(toCopy.taskDeadline);
        setTaskStatus(toCopy.taskStatus);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(taskName, taskDeadline, taskStatus);
    }

    public void setTaskName(TaskName taskName) {
        this.taskName = taskName;
    }

    public Optional<TaskName> getTaskName() {
        return Optional.ofNullable(taskName);
    }

    public void setTaskDeadline(TaskDeadline taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public Optional<TaskDeadline> getTaskDeadline() {
        return Optional.ofNullable(taskDeadline);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Optional<TaskStatus> getTaskStatus() {
        return Optional.ofNullable(taskStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDescriptor)) {
            return false;
        }

        // state check
        TaskDescriptor taskDescriptor = (TaskDescriptor) other;

        return getTaskName().equals(taskDescriptor.getTaskName())
                && getTaskDeadline().equals(taskDescriptor.getTaskDeadline())
                && getTaskStatus().equals(taskDescriptor.getTaskStatus());
    }
}
