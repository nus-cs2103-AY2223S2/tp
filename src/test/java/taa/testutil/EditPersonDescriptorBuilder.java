package taa.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import taa.logic.commands.EditStudentCommand;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditStudentCommand.EditStudentDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditStudentCommand.EditStudentDescriptor();
    }

    public EditPersonDescriptorBuilder(EditStudentCommand.EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentCommand.EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditPersonDescriptorBuilder(Student student) {
        descriptor = new EditStudentCommand.EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setTags(student.getClassTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditStudentCommand.EditStudentDescriptor build() {
        return descriptor;
    }
}
