package seedu.address.logic.trackereventsystem;

import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;

/**
 * Represents an observer that is triggered when a lecture is added, edited, or deleted.
 */
public interface OnLectureEditedEventObserver {
    /**
     * Called when a lecture is edited.
     *
     * @param moduleCode The code of the module that the edited lecture belongs to.
     * @param originalLecture The original lecture. {@code null} if the event was triggered by a lecture being added.
     * @param editedLecture The edited lecture. {@code null} if the event was triggerd by a lecture being deleted.
     */
    void onLectureEdited(ModuleCode moduleCode, ReadOnlyLecture originalLecture, ReadOnlyLecture editedLecture);
}
