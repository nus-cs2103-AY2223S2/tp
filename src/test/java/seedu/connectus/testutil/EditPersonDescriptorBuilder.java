package seedu.connectus.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.connectus.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone().get());
        descriptor.setEmail(person.getEmail().get());
        descriptor.setAddress(person.getAddress().get());
        descriptor.setSocialMedia(person.getSocialMedia().orElse(SocialMedia.create()));
        descriptor.setRemarks(person.getRemarks());
        descriptor.setCcas(person.getCcas());
        descriptor.setMajors(person.getMajors());
        descriptor.setModules(person.getModules());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Remark> tagSet = Stream.of(tags).map(Remark::new).collect(Collectors.toSet());
        descriptor.setRemarks(tagSet);
        return this;
    }

    /**
     * Parses the {@code modules} into a {@code Set<Module>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withModules(String... modules) {
        Set<Module> moduleSet = Stream.of(modules).map(Module::new).collect(Collectors.toSet());
        descriptor.setModules(moduleSet);
        return this;
    }

    /**
     * Parses the {@code modules} into a {@code Set<Cca>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withCcas(String... ccas) {
        Set<Cca> ccaSet = Stream.of(ccas).map(Cca::new).collect(Collectors.toSet());
        descriptor.setCcas(ccaSet);
        return this;
    }

    /**
     * Parses the {@code modules} into a {@code Set<CcaPosiiton>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withMajors(String... majors) {
        Set<Major> majorSet = Stream.of(majors).map(Major::new).collect(Collectors.toSet());
        descriptor.setMajors(majorSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
