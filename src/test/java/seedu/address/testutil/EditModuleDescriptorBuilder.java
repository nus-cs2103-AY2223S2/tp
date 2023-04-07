package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building {@code EditModuleDescriptor} objects.
 */
public class EditModuleDescriptorBuilder {

    private final EditModuleDescriptor descriptor;

    /**
     * Constructs an {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    /**
     * Constructs an {@code EditModuleDescriptorBuilder} with the data of {@code descriptor}.
     *
     * @param descriptor The {@code EditModuleDescriptor} containing the data to copy.
     */
    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        requireNonNull(descriptor);

        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Constructs an {@code EditModuleDescriptorBuilder} with fields containing the details of {@code module}.
     *
     * @param module The module whose details will be used.
     */
    public EditModuleDescriptorBuilder(Module module) {
        requireNonNull(module);

        descriptor = new EditModuleDescriptor();
        descriptor.setCode(module.getCode());
        descriptor.setName(module.getName());
        descriptor.setTags(module.getTags());
    }

    /**
     * Sets the {@code code} of the {@code EditModuleDescriptor} that we are building.
     *
     * @param code The code to set to.
     * @return This {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder withCode(String code) {
        descriptor.setCode(code == null ? null : new ModuleCode(code));
        return this;
    }

    /**
     * Sets the {@code name} of the {@code EditModuleDescriptor} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder withName(String name) {
        descriptor.setName(name == null ? null : new ModuleName(name));
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code EditModuleDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder withTags(String... tags) {
        return withTags(Arrays.asList(tags));
    }

    /**
     * Sets the {@code tags} of the {@code EditModuleDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditModuleDescriptorBuilder}.
     */
    public EditModuleDescriptorBuilder withTags(Collection<String> tags) {
        descriptor.setTags(tags == null ? null : tags.stream().map(Tag::new).collect(Collectors.toSet()));
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
