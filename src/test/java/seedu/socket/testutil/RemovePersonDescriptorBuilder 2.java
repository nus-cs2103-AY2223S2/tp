package seedu.socket.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.socket.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.socket.model.person.Address;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.Phone;
import seedu.socket.model.person.tag.Language;
import seedu.socket.model.person.tag.Tag;

/**
 * A utility class to help with building RemovePersonDescriptor objects.
 */
public class RemovePersonDescriptorBuilder {

    private RemovePersonDescriptor descriptor;

    public RemovePersonDescriptorBuilder() {
        descriptor = new RemovePersonDescriptor();
    }

    public RemovePersonDescriptorBuilder(RemovePersonDescriptor descriptor) {
        this.descriptor = new RemovePersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public RemovePersonDescriptorBuilder(Person person) {
        descriptor = new RemovePersonDescriptor();
        descriptor.setPerson(person);
        descriptor.setProfile(person.getProfile());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setLanguages(person.getLanguages());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Profile} of the {@code EditPersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withPerson(Person person) {
        descriptor.setPerson(person);
        return this;
    }

    /**
     * Sets the {@code Profile} of the {@code EditPersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withProfile(String profile) {
        descriptor.setProfile(new GitHubProfile(profile));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code languages} into a {@code Set<Language>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public RemovePersonDescriptorBuilder withLanguages(String... languages) {
        Set<Language> languageSet = Stream.of(languages).map(Language::new).collect(Collectors.toSet());
        descriptor.setLanguages(languageSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public RemovePersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public RemovePersonDescriptor build() {
        return descriptor;
    }

}
