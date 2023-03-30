package seedu.address.logic.trackereventsystem;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;

// TODO: Test this
/**
 * An event system that is triggered when a command makes modifications to the {@code Tracker}.
 */
public class TrackerEventSystem {

    private final List<OnModuleEditedEventObserver> onModuleEditedEventObservers = new ArrayList<>();
    private final List<OnLectureEditedEventObserver> onLectureEditedEventObservers = new ArrayList<>();
    private final List<OnVideoEditedEventObserver> onVideoEditedEventObservers = new ArrayList<>();

    /**
     * Add observer(s) to the "on module edited" event which is triggered when a module is added, edited, or
     * deleted.
     *
     * @param observer The observer to be added.
     */
    public void addOnModuleModifiedObserver(OnModuleEditedEventObserver... observer) {
        requireNonNull(observer);
        onModuleEditedEventObservers.addAll(Arrays.asList(observer));
    }

    /**
     * Add observer(s) to the "on lecture edited" event which is triggered when a lecture is added, edited,
     * or deleted.
     *
     * @param observer The observer to be added.
     */
    public void addOnLectureModifiedObserver(OnLectureEditedEventObserver... observer) {
        requireNonNull(observer);
        onLectureEditedEventObservers.addAll(Arrays.asList(observer));
    }

    /**
     * Adds an observer to the "on video edited" event which is triggered when a video is added, edited, or
     * deleted.
     *
     * @param observer The observer to be added.
     */
    public void addOnVideoModifiedObserver(OnVideoEditedEventObserver observer) {
        requireNonNull(observer);
        onVideoEditedEventObservers.add(observer);
    }

    /**
     * Remove observer(s) from the "on module edited" event which is triggered when a module is added,
     * edited, or deleted.
     *
     * @param observer The observer to be removed.
     */
    public void removeOnModuleModifiedObserver(OnModuleEditedEventObserver... observer) {
        requireNonNull(observer);
        onModuleEditedEventObservers.removeAll(Arrays.asList(observer));
    }

    /**
     * Removes an observer from the "on lecture edited" event which is triggered when a lecture is added,
     * edited, or deleted.
     *
     * @param observer The observer to be removed.
     */
    public void removeOnLectureModifiedObserver(OnLectureEditedEventObserver... observer) {
        requireNonNull(observer);
        onLectureEditedEventObservers.removeAll(Arrays.asList(observer));
    }

    /**
     * Removes an observer from the "on video edited" event which is triggered when a video is added, edited,
     * or deleted.
     *
     * @param observer The observer to be removed.
     */
    public void removeOnVideoModifiedObserver(OnVideoEditedEventObserver observer) {
        requireNonNull(observer);
        onVideoEditedEventObservers.remove(observer);
    }

    /**
     * Triggers the "on module edited" event.<p>
     *
     * Should be called when a module is added, edited, or deleted.
     *
     * @param originalModule The original module. {@code null} if the event was triggered by a module being added.
     * @param editedModule The edited module. {@code null} if the event was triggered by a module being deletd.
     */
    public void triggerOnModuleEditedEvent(ReadOnlyModule originalModule, ReadOnlyModule editedModule) {
        for (OnModuleEditedEventObserver observer : onModuleEditedEventObservers) {
            observer.onModuleEdited(originalModule, editedModule);
        }
    }

    /**
     * Triggers the "on lecture edited" event. <p>
     *
     * Should be called when a lecture is added, edited, or deleted.
     *
     * @param moduleCode The code of the module that the edited lecture belongs to.
     * @param originalLecture The original lecture. {@code null} if the event was triggered by a lecture being added.
     * @param editedLecture The edited lecture. {@code null} if the event was triggerd by a lecture being deleted.
     */
    public void triggerOnLectureEditedEvent(ModuleCode moduleCode, ReadOnlyLecture originalLecture,
            ReadOnlyLecture editedLecture) {

        for (OnLectureEditedEventObserver observer : onLectureEditedEventObservers) {
            observer.onLectureEdited(moduleCode, originalLecture, editedLecture);
        }
    }

    /**
     * Triggers the "on video edited" event. <p>
     *
     * Should be called when a video is added, edited, or deleted.
     *
     * @param moduleCode The code of the module that the lecture with name {@code lectureName} belongs to.
     * @param lectureName The name of the lecture that the edited video belongs to.
     * @param originalVideo The original video. {@code null} if the event was triggered by a video being added.
     * @param editedVideo The edited video. {@code null} if the event was triggered by a video being deleted.
     */
    public void triggerOnVideoEditedEvent(ModuleCode moduleCode, LectureName lectureName, Video originalVideo,
            Video editedVideo) {
        for (OnVideoEditedEventObserver observer : onVideoEditedEventObservers) {
            observer.onVideoEdited(moduleCode, lectureName, originalVideo, editedVideo);
        }
    }

}
