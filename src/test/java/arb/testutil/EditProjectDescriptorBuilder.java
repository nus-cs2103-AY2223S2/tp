package arb.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.model.tag.Tag;

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
        descriptor.setPrice(project.getPrice());
        descriptor.setTags(project.getTags());
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

    /**
     * Sets the {@code Tags} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    public EditProjectDescriptor build() {
        return descriptor;
    }
}
