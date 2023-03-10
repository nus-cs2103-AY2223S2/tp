package seedu.address.model.video;

import java.util.List;

import seedu.address.model.UniqueDataList;
import seedu.address.model.video.exceptions.DuplicateVideoException;
import seedu.address.model.video.exceptions.VideoNotFoundException;

/**
 * A list of videos that enforces uniqueness between its elements and does not allow nulls. A video is considered unique
 * by comparing using {@code Video#isSameVideo(Video)}. As such, adding and updating of videos uses
 * Video#isSameVideo(Video) for equality so as to ensure that the video being added or updated is unique in terms of
 * name in the UniqueVideoList. However, the removal of a video uses Video#equals(Object) so as to ensure that the
 * video with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Video#isSameVideo(Video)
 */
public class UniqueVideoList extends UniqueDataList<Video> {

    public UniqueVideoList() {
        super((a, b) -> a.isSameVideo(b), DuplicateVideoException::new, VideoNotFoundException::new);
    }

    /**
     * Returns true if the list contains an equivalent video as the given argument.
     */
    @Override
    public boolean contains(Video toCheck) {
        return super.contains(toCheck);
    }

    /**
     * Adds a video to the list.
     * The video must not already exist in the list.
     */
    @Override
    public void add(Video toAdd) {
        super.add(toAdd);
    }

    /**
     * Replaces the video {@code target} in the list with {@code editedVideo}.
     * {@code target} must exist in the list.
     * The name of {@code editedVideo} must not be the same as another existing video in the list.
     */
    public void setVideo(Video target, Video editedVideo) {
        super.setData(target, editedVideo);
    }

    /**
     * Removes the equivalent video from the list.
     * The video must exist in the list.
     */
    @Override
    public void remove(Video toRemove) {
        super.remove(toRemove);
    }

    public void setVideos(UniqueVideoList replacement) {
        super.setAllData(replacement);
    }

    /**
     * Replaces the contents of this list with {@code videos}.
     * {@code videos} must not contain duplicate videos.
     */
    public void setVideos(List<Video> videos) {
        super.setAllData(videos);
    }
}
