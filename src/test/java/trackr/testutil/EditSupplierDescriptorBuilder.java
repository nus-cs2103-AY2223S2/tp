package trackr.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import trackr.logic.commands.supplier.EditSupplierCommand.EditSupplierDescriptor;
import trackr.model.commons.Tag;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;

/**
 * A utility class to help with building EditSupplierDescriptor objects.
 */
public class EditSupplierDescriptorBuilder {

    private EditSupplierDescriptor descriptor;

    public EditSupplierDescriptorBuilder() {
        descriptor = new EditSupplierDescriptor();
    }

    public EditSupplierDescriptorBuilder(EditSupplierDescriptor descriptor) {
        this.descriptor = new EditSupplierDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSupplierDescriptor} with fields containing {@code supplier}'s details
     */
    public EditSupplierDescriptorBuilder(Supplier person) {
        descriptor = new EditSupplierDescriptor();
        descriptor.setName(person.getPersonName());
        descriptor.setPhone(person.getPersonPhone());
        descriptor.setEmail(person.getPersonEmail());
        descriptor.setAddress(person.getPersonAddress());
        descriptor.setTags(person.getPersonTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withName(String name) {
        descriptor.setName(new PersonName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new PersonPhone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new PersonEmail(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new PersonAddress(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSupplierDescriptor}
     * that we are building.
     */
    public EditSupplierDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditSupplierDescriptor build() {
        return descriptor;
    }
}
