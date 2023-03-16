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
     *
     * @return The name of the lecture.
     */
    public LectureName getName();

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable tag set.
     */
    public Set<Tag> getTags();

    /**
     * Returns an immutable video list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable video list.
     */
    public ObservableList<Video> getVideoList();

    /**
     * Returns the video whose name is the same as {@code name}. If no such video exist, return null.
     *
     * @param name The name of the video to get.
     * @return The video whose name is the same as {@code name}. If no such video exist, return null.
     */
    public Video getVideo(VideoName name);

    /**
     * Returns true if a video with the same name as {@code video} exists in the lecture.
     *
     * @param video The video to check if it is in the lecture.
     * @return True if a video with the same name as {@code video} exists in the lecture. Otherwise, false.
     */
    public boolean hasVideo(Video video);

    /**
     * Returns true if a video with the name {@code videoName} exists in the lecture.
     *
     * @param videoName The video name to check if it is in the lecture.
     * @return True if a video with the name {@code videoName} exists in the lecture. Otherwise, false.
     */
    public boolean hasVideo(VideoName videoName);

    /**
     * Returns true if both lectures have the same name.<p>
     * This defines a weaker notion of equality between two lectures.
     *
     * @param other The lecture to check if this lecture is the same with.
     * @return True if both lectures have the same name. Otherwise, false.
     */
    public boolean isSameLecture(Lecture other);
}
