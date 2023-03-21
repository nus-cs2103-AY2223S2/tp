package seedu.careflow.testutil;

import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPersonDescriptor;
import seedu.careflow.model.person.Address;
import seedu.careflow.model.person.DateOfBirth;
import seedu.careflow.model.person.DrugAllergy;
import seedu.careflow.model.person.Email;
import seedu.careflow.model.person.Gender;
import seedu.careflow.model.person.Ic;
import seedu.careflow.model.person.Name;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.person.Phone;

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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code patient}'s details
     */
    public EditPersonDescriptorBuilder(Patient patient) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setEmail(patient.getEmail());
        descriptor.setAddress(patient.getAddress());
        descriptor.setDateOfBirth(patient.getBirthDate());
        descriptor.setGender(patient.getGender());
        descriptor.setIc(patient.getIc());
        descriptor.setDrugAllergy(patient.getDrugAllergy());
        descriptor.setEmergencyContact(patient.getEmergencyContact());
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
     * Sets the {@code DateOfBirth} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDob(String dob) {
        descriptor.setDateOfBirth(new DateOfBirth(dob));
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
     * Sets the {@code Ic} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withIc(String ic) {
        descriptor.setIc(new Ic(ic));
        return this;
    }

    /**
     * Sets the {@code DrugAllergy} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDrugAllergy(String drugAllergy) {
        descriptor.setDrugAllergy(new DrugAllergy(drugAllergy));
        return this;
    }

    /**
     * Sets the {@code EmergencyContact} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmergencyContact(String emergencyContact) {
        descriptor.setEmergencyContact(new Phone(emergencyContact));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
