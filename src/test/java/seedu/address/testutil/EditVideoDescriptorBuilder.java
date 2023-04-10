package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * A utility class to help with building {@code EditVideoDescriptor} objects.
 */
public class EditVideoDescriptorBuilder {

    private final EditVideoDescriptor descriptor;

    /**
     * Constructs an {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder() {
        descriptor = new EditVideoDescriptor();
    }

    /**
     * Constructs an {@code EditVideoDescriptorBuilder} with the data of {@code descriptor}.
     *
     * @param descriptor The {@code EditVideoDescriptor} containing the data to copy.
     */
    public EditVideoDescriptorBuilder(EditVideoDescriptor descriptor) {
        requireNonNull(descriptor);

        this.descriptor = new EditVideoDescriptor(descriptor);
    }

    /**
     * Constructs an {@code EditVideoDescriptorBuilder} with fields containing the details of {@code video}.
     *
     * @param video The video whose details will be used.
     */
    public EditVideoDescriptorBuilder(Video video) {
        requireNonNull(video);

        descriptor = new EditVideoDescriptor();
        descriptor.setName(video.getName());
        descriptor.setWatched(video.hasWatched());
        descriptor.setTags(video.getTags());
    }

    /**
     * Sets the {@code name} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withName(String name) {
        descriptor.setName(name == null ? null : new VideoName(name));
        return this;
    }

    /**
     * Sets the {@code hasWatched} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param hasWatched The watch status to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withWatched(Boolean hasWatched) {
        descriptor.setWatched(hasWatched);
        return this;
    }

    /**
     * Sets the {@code timestamp} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param timestamp The timestamp to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withTimestamp(String timestamp) {
        descriptor.setTimestamp(timestamp == null ? null : new VideoTimestamp(timestamp));
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withTags(String... tags) {
        return withTags(Arrays.asList(tags));
    }

    /**
     * Sets the {@code tags} of the {@code EditVideoDescriptor} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code EditVideoDescriptorBuilder}.
     */
    public EditVideoDescriptorBuilder withTags(Collection<String> tags) {
        descriptor.setTags(tags == null ? null : tags.stream().map(Tag::new).collect(Collectors.toSet()));
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
