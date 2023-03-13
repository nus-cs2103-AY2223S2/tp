package seedu.loyaltylift.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.loyaltylift.logic.commands.EditCustomerCommand;
import seedu.loyaltylift.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.tag.Tag;

/**
 * A utility class to help with building EditCustomerDescriptor objects.
 */
public class EditCustomerDescriptorBuilder {

    private EditCustomerCommand.EditCustomerDescriptor descriptor;

    public EditCustomerDescriptorBuilder() {
        descriptor = new EditCustomerCommand.EditCustomerDescriptor();
    }

    public EditCustomerDescriptorBuilder(EditCustomerCommand.EditCustomerDescriptor descriptor) {
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
        descriptor.setCustomerType(customer.getCustomerType());
    }

    /**
     * Sets the {@code CustomerType} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withCustomerType(CustomerType customerType) {
        descriptor.setCustomerType(customerType);
        return this;
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

    public EditCustomerCommand.EditCustomerDescriptor build() {
        return descriptor;
    }
}
