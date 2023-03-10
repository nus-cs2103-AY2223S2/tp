package seedu.address.model.video;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a video of a lecture that belongs to a module that is in the tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Video {

    private final VideoName name;

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Video}.
     *
     * @param name The name of the video.
     * @param tags The tags applied to the module.
     */
    public Video(VideoName name, Set<Tag> tags) {
        requireNonNull(name);
        this.name = name;
        this.tags.addAll(tags);
    }

    public VideoName getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both videos have the same name.
     * This defines a weaker notion of equality between two videos.
     */
    public boolean isSameVideo(Video other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getName().equals(getName());
    }

    /**
     * Returns true if both videos have the same fields.
     * This defines a stronger notion of equality between two videos.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Video)) {
            return false;
        }

        Video otherVideo = (Video) other;
        return otherVideo.getName().equals(getName())
                && otherVideo.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
