package seedu.sudohr.testutil;

import seedu.sudohr.logic.commands.EditDepartmentCommand;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;

/**
 * A utility class to help with building EditDepartmentDescriptor objects.
 */
public class EditDepartmentDescriptorBuilder {

    private EditDepartmentCommand.EditDepartmentDescriptor descriptor;

    public EditDepartmentDescriptorBuilder() {
        descriptor = new EditDepartmentCommand.EditDepartmentDescriptor();
    }

    public EditDepartmentDescriptorBuilder(EditDepartmentCommand.EditDepartmentDescriptor descriptor) {
        this.descriptor = new EditDepartmentCommand.EditDepartmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDepartmentDescriptor} with fields containing {@code Department}'s details
     */
    public EditDepartmentDescriptorBuilder(Department department) {
        descriptor = new EditDepartmentCommand.EditDepartmentDescriptor();
        descriptor.setName(department.getName());
    }

    /**
     * Sets the {@code Name} of the {@code EditDepartmentDescriptor} that we are building.
     */
    public EditDepartmentDescriptorBuilder withName(DepartmentName name) {
        descriptor.setName(name);
        return this;
    }

    public EditDepartmentCommand.EditDepartmentDescriptor build() {
        return descriptor;
    }
}
