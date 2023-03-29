package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.module.Address;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.Remark;
import seedu.address.model.module.Resource;
import seedu.address.model.module.Teacher;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setName(module.getName());
        descriptor.setResource(module.getResource());
        descriptor.setTimeSlot(module.getTimeSlot());
        descriptor.setAddress(module.getAddress());
        descriptor.setTeacher(module.getTeacher());
        descriptor.setDeadline(module.getDeadline());
        descriptor.setRemark(module.getRemark());
        descriptor.setTags(module.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withResource(String type) {
        descriptor.setResource(new Resource(type));
        return this;
    }

    /**
     * Sets the {@code TimeSlot} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withTimeSlot(String timeSlot) {
        descriptor.setTimeSlot(new TimeSlot(timeSlot));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code Teacher} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withTeacher(String teacher) {
        descriptor.setTeacher(new Teacher(teacher));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
