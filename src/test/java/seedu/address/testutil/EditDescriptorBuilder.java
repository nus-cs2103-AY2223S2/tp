package seedu.address.testutil;

import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditDescriptorBuilder {

    private EditDescriptor descriptor;

    public EditDescriptorBuilder() {
        descriptor = new EditDescriptor();
    }

    public EditDescriptorBuilder(EditDescriptor descriptor) {
        this.descriptor = new EditDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDescriptorBuilder} with fields containing {@code person}'s details
     */
    private EditDescriptorBuilder(Person person) {
        descriptor = new EditDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setNric(person.getNric());
        descriptor.setBirthDate(person.getBirthDate());
        descriptor.setRegion(person.getRegion());
        descriptor.setAvailableDates(person.getAvailableDates());
        descriptor.setTags(person.getTags());
    }

    /**
     * Returns an {@code EditDescriptorBuilder} with fields containing {@code elderly}'s details
     */
    public EditDescriptorBuilder(Elderly elderly) {
        this((Person) elderly);
        descriptor.setRiskLevel(elderly.getRiskLevel());
    }

    /**
     * Returns an {@code EditDescriptorBuilder} with fields containing {@code volunteer}'s details
     */
    public EditDescriptorBuilder(Volunteer volunteer) {
        this((Person) volunteer);
        descriptor.setMedicalTags(volunteer.getMedicalTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withNric(String nric) {
        descriptor.setNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withBirthDate(String birthDate) {
        descriptor.setBirthDate(new BirthDate(birthDate));
        return this;
    }

    /**
     * Sets the {@code Region} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withRegion(String region) {
        descriptor.setRegion(new Region(region));
        return this;
    }

    /**
     * Sets the {@code RiskLevel} of the {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withRiskLevel(String riskLevel) {
        descriptor.setRiskLevel(new RiskLevel(riskLevel.toUpperCase()));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditDescriptor}
     * that we are building.
     */
    public EditDescriptorBuilder withTags(String... tags) {
        descriptor.setTags(SampleDataUtil.getTagSet(tags));
        return this;
    }

    /**
     * Parsers the {@code dates} and sets the {@code Set<AvailableDate>} of the
     * {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withAvailableDates(String... dates) {
        descriptor.setAvailableDates(SampleDataUtil.getAvailableDateSet(dates));
        return this;
    }

    /**
     * Parsers the {@code tags} and sets the {@code Set<MedicalQualificationTag>} of the
     * {@code EditDescriptor} that we are building.
     */
    public EditDescriptorBuilder withMedicalQualificationTags(String... tags) {
        descriptor.setMedicalTags(SampleDataUtil.getMedicalTagSet(tags));
        return this;
    }


    public EditDescriptor build() {
        return descriptor;
    }
}
