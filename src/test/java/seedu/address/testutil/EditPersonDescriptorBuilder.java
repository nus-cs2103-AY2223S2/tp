package seedu.address.testutil;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.editpersoncommandsparser.PersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private PersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new PersonDescriptor();
    }

    public EditPersonDescriptorBuilder(PersonDescriptor descriptor) {
        this.descriptor = new PersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new PersonDescriptor();
        descriptor.setIndex(Optional.of(Index.fromOneBased(1)));
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setComms(person.getComms());
        descriptor.setGender(person.getGender());
        descriptor.setFaculty(person.getFaculty());
        descriptor.setMajor(person.getMajor());
        descriptor.setModules(person.getModules());
        descriptor.setRace(person.getRace());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMajor(String major) {
        descriptor.setMajor(new Major(major));
        return this;
    }

    /**
     * Sets the {@code Race} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRace(String race) {
        descriptor.setRace(new Race(race));
        return this;
    }

    /**
     * Sets the {@code Comms} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withComms(String comms) {
        descriptor.setComms(new CommunicationChannel(comms));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(new Tags(tagSet));
        return this;
    }

    public PersonDescriptor build() {
        return descriptor;
    }
}
