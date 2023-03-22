package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;

/**
 * A utility class to help with building {@code EditModuleDescriptor} objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    /**
     * Creates a {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    /**
     * Creates a {@code EditModuleDescriptorBuilder} with the data of {@code descriptor}.
     *
     * @param descriptor The {@code EditModuleDescriptor} containing the data to copy.
     */
    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        requireNonNull(descriptor);

        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Creates a {@code EditModuleDescriptorBuilder} with fields containing the {@code module} details.
     *
     * @param module The module.
     */
    public EditModuleDescriptorBuilder(Module module) {
        requireNonNull(module);

        descriptor = new EditModuleDescriptor();
        descriptor.setCode(module.getCode());
        descriptor.setName(module.getName());
    }

    /**
     * Sets the {@code code} of the {@code EditModuleDescriptor} that we are building.
     *
     * @param code The code to set to.
     * @return This {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder withCode(String code) {
        descriptor.setCode(new ModuleCode(code));
        return this;
    }

    /**
     * Sets the {@code name} of the {@code EditModuleDescriptor} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setName(new ModuleName(name));
        return this;
    }

    /**
     * Returns the {@code EditModuleDescriptor} that we built.
     *
     * @return The {@code EditModuleDescriptor} that we built.
     */
    public EditModuleDescriptor build() {
        return descriptor;
    }

}
