package seedu.address.model.lecture;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * Unmodifiable view of a lecture.
 */
public interface ReadOnlyLecture {
    /**
     * Returns the name of the lecture.
     */
    public LectureName getName();

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags();

    /**
     * Returns an immutable video list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Video> getVideoList();

    /**
     * Returns the video whose name is the same as {@code name}. If no such video exist, return null.
     */
    public Video getVideo(VideoName name);

    /**
     * Returns true if a video with the same name as {@code video} exists in the lecture.
     */
    public boolean hasVideo(Video video);

    /**
     * Returns true if both lectures have the same name.
     * This defines a weaker notion of equality between two lectures.
     */
    public boolean isSameLecture(Lecture other);
}
