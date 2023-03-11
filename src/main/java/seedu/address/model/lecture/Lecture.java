package seedu.address.model.lecture;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.video.UniqueVideoList;
import seedu.address.model.video.Video;

/**
 * Represents a lecture of a module that is in the tracker..
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lecture {

    private final LectureName name;

    private final Set<Tag> tags = new HashSet<>();

    private final UniqueVideoList videos = new UniqueVideoList();

    /**
     * Constructs a {@code Lecture}.
     *
     * @param name The name of the lecture.
     * @param tags The tags applied to the module.
     */
    public Lecture(LectureName name) {
        requireAllNonNull(name);
        this.name = name;
    }

    /**
     * Constructs a {@code Lecture}.
     *
     * @param name The name of the lecture.
     * @param tags The tags applied to the module.
     */
    public Lecture(LectureName name, Set<Tag> tags, List<Video> videos) {
        requireAllNonNull(name, tags, videos);
        this.name = name;
        this.tags.addAll(tags);
        this.videos.setVideos(videos);
    }

    public LectureName getName() {
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
     * Returns an immutable video list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Video> getVideos() {
        return videos.asUnmodifiableList();
    }

    /**
     * Returns true if both lectures have the same name.
     * This defines a weaker notion of equality between two lectures.
     */
    public boolean isSameLecture(Lecture other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getName().equals(getName());
    }

    /**
     * Returns true if both lectures have the same fields.
     * This defines a stronger notion of equality between two lectures.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lecture)) {
            return false;
        }

        Lecture otherLecture = (Lecture) other;
        return otherLecture.getName().equals(getName())
                && otherLecture.getTags().equals(getTags())
                && otherLecture.getVideos().equals(getVideos());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags, videos);
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

        List<Video> videos = getVideos();
        if (!videos.isEmpty()) {
            builder.append("; Videos: ");
            videos.forEach(builder::append);
        }

        return builder.toString();
    }
}
