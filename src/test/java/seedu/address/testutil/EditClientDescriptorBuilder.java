package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private final EditCommand.EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditCommand.EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditCommand.EditClientDescriptor descriptor) {
        this.descriptor = new EditCommand.EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditCommand.EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }


    public EditCommand.EditClientDescriptor build() {
        return descriptor;
    }
}
