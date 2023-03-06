package seedu.loyaltylift.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.loyaltylift.logic.commands.EditCommand;
import seedu.loyaltylift.logic.commands.EditCommand.EditCustomerDescriptor;
import seedu.loyaltylift.model.customer.Address;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Name;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.tag.Tag;

/**
 * A utility class to help with building EditCustomerDescriptor objects.
 */
public class EditCustomerDescriptorBuilder {

    private EditCommand.EditCustomerDescriptor descriptor;

    public EditCustomerDescriptorBuilder() {
        descriptor = new EditCommand.EditCustomerDescriptor();
    }

    public EditCustomerDescriptorBuilder(EditCommand.EditCustomerDescriptor descriptor) {
        this.descriptor = new EditCustomerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCustomerDescriptor} with fields containing {@code customer}'s details
     */
    public EditCustomerDescriptorBuilder(Customer customer) {
        descriptor = new EditCustomerDescriptor();
        descriptor.setName(customer.getName());
        descriptor.setPhone(customer.getPhone());
        descriptor.setEmail(customer.getEmail());
        descriptor.setAddress(customer.getAddress());
        descriptor.setTags(customer.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCustomerDescriptor}
     * that we are building.
     */
    public EditCustomerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditCustomerDescriptor build() {
        return descriptor;
    }
}
