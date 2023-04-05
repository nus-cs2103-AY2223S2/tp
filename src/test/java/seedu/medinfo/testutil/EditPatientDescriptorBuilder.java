package seedu.medinfo.testutil;

import seedu.medinfo.logic.commands.EditCommand;
import seedu.medinfo.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medinfo.model.patient.Name;
import seedu.medinfo.model.patient.Nric;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.Status;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditCommand.EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing
     * {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setNric(patient.getNric());
        descriptor.setStatus(patient.getStatus());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withNric(String nric) {
        descriptor.setNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }


}
