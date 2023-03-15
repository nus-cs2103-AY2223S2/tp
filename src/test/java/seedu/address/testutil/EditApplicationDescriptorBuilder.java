package seedu.address.testutil;

import seedu.address.logic.commands.EditApplicationCommand;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;

/**
 * A utility class to help with building EditApplicationDescriptor objects.
 */
public class EditApplicationDescriptorBuilder {

    private EditApplicationCommand.EditApplicationDescriptor descriptor;

    public EditApplicationDescriptorBuilder() {
        descriptor = new EditApplicationDescriptor();
    }

    public EditApplicationDescriptorBuilder(EditApplicationDescriptor descriptor) {
        this.descriptor = new EditApplicationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicationDescriptor} with fields containing {@code application}'s details
     */
    public EditApplicationDescriptorBuilder(Application application) {
        descriptor = new EditApplicationDescriptor();
        descriptor.setRole(application.getRole());
        descriptor.setCompanyName(application.getCompanyName());
        descriptor.setCompanyEmail(application.getCompanyEmail());
        descriptor.setStatus(application.getStatus());
    }

    /**
     * Sets the {@code Role} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Company Name} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code Company Email} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withCompanyEmail(String companyEmail) {
        descriptor.setCompanyEmail(new CompanyEmail(companyEmail));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    public EditApplicationDescriptor build() {
        return descriptor;
    }
}
