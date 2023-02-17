package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditTaskDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(task.getName());
        descriptor.setDescription(task.getDescription());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
