package seedu.task.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.task.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Event;
import seedu.task.model.task.Name;
import seedu.task.model.task.Task;

//@@author lywich-reused
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
        descriptor.setName(task.getName());
        descriptor.setDescription(task.getDescription());
        descriptor.setTags(task.getTags());
        descriptor.setEffort(task.getEffort());
        if (task instanceof Event) {
            descriptor.setFrom(((Event) task).getFrom());
            descriptor.setTo(((Event) task).getTo());
        }
        if (task instanceof Deadline) {
            descriptor.setDeadline(((Deadline) task).getDeadline());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    //@@author joyngjr
    /**
     * Sets the {@code Effort} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withEffort(long e) {
        descriptor.setEffort(new Effort(e));
        return this;
    }

    //@@author PROGRAMMERHAO
    /**
     * Sets the {@code from} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withFrom(String date) {
        descriptor.setFrom(new Date(date));
        return this;
    }

    /**
     * Sets the {@code to} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTo(String to) {
        descriptor.setTo(new Date(to));
        return this;
    }

    /**
     * Sets the {@code Effort} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Date(deadline));
        return this;
    }

    //@@author
    public EditTaskDescriptor build() {
        return descriptor;
    }
}
