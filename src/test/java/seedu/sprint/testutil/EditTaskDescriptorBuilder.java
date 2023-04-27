package seedu.sprint.testutil;

import seedu.sprint.logic.commands.EditTaskCommand;
import seedu.sprint.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.sprint.model.task.Deadline;
import seedu.sprint.model.task.Description;
import seedu.sprint.model.task.Task;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {
    private EditTaskCommand.EditTaskDescriptor descriptor;

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
        descriptor.setDeadline(task.getDeadline());
        descriptor.setDescription(task.getDescription());
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
