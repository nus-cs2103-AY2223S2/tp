package trackr.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import trackr.model.commons.Tag;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonDescriptor;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;

/**
 * A utility class to help with building EditSupplierDescriptor objects.
 */
//@@author arkarsg-reused
public class PersonDescriptorBuilder {

    private PersonDescriptor descriptor;

    public PersonDescriptorBuilder() {
        descriptor = new PersonDescriptor();
    }

    public PersonDescriptorBuilder(PersonDescriptor descriptor) {
        this.descriptor = new PersonDescriptor(descriptor);
    }

    //@@author liumc-sg-reused
    /**
     * Returns an {@code EditSupplierDescriptor} with fields containing {@code supplier}'s details
     */
    public PersonDescriptorBuilder(Supplier person) {
        descriptor = new PersonDescriptor();
        descriptor.setName(person.getPersonName());
        descriptor.setPhone(person.getPersonPhone());
        descriptor.setEmail(person.getPersonEmail());
        descriptor.setAddress(person.getPersonAddress());
        descriptor.setTags(person.getPersonTags());
    }
    //@@author

    //@@author arkarsg-reused
    /**
     * Sets the {@code Name} of the {@code EditSupplierDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withName(String name) {
        descriptor.setName(new PersonName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditSupplierDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new PersonPhone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditSupplierDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new PersonEmail(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditSupplierDescriptor} that we are building.
     */
    public PersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new PersonAddress(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSupplierDescriptor}
     * that we are building.
     */
    public PersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public PersonDescriptor build() {
        return descriptor;
    }
}
