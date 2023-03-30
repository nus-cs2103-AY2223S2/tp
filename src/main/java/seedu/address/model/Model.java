package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<? super ReadOnlyModule> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<? super ReadOnlyModule> PREDICATE_HIDE_ALL_MODULES = unused -> false;

    /** {@code Predicate} that always evaluate to false */
    Predicate<? super ReadOnlyLecture> PREDICATE_HIDE_ALL_LECTURES = unused -> false;

    /** {@code Predicate} that always evaluate to false */
    Predicate<? super Video> PREDICATE_HIDE_ALL_VIDEOS = unused -> false;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs The data to replace the current user prefs data.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     *
     * @return The user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns Navigation.
     */
    ReadOnlyNavigation getNavigation();

    /**
     * Returns current navigation context.
     */
    NavigationContext getCurrentNavContext();

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return The user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings The GUI settings to set the user prefs's GUI settings to.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' tracker file path.
     *
     * @return The user prefs' tracker file path.
     */
    Path getTrackerFilePath();

    /**
     * Sets the user prefs' tracker file path.
     *
     * @param trackerFilePath The file path to set the user prefs' tracker file path to.
     */
    void setTrackerFilePath(Path trackerFilePath);

    /**
     * Replaces tracker data with the data in {@code tracker}.
     *
     * @param tracker The tracker whose data will replace the current tracker data.
     */
    void setTracker(ReadOnlyTracker tracker);

    /**
     * Returns the tracker.
     *
     * @return The tracker.
     */
    ReadOnlyTracker getTracker();

    /**
     * Clears the tracker.
     */
    void clearTracker();

    /**
     * Returns true if a module with the same code as {@code module} exists in the tracker.
     *
     * @param module The module to check if exist.
     * @return True if a module with the same code as {@code module} exists in the tracker. Otherwise, false.
     */
    boolean hasModule(ReadOnlyModule module);

    /**
     * Returns true if a module which has {@code moduleCode} exists in the tracker.
     *
     * @param moduleCode The code of the module to check if it exist.
     * @return True if a module that has {@code moduleCode} exists in the tracker. Otherwise, false.
     */
    boolean hasModule(ModuleCode moduleCode);

    /**
     * Returns a module which has {@code moduleCode}.
     * The module must exist in the tracker.
     *
     * @param moduleCode The code of the module to return
     * @return Module that has {@code moduleCode}.
     */
    ReadOnlyModule getModule(ModuleCode moduleCode);

    /**
     * Deletes the given module. <p>
     * The module must exist in the tracker.
     *
     * @param target The module to be deleted.
     */
    void deleteModule(ReadOnlyModule target);

    /**
     * Adds the given module.
     *
     * @param module The module to be added.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.<p>
     * {@code target} must exist in the tracker.<p>
     * The module code of {@code editedModule} must not be the same as another existing module in the tracker.
     *
     * @param target The module to be replaced.
     * @param editedModule The module that will replace.
     */
    void setModule(ReadOnlyModule target, Module editedModule);

    /**
     * Returns true if a module of {@code moduleCode} has a lecture with {@code lectureName}.
     *
     * @param moduleCode The moduleCode of the module that the lecture with {@code lectureName} belongs to.
     * @return True if a module that has {@code moduleCode} has a lecture with {@code lectureName}. Otherwise, false.
     */
    boolean hasLecture(ModuleCode moduleCode, LectureName lectureName);

    /**
     * Returns a lecture with {@code lectureName} in module of {@code moduleCode}
     * The module must exist in the tracker.
     * The lecture must exist in {@code module}
     *
     * @param moduleCode The moduleCode of the module that the lecture with {@code lecturename} belongs to.
     * @return Lecture with {@code lectureName} found in module of {@code moduleCode}
     */
    ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName);

    /**
     * Deletes the given lecture {@code target}.<p>
     * The lecture must exist in {@code module}.
     *
     * @param module The module to delete the lecture from.
     * @param target The lecture to be deleted.
     */
    void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target);

    /**
     * Adds the given lecture to {@code module}.
     *
     * @param module The module to add the lecture to.
     * @param lecture The lecture to add.
     */
    void addLecture(ReadOnlyModule module, Lecture lecture);

    /**
     * Replaces the given lecture {@code target} with {@code editedLecture}.<p>
     * {@code target} must exist in {@code module}.<p>
     * The name of {@code editedLecture} must not be the same as another existing lecture in {@code module}.
     *
     * @param module The module whose lecture is to be replaced.
     * @param target The lecture to be replaced.
     * @param editedLecture The lecture that will replace.
     */
    void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture);

    /**
     * Returns true if a video with the name {@code videoName} exists in a lecture with the name {@code lectureName}
     * which exists in a module with code {@code moduleCode}.
     *
     * @param moduleCode The code of the module containing the lecture with name {@code lectureName}.
     * @param lectureName The name of the lecture to check if it contains the video.
     * @param videoName The video name to check if exist.
     * @return True if a video with the name {@code videoName} exists in a lecture with the name {@code lectureName}
     *         which exists in a module with code {@code moduleCode}. Otherwise, false.
     */
    boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName);

    /**
     * Returns the video with the name {@code videoName} which exists in a lecture with the name {@code lectureName}
     * which exists in a module with code {@code moduleCode}.<p>
     *
     * Returns null if the video does not exist.
     *
     * @param moduleCode The code of the module containing the lecture with name {@code lectureName}.
     * @param lectureName The name of the lecture containing the video with name {@code videoName}.
     * @param videoName The name of the video.
     * @return The video with the name {@code videoName} which exists in a lecture with the name {@code lectureName}
     *         which exists in a module with code {@code moduleCode}. Null, if the video does not exist.
     */
    Video getVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName);

    /**
     * Deletes the given video {@code target}.<p>
     * The video must exist in {@code lecture}.
     *
     * @param lecture The lecture to delete the video from.
     * @param video The video to be deleted.
     */
    void deleteVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Adds the given video to {@code lecture}.
     *
     * @param lecture The lecture to add the video to.
     * @param video The video to add.
     */
    void addVideo(ReadOnlyLecture lecture, Video video);

    /**
     * Replaces the given video {@code target} with {@code editedVideo}.<p>
     * {@code target} must exist in {@code lecture}.<p>
     * The name of {@code editedVideo} must not be the same as another existing video in {@code lecture}.
     *
     * @param lecture The lecture whose video is to be replaced.
     * @param target The video to be replaced.
     * @param editedVideo The video that will replace.
     */
    void setVideo(ReadOnlyLecture lecture, Video target, Video editedVideo);

    /**
     * Returns an unmodifiable view of the filtered module list.
     *
     * @return An unmodifiable view of the filtered module list.
     */
    ObservableList<? extends ReadOnlyModule> getFilteredModuleList();

    /**
     * Returns an unmodifiable view of the filtered lecture list.
     *
     * @return An unmodifiable view of the filtered lecture list.
     */
    ObservableList<? extends ReadOnlyLecture> getFilteredLectureList();

    /**
     * Returns an unmodifiable view of the filtered lecture list.
     *
     * @return An unmodifiable view of the filtered lecture list.
     */
    ObservableList<? extends Video> getFilteredVideoList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter modules by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<? super ReadOnlyModule> predicate);

    /**
     * Updates the filter of the filtered lecture list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter lecture by.
     * @param module The module that the lecture list belongs to.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLectureList(Predicate<? super ReadOnlyLecture> predicate, ReadOnlyModule module);

    /**
     * Updates the filter of the filtered video list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter video by.
     * @param moduleCode The module code that the lecture belongs to.
     * @param lecture The lecture that the video list belongs to.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVideoList(Predicate<Video> predicate,
            ModuleCode moduleCode, ReadOnlyLecture lecture);

    /**
     * Returns the last list type to displayed to user.
     */
    DisplayListLevel getLastListLevel();

    /**
     * Returns the module of the listed lectures or listed videos.
     */
    ReadOnlyModule getListedLecturesByModule();

    /**
     * Returns the lecture of the listed videos.
     */
    ReadOnlyLecture getListedVideosByLecture();

    /**
     * Navigates to the parent layer.
     */
    void navigateBack();

    /**
     * Navigates to the root layer.
     */
    void navigateToRoot();

    /**
     * Navigates to the specified module layer of module code.
     */
    void navigateTo(ModuleCode moduleCode);

    /**
     * Navigates to the lecture layer of lecture name belonging to module layer of module code.
     */
    void navigateTo(ModuleCode moduleCode, LectureName lectureName);

    /**
     * Navigates to the module layer of module code only from root layer.
     */
    void navigateToModFromRoot(ModuleCode moduleCode);

    /**
     * Navigates to the lecture layer of lecture name only from module layer.
     */
    void navigateToLecFromMod(LectureName lectureName);

}
