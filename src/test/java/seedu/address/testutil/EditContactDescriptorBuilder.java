package seedu.address.testutil;

import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;

/**
 * A utility class to help with building EditContactDescriptor objects.
 */
public class EditContactDescriptorBuilder {
    private EditContactCommand.EditContactDescriptor descriptor;

    public EditContactDescriptorBuilder() {
        descriptor = new EditContactCommand.EditContactDescriptor();
    }

    public EditContactDescriptorBuilder(EditContactCommand.EditContactDescriptor descriptor) {
        this.descriptor = new EditContactCommand.EditContactDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditContactDescriptor} with fields containing {@code contact}'s details
     */
    public EditContactDescriptorBuilder(Contact contact) {
        descriptor = new EditContactCommand.EditContactDescriptor();
        descriptor.setPhone(contact.getPhone());
        descriptor.setEmail(contact.getEmail());
    }

    /**
     * Sets the {@code Phone} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    public EditContactCommand.EditContactDescriptor build() {
        return descriptor;
    }
}
