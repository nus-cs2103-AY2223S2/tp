package seedu.address.model.video;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a video of a lecture.<p>
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Video {

    private final VideoName name;
    private final boolean hasWatched;

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Video}.
     *
     * @param name The name of the video.
     * @param tags The tags applied to the video.
     */
    public Video(VideoName name, boolean hasWatched, Set<Tag> tags) {
        requireAllNonNull(name, hasWatched, tags);

        this.name = name;
        this.hasWatched = hasWatched;

        this.tags.addAll(tags);
    }

    public VideoName getName() {
        return name;
    }

    public boolean hasWatched() {
        return hasWatched;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable tag set.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both videos have the same name.<p>
     * This defines a weaker notion of equality between two videos.
     *
     * @param other The video to check if this video is the same with.
     * @return True if both videos have the same name. Otherwise, false.
     */
    public boolean isSameVideo(Video other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getName().equals(getName());
    }

    /**
     * Returns true if both videos have the same fields.<p>
     * This defines a stronger notion of equality between two videos.
     *
     * @param other The video to check if it is equivalent to this video.
     * @return True if both videos have the same fields. Otherwise, false.
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
                && otherVideo.hasWatched() == hasWatched()
                && otherVideo.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, hasWatched, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        builder.append(String.format("; %s", hasWatched ? "Watched" : "Not Watched"));

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
