package seedu.address.model.video;

import java.util.List;

import seedu.address.model.UniqueDataList;
import seedu.address.model.video.exceptions.DuplicateVideoException;
import seedu.address.model.video.exceptions.VideoNotFoundException;

/**
 * A list of videos that enforces uniqueness between its elements and does not allow nulls.<p>
 *
 * A video is considered unique by comparing using {@code Video#isSameVideo(Video)}. As such, adding and updating of
 * videos uses {@code Video#isSameVideo(Video)} for equality so as to ensure that the video being added or updated is
 * unique in terms of name in the {@code UniqueVideoList}.<p>
 *
 * However, the removal of a video uses {@code Video#equals(Object)} so as to ensure that the video with exactly the
 * same fields will be removed.<p>
 *
 * Supports a minimal set of list operations.<p>
 *
 * @see Video#isSameVideo(Video)
 */
public class UniqueVideoList extends UniqueDataList<Video> {

    /**
     * Constructs a {@code UniqueVideoList}.
     */
    public UniqueVideoList() {
        super((a, b) -> a.isSameVideo(b), DuplicateVideoException::new, VideoNotFoundException::new);
    }

    /**
     * Replaces the video {@code target} in the list with {@code editedVideo}.<p>
     * {@code target} must exist in the list.<p>
     * {@code editedVideo} must not have the same name as another existing video in the list.
     *
     * @param target The video to be replaced.
     * @param editedVideo The video that will replace.
     * @throws VideoNotFoundException Indicates that {@code target} does not exist in the list.
     * @throws DuplicateVideoException Indicates that {@code editedVideo} is the same as another existing
     *                                 video in the list.
     */
    public void setVideo(Video target, Video editedVideo) {
        super.setData(target, editedVideo);
    }

    /**
     * Replaces the content of this list with {@code replacement}.
     *
     * @param replacement The list containing the video that will replace.
     */
    public void setVideos(UniqueVideoList replacement) {
        super.setAllData(replacement);
    }

    /**
     * Replaces the contents of this list with {@code videos}.<p>
     * {@code videos} must not contain duplicate videos.
     *
     * @param videos The list containing the videos that will replace.
     */
    public void setVideos(List<Video> videos) {
        super.setAllData(videos);
    }
}
