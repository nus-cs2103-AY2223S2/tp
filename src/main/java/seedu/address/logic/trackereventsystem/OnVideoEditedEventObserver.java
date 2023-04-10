package seedu.address.logic.trackereventsystem;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;

/**
 * Represents an observer that is triggered when a video is added, edited, or deleted.
 */
public interface OnVideoEditedEventObserver {
    /**
     * Called when a video is added, edited, or deleted.
     *
     * @param moduleCode The code of the module that the lecture with name {@code lectureName} belongs to.
     * @param lectureName The name of the lecture that the edited video belongs to.
     * @param originalVideo The original video. {@code null} if the event was triggered by a video being added.
     * @param editedVideo The edited video. {@code null} if the event was triggered by a video being deleted.
     */
    void onVideoEdited(ModuleCode moduleCode, LectureName lectureName, Video originalVideo, Video editedVideo);
}
