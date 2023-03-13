package seedu.sudohr.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.sudohr.logic.commands.EditCommand;
import seedu.sudohr.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.sudohr.model.employee.Address;
import seedu.sudohr.model.employee.Email;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.Name;
import seedu.sudohr.model.employee.Phone;
import seedu.sudohr.model.tag.Tag;

/**
 * A utility class to help with building EditEmployeeDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditEmployeeDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditEmployeeDescriptor();
    }

    public EditPersonDescriptorBuilder(EditEmployeeDescriptor descriptor) {
        this.descriptor = new EditEmployeeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEmployeeDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Employee person) {
        descriptor = new EditEmployeeDescriptor();
        descriptor.setId(person.getId());
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code id} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withId(String id) {
        descriptor.setId(new Id(id));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEmployeeDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditEmployeeDescriptor build() {
        return descriptor;
    }
}
