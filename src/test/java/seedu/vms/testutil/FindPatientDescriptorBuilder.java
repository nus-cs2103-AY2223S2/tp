package seedu.vms.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.vms.logic.commands.patient.FindCommand.FindPatientDescriptor;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;

/**
 * A utility class to help with building FindPatientDescriptor objects.
 */
public class FindPatientDescriptorBuilder {

    private FindPatientDescriptor descriptor;

    public FindPatientDescriptorBuilder() {
        descriptor = new FindPatientDescriptor();
    }

    public FindPatientDescriptorBuilder(FindPatientDescriptor descriptor) {
        this.descriptor = new FindPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code FindPatientDescriptor} with fields containing {@code patient}'s details
     */
    public FindPatientDescriptorBuilder(Patient patient) {
        descriptor = new FindPatientDescriptor();
        descriptor.setNameSearch(patient.getName().toString());
        descriptor.setPhone(patient.getPhone());
        descriptor.setDob(patient.getDob());
        descriptor.setBloodType(patient.getBloodType());
        descriptor.setAllergies(patient.getAllergy());
        descriptor.setVaccines(patient.getVaccine());
    }

    /**
     * Sets the {@code Name} of the {@code FindPatientDescriptor} that we are building.
     */
    public FindPatientDescriptorBuilder withName(String name) {
        descriptor.setNameSearch(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code FindPatientDescriptor} that we are building.
     */
    public FindPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Dob} of the {@code FindPatientDescriptor} that we are building.
     *
     * @throws ParseException
     */
    public FindPatientDescriptorBuilder withDob(String dateOfBirth) {
        descriptor.setDob(new Dob(dateOfBirth));
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code FindPatientDescriptor} that we are building.
     */
    public FindPatientDescriptorBuilder withBloodType(String bloodType) {
        descriptor.setBloodType(new BloodType(bloodType));
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<GroupName>} and set it to the {@code FindPatientDescriptor}
     * that we are building.
     */
    public FindPatientDescriptorBuilder withAllergies(String... allergies) {
        Set<GroupName> allergySet = Stream.of(allergies).map(GroupName::new).collect(Collectors.toSet());
        descriptor.setAllergies(allergySet);
        return this;
    }

    /**
     * Parses the {@code vaccines} into a {@code Set<GroupName>} and set it to the {@code FindPatientDescriptor}
     * that we are building.
     */
    public FindPatientDescriptorBuilder withVaccines(String... vaccines) {
        Set<GroupName> vaccineSet = Stream.of(vaccines).map(GroupName::new).collect(Collectors.toSet());
        descriptor.setVaccines(vaccineSet);
        return this;
    }

    public FindPatientDescriptor build() {
        return descriptor;
    }
}
