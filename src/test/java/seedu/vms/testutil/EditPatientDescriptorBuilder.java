package seedu.vms.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setDob(patient.getDob());
        descriptor.setBloodType(patient.getBloodType());
        descriptor.setAllergies(patient.getAllergy());
        descriptor.setVaccines(patient.getVaccine());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Dob} of the {@code EditPatientDescriptor} that we are building.
     *
     * @throws ParseException
     */
    public EditPatientDescriptorBuilder withDob(String dateOfBirth) {
        descriptor.setDob(new Dob(dateOfBirth));
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withBloodType(String bloodType) {
        descriptor.setBloodType(new BloodType(bloodType));
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<GroupName>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withAllergies(String... allergies) {
        Set<GroupName> allergySet = Stream.of(allergies).map(GroupName::new).collect(Collectors.toSet());
        descriptor.setAllergies(allergySet);
        return this;
    }

    /**
     * Parses the {@code vaccines} into a {@code Set<GroupName>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withVaccines(String... vaccines) {
        Set<GroupName> vaccineSet = Stream.of(vaccines).map(GroupName::new).collect(Collectors.toSet());
        descriptor.setVaccines(vaccineSet);
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}
