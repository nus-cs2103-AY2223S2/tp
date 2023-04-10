package seedu.address.model.video;

import java.util.List;

import seedu.address.model.SortedUniqueDataList;
import seedu.address.model.video.exceptions.DuplicateVideoException;
import seedu.address.model.video.exceptions.VideoNotFoundException;

// TODO: Test this
/**
 * A list of sorted videos that enforces uniqueness between its elements and does not allow nulls.<p>
 *
 * A video is sorted by using the {@code Video#compareTo(Video)} method.<p>
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
 * @see Video#compareTo(Video)
 * @see Video#isSameVideo(Video)
 */
public class UniqueVideoList extends SortedUniqueDataList<Video> {

    /**
     * Constructs a {@code UniqueVideoList}.
     */
    public UniqueVideoList() {
        super((a, b) -> a.isSameVideo(b), DuplicateVideoException::new, VideoNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     *
     * @param toAdd {@inheritDoc}
     * @throws DuplicateVideoException Indicates that {@code toAdd} already exist in the list.
     */
    @Override
    public void add(Video toAdd) {
        super.add(toAdd);
    }

    /**
     * {@inheritDoc}
     *
     * @param toRemove {@inheritDoc}
     * @throws VideoNotFoundException Indicates that the video does not exist in the list.
     */
    @Override
    public void remove(Video toRemove) {
        super.remove(toRemove);
    }

    /**
     * Replaces the video {@code target} in the list with {@code editedVideo}.<p>
     *
     * @param target The video to be replaced. It must exist in the list.
     * @param editedVideo The video that will replace. It must not have the same name as another existing
     *                    video in the list.
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
     * Replaces the contents of this list with {@code videos}.
     *
     * @param videos The list containing the videos that will replace. It must not contain duplicate videos.
     */
    public void setVideos(List<Video> videos) {
        super.setAllData(videos);
    }
}
