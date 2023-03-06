package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPetDescriptor;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Email;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPetDescriptor objects.
 */
public class EditPetDescriptorBuilder {

    private EditPetDescriptor descriptor;

    public EditPetDescriptorBuilder() {
        descriptor = new EditPetDescriptor();
    }

    public EditPetDescriptorBuilder(EditPetDescriptor descriptor) {
        this.descriptor = new EditPetDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPetDescriptor} with fields containing {@code pet}'s details
     */
    public EditPetDescriptorBuilder(Pet pet) {
        descriptor = new EditPetDescriptor();
        descriptor.setName(pet.getName());
        descriptor.setPhone(pet.getPhone());
        descriptor.setEmail(pet.getEmail());
        descriptor.setAddress(pet.getAddress());
        descriptor.setTags(pet.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPetDescriptor}
     * that we are building.
     */
    public EditPetDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPetDescriptor build() {
        return descriptor;
    }
}
