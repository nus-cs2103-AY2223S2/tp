package seedu.sprint.testutil;

import seedu.sprint.logic.commands.EditApplicationCommand;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.task.Deadline;
import seedu.sprint.model.task.Description;
import seedu.sprint.model.task.Task;

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
        descriptor.setTask(application.getTask());
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

    /**
     * Sets the {@code Task} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withTask(String deadline, String description) {
        descriptor.setTask(new Task(new Deadline(deadline), new Description(description)));
        return this;
    }

    public EditApplicationDescriptor build() {
        return descriptor;
    }
}
