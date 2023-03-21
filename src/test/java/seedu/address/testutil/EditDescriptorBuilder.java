package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditDescriptorBuilder {

    private EditDescriptor descriptor;
    private Set<AvailableDate> availableDates;
    private Set<MedicalQualificationTag> medicalTags;

    public EditDescriptorBuilder() {
        descriptor = new EditDescriptor();
        availableDates = new HashSet<>();
        medicalTags = new HashSet<>();
    }

    public EditDescriptorBuilder(EditDescriptor descriptor) {
        this.descriptor = new EditDescriptor(descriptor);
        availableDates = new HashSet<>();
        if (descriptor.getAvailableDates().isPresent()) {
            descriptor.getAvailableDates().get().stream().map(availableDate -> availableDates.add(availableDate));
        }
        medicalTags = new HashSet<>();
        if (descriptor.getMedicalTags().isPresent()) {
            descriptor.getMedicalTags().get().stream().map(tag -> medicalTags.add(tag));
        }
    }

    /**
     * Returns an {@code EditPersonDescriptorBuilder} with fields containing {@code person}'s details
     */
    public EditDescriptorBuilder(Person person) {
        descriptor = new EditDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setNric(person.getNric());
        descriptor.setAge(person.getAge());
        descriptor.setRegion(person.getRegion());
        descriptor.setAvailableDates(person.getAvailableDates());
        descriptor.setTags(person.getTags());
        availableDates = person.getAvailableDates();
    }

    public EditDescriptorBuilder(Elderly elderly) {
        this((Person) elderly);
        descriptor.setRiskLevel(elderly.getRiskLevel());
    }

    public EditDescriptorBuilder(Volunteer volunteer) {
        this((Person) volunteer);
        descriptor.setMedicalTags(volunteer.getMedicalTags());
        medicalTags = volunteer.getMedicalTags();
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withNric(String nric) {
        descriptor.setNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withAge(String age) {
        descriptor.setAge(new Age(age));
        return this;
    }

    /**
     * Sets the {@code Region} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withRegion(String region) {
        descriptor.setRegion(new Region(region));
        return this;
    }

    /**
     * Sets the {@code RiskLevel} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withRiskLevel(String riskLevel) {
        descriptor.setRiskLevel(new RiskLevel(riskLevel.toUpperCase()));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Adds the {@code AvailableDate} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withAvailableDate(String startDate, String endDate) {
        availableDates.add(SampleDataUtil.getAvailableDate(startDate, endDate));
        return this;
    }

    /**
     * Adds the {@code MedicalQualificationLevel} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDescriptorBuilder withMedicalQualificationTag(String skill, String qualificationLevel) {
        medicalTags.add(new MedicalQualificationTag(skill, qualificationLevel));
        return this;
    }


    public EditDescriptor build() {
        descriptor.setAvailableDates(availableDates);
        descriptor.setMedicalTags(medicalTags);
        return descriptor;
    }
}
