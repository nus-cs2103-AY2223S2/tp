package seedu.address.testutil;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

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
        descriptor.setTitle(task.getTitle());
        descriptor.setContent(task.getContent());
        descriptor.setStatus(task.getStatus());
        descriptor.setId(task.getId());
    }
    /**
     * Sets the {@code Title} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }
    /**
     * Sets the {@code Content} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withContent(String content) {
        descriptor.setContent(new Content(content));
        return this;
    }
    /**
     * Sets the {@code Status} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(Boolean.parseBoolean(status)));
        return this;
    }
    /**
     * Sets the {@code Id} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withId(String id) {
        descriptor.setId(new Id(id));
        return this;
    }
    /**
     * Sets the {@code Id} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withId(Id id) {
        descriptor.setId(id);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
