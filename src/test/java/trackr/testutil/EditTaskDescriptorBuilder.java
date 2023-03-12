package trackr.testutil;

import trackr.logic.commands.EditTaskCommand.EditTaskDescriptor;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor editTaskDescriptor;

    public EditTaskDescriptorBuilder() {
        editTaskDescriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor editTaskDescriptor) {
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setTaskName(task.getTaskName());
        editTaskDescriptor.setTaskDeadline(task.getTaskDeadline());
        editTaskDescriptor.setTaskStatus(task.getTaskStatus());
    }

    /**
     * Sets the {@code TaskName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskName(String taskName) {
        editTaskDescriptor.setTaskName(new TaskName(taskName));
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDeadline(String taskDeadline) {
        editTaskDescriptor.setTaskDeadline(new TaskDeadline(taskDeadline));
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskStatus(String taskStatus) {
        editTaskDescriptor.setTaskStatus(new TaskStatus(taskStatus));
        return this;
    }

    public EditTaskDescriptor build() {
        return editTaskDescriptor;
    }
}
