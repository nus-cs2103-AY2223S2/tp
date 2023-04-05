package trackr.testutil;

import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskDescriptor;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * A utility class to help with building TaskDescriptor objects.
 */
public class TaskDescriptorBuilder {

    private TaskDescriptor taskDescriptor;

    public TaskDescriptorBuilder() {
        taskDescriptor = new TaskDescriptor();
    }

    public TaskDescriptorBuilder(TaskDescriptor taskDescriptor) {
        this.taskDescriptor = new TaskDescriptor(taskDescriptor);
    }

    /**
     * Returns an {@code TaskDescriptor} with fields containing {@code task}'s details
     */
    public TaskDescriptorBuilder(Task task) {
        taskDescriptor = new TaskDescriptor();
        taskDescriptor.setTaskName(task.getTaskName());
        taskDescriptor.setTaskDeadline(task.getTaskDeadline());
        taskDescriptor.setTaskStatus(task.getTaskStatus());
    }

    /**
     * Sets the {@code TaskName} of the {@code TaskDescriptor} that we are building.
     */
    public TaskDescriptorBuilder withTaskName(String taskName) {
        taskDescriptor.setTaskName(new TaskName(taskName));
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code TaskDescriptor} that we are building.
     */
    public TaskDescriptorBuilder withTaskDeadline(String taskDeadline) {
        taskDescriptor.setTaskDeadline(new TaskDeadline(taskDeadline));
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code TaskDescriptor} that we are building.
     */
    public TaskDescriptorBuilder withTaskStatus(String taskStatus) {
        taskDescriptor.setTaskStatus(new TaskStatus(taskStatus));
        return this;
    }

    public TaskDescriptor build() {
        return taskDescriptor;
    }
}
