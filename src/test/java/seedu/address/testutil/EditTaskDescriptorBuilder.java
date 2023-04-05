package seedu.address.testutil;

import static seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;

import seedu.address.model.task.Comment;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setTaskType(task.getTaskType());
        descriptor.setTaskDescription(task.getDescription());
        descriptor.setComment(task.getTaskComment());
        descriptor.setDeadline(task.getDate());
    }

    /**
     * Sets the {@code taskType} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskType(String taskType) {
        descriptor.setTaskType(taskType);
        return this;
    }

    /**
     * Sets the {@code taskDescription} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDescription(String taskDescription) {
        descriptor.setTaskDescription(new TaskDescription(taskDescription));
        return this;
    }

    /**
     * Sets the {@code comment} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withComment(String comment) {
        descriptor.setComment(new Comment(comment));
        return this;
    }

    /**
     * Sets the {@code date} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDate(String date) {
        descriptor.setDeadline(date);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}

