package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building {@code EditLectureDescriptor} objects.
 */
public class EditLectureDescriptorBuilder {

    private final EditLectureDescriptor descriptor;

    /**
     * Constructs an {@code EditLectureDescriptorBuilder}.
     */
    public EditLectureDescriptorBuilder() {
        descriptor = new EditLectureDescriptor();
    }

    /**
     * Constructs an {@code EditLectureDescriptorBuilder} with the data of {@code descriptor}.
     *
     * @param descriptor The {@code EditLectureDescriptor} containing the data to copy.
     */
    public EditLectureDescriptorBuilder(EditLectureDescriptor descriptor) {
        requireNonNull(descriptor);

        this.descriptor = new EditLectureDescriptor(descriptor);
    }

    /**
     * Constructs an {@code EditLectureDescriptorBuilder} with fields containing the details of {@code lecture}.
     *
     * @param lecture The lecture whose details will be used.
     */
    public EditLectureDescriptorBuilder(Lecture lecture) {
        requireNonNull(lecture);

        descriptor = new EditLectureDescriptor();
        descriptor.setName(lecture.getName());
        descriptor.setTags(lecture.getTags());
    }

    /**
     * Sets the {@code name} of the {@code EditLectureDescriptor} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code EditLectureDescriptorBuilder}.
     */
    public EditLectureDescriptorBuilder withName(String name) {
        descriptor.setName(name == null ? null : new LectureName(name));
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code EditLectureDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditLectureDescriptorBuilder}.
     */
    public EditLectureDescriptorBuilder withTags(String... tags) {
        return withTags(Arrays.asList(tags));
    }

    /**
     * Sets the {@code tags} of the {@code EditLectureDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditLectureDescriptorBuilder}.
     */
    public EditLectureDescriptorBuilder withTags(Collection<String> tags) {
        descriptor.setTags(tags == null ? null : tags.stream().map(Tag::new).collect(Collectors.toSet()));
        return this;
    }

    /**
     * Returns the {@code EditLectureDescriptor} that we built.
     *
     * @return The {@code EditLectureDescriptor} that we built.
     */
    public EditLectureDescriptor build() {
        return descriptor;
    }

}
