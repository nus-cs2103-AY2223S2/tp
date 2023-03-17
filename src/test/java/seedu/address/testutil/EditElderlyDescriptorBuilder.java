package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.util.EditElderlyDescriptor;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditElderlyDescriptor objects.
 */
public class EditElderlyDescriptorBuilder {

    private EditElderlyDescriptor descriptor;

    public EditElderlyDescriptorBuilder() {
        descriptor = new EditElderlyDescriptor();
    }

    public EditElderlyDescriptorBuilder(EditElderlyDescriptor descriptor) {
        this.descriptor = new EditElderlyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditElderlyDescriptor} with fields containing {@code elderly}'s details
     */
    public EditElderlyDescriptorBuilder(Elderly elderly) {
        descriptor = new EditElderlyDescriptor();
        descriptor.setName(elderly.getName());
        descriptor.setPhone(elderly.getPhone());
        descriptor.setEmail(elderly.getEmail());
        descriptor.setAddress(elderly.getAddress());
        descriptor.setNric(elderly.getNric());
        descriptor.setAge(elderly.getAge());
        descriptor.setRegion(elderly.getRegion());
        descriptor.setRiskLevel(elderly.getRiskLevel());
        descriptor.setTags(elderly.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withNric(String nric) {
        descriptor.setNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withAge(String age) {
        descriptor.setAge(new Age(age));
        return this;
    }

    /**
     * Sets the {@code Region} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withRegion(String region) {
        descriptor.setRegion(new Region(region));
        return this;
    }

    /**
     * Sets the {@code RiskLevel} of the {@code EditElderlyDescriptor} that we are building.
     */
    public EditElderlyDescriptorBuilder withRiskLevel(String riskLevel) {
        descriptor.setRiskLevel(new RiskLevel(riskLevel.toUpperCase()));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditElderlyDescriptor}
     * that we are building.
     */
    public EditElderlyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditElderlyDescriptor build() {
        return descriptor;
    }
}
