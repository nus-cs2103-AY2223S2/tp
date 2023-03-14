package arb.testutil;

import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;

/**
 * A utility class to help with building EditProjectDescriptor objects.
 */
public class EditProjectDescriptorBuilder {

    private EditProjectDescriptor descriptor;

    public EditProjectDescriptorBuilder() {
        descriptor = new EditProjectDescriptor();
    }

    public EditProjectDescriptorBuilder(EditProjectDescriptor descriptor) {
        this.descriptor = new EditProjectDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProjectDescriptor} with fields containing {@code project}'s details
     */
    public EditProjectDescriptorBuilder(Project project) {
        descriptor = new EditProjectDescriptor();
        descriptor.setTitle(project.getTitle());
        descriptor.setDeadline(project.getDeadline());
    }

    /**
     * Sets the {@code Title} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    public EditProjectDescriptor build() {
        return descriptor;
    }
}
