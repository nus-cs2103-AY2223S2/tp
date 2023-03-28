package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * A utility class to help with building {@code EditVideoDescriptor} objects.
 */
public class EditVideoDescriptorBuilder {

    private EditVideoDescriptor descriptor;

    /**
     * Creates a {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder() {
        descriptor = new EditVideoDescriptor();
    }

    /**
     * Creates a {@code EditVideoDescriptorBuilder} with the data of {@code descriptor}.
     *
     * @param descriptor The {@code EditVideoDescriptor} containing the data to copy.
     */
    public EditVideoDescriptorBuilder(EditVideoDescriptor descriptor) {
        requireNonNull(descriptor);

        this.descriptor = new EditVideoDescriptor(descriptor);
    }

    /**
     * Creates a {@code EditVideoDescriptorBuilder} with fields containing the {@code video} details.
     *
     * @param video The video.
     */
    public EditVideoDescriptorBuilder(Video video) {
        requireNonNull(video);

        descriptor = new EditVideoDescriptor();
        descriptor.setName(video.getName());
        descriptor.setTags(video.getTags());
    }

    /**
     * Sets the {@code name} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withName(String name) {
        descriptor.setName(new VideoName(name));
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withTags(String... tags) {
        descriptor.setTags(
                Arrays.asList(tags).stream().map(Tag::new).collect(Collectors.toSet()));
        return this;
    }

    /**
     * Returns the {@code EditVideoDescriptor} that we built.
     *
     * @return The {@code EditVideoDescriptor} that we built.
     */
    public EditVideoDescriptor build() {
        return descriptor;
    }

}
