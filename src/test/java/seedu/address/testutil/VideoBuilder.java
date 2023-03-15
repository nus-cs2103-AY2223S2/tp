package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * A utility class to help with building {@code Video} objects.
 */
public class VideoBuilder {

    public static final String DEFAULT_NAME = "Vid 1";
    public static final boolean DEFAULT_HAS_WATCHED = false;

    private VideoName name;
    private boolean hasWatched;
    private Set<Tag> tags;

    /**
     * Creates a {@code VideoBuilder} with the default details.
     */
    public VideoBuilder() {
        name = new VideoName(DEFAULT_NAME);
        hasWatched = DEFAULT_HAS_WATCHED;
        tags = new HashSet<>();
    }

    /**
     * Initializes the {@code VideoBuilder} with the data of {@code videoToCopy}.
     *
     * @param videoToCopy The video containing the data to copy.
     */
    public VideoBuilder(Video videoToCopy) {
        name = videoToCopy.getName();
        hasWatched = videoToCopy.hasWatched();
        tags = new HashSet<>(videoToCopy.getTags());
    }

    /**
     * Sets the {@code name} of the {@code Video} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code VideoBuilder}.
     */
    public VideoBuilder withName(String name) {
        this.name = new VideoName(name);
        return this;
    }

    /**
     * Sets the {@code hasWatched} of the {@code Video} that we are building.
     *
     * @param hasWatched The watch status to set to.
     * @return This {@code VideoBuilder}.
     */
    public VideoBuilder withHasWatched(boolean hasWatched) {
        this.hasWatched = hasWatched;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Video} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code VideoBuilder}.
     */
    public VideoBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Returns a {@code Video} object with values set to those of this object.
     *
     * @return A {@code Video} object with values set to those of this object.
     */
    public Video build() {
        return new Video(name, hasWatched, tags);
    }

}
