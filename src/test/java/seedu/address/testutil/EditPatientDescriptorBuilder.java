package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.patient.Diagnosis;
import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.Remark;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;
import seedu.address.model.tag.Tag;

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
        descriptor.setEmail(patient.getEmail());
        descriptor.setHeight(patient.getHeight());
        descriptor.setWeight(patient.getWeight());
        descriptor.setDiagnosis(patient.getDiagnosis());
        descriptor.setStatus(patient.getStatus());
        descriptor.setRemark(patient.getRemark());
        descriptor.setTags(patient.getTags());
        descriptor.setDoctors(patient.getDoctors());
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
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    /**
     * Sets the {@code Diagnosis} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withDiagnosis(String diagnosis) {
        descriptor.setDiagnosis(new Diagnosis(diagnosis));
        return this;
    }


    /**
     * Sets the {@code Status} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}
