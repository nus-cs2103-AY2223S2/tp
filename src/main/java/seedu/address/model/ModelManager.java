package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
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
 * Represents the in-memory model of the tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tracker tracker;
    private final UserPrefs userPrefs;
    private final Navigation navigation;
    private ReadOnlyModule listedLecturesByModule;
    private ReadOnlyLecture listedVideosByLecture;
    private FilteredList<? extends ReadOnlyModule> filteredModules;
    private FilteredList<? extends ReadOnlyLecture> filteredLectures;
    private FilteredList<? extends Video> filteredVideos;
    private DisplayListLevel lastListLevel;

    /**
     * Constructs a {@code ModelManager} using the provided {@code tracker} and {@code userPrefs}.
     *
     * @param tracker The tracker.
     * @param userPrefs The user prefs.
     */
    public ModelManager(ReadOnlyTracker tracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tracker, userPrefs);

        logger.fine("Initializing with tracker: " + tracker + " and user prefs " + userPrefs);

        this.tracker = new Tracker(tracker);
        this.userPrefs = new UserPrefs(userPrefs);
        this.navigation = new NavigationStack();
        filteredModules = new FilteredList<>(this.tracker.getModuleList());
        lastListLevel = DisplayListLevel.MODULE;
    }

    /**
     * Constructs a {@code ModelManager}.
     */
    public ModelManager() {
        this(new Tracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTrackerFilePath() {
        return userPrefs.getTrackerFilePath();
    }

    @Override
    public void setTrackerFilePath(Path trackerFilePath) {
        userPrefs.setTrackerFilePath(trackerFilePath);
    }

    //=========== Tracker ====================================================================================

    @Override
    public void setTracker(ReadOnlyTracker tracker) {
        this.tracker.resetData(tracker);
    }

    @Override
    public ReadOnlyTracker getTracker() {
        return tracker;
    }

    @Override
    public void clearTracker() {
        tracker.clear();
    }

    @Override
    public boolean hasModule(ReadOnlyModule module) {
        return tracker.hasModule(module);
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return tracker.hasModule(moduleCode);
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        return tracker.getModule(moduleCode);
    }

    @Override
    public void deleteModule(ReadOnlyModule target) {
        tracker.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        tracker.addModule(module);
    }

    @Override
    public void setModule(ReadOnlyModule target, Module editedModule) {
        tracker.setModule(target, editedModule);
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return getLecture(moduleCode, lectureName) != null;
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        ReadOnlyModule mod = tracker.getModule(moduleCode);
        return mod == null ? null : mod.getLecture(lectureName);
    }

    @Override
    public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
        requireNonNull(module);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Module) module).removeLecture(target);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void addLecture(ReadOnlyModule module, Lecture lecture) {
        requireNonNull(module);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Module) module).addLecture(lecture);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture) {
        requireNonNull(module);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Module) module).setLecture(target, editedLecture);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return getVideo(moduleCode, lectureName, videoName) != null;
    }

    @Override
    public Video getVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        requireAllNonNull(moduleCode, lectureName, videoName);

        ReadOnlyModule mod = tracker.getModule(moduleCode);
        if (mod == null) {
            return null;
        }

        ReadOnlyLecture lec = mod.getLecture(lectureName);
        if (lec == null) {
            return null;
        }

        return lec.getVideo(videoName);
    }

    @Override
    public void deleteVideo(ReadOnlyLecture lecture, Video video) {
        requireNonNull(lecture);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Lecture) lecture).removeVideo(video);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void addVideo(ReadOnlyLecture lecture, Video video) {
        requireNonNull(lecture);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Lecture) lecture).addVideo(video);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    @Override
    public void setVideo(ReadOnlyLecture lecture, Video target, Video editedVideo) {
        requireNonNull(lecture);
        //CHECKSTYLE.OFF: SeparatorWrap
        ((Lecture) lecture).setVideo(target, editedVideo);
        //CHECKSTYLE.ON: SeparatorWrap
    }

    //=========== Filtered List Accessors =============================================================

    @Override
    public void updateAllFilteredListAsHidden() {
        filteredModules.setPredicate(PREDICATE_HIDE_ALL_MODULES);
        if (filteredLectures != null) {
            filteredLectures.setPredicate(PREDICATE_HIDE_ALL_LECTURES);
        }
        if (filteredVideos != null) {
            filteredVideos.setPredicate(PREDICATE_HIDE_ALL_VIDEOS);
        }
    }

    @Override
    public DisplayListLevel getLastListLevel() {
        return lastListLevel;
    };

    private DisplayListLevel setLastListLevel(DisplayListLevel listLevel) {
        return lastListLevel = listLevel;
    };

    @Override
    public ReadOnlyModule getListedLecturesByModule() {
        return listedLecturesByModule;
    };

    @Override
    public ReadOnlyLecture getListedVideosByLecture() {
        return listedVideosByLecture;
    }

    //=========== Filtered Module List Accessors =============================================================

    @Override
    public ObservableList<? extends ReadOnlyModule> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<? super ReadOnlyModule> predicate) {
        requireNonNull(predicate);
        filteredModules = new FilteredList<>(this.tracker.getModuleList());
        filteredModules.setPredicate(predicate);
        setLastListLevel(DisplayListLevel.MODULE);

        // Hide other list components
        if (filteredLectures != null) {
            filteredLectures.setPredicate(PREDICATE_HIDE_ALL_LECTURES);
        }
        if (filteredVideos != null) {
            filteredVideos.setPredicate(PREDICATE_HIDE_ALL_VIDEOS);
        }
    }

    //=========== Filtered Lecture List Accessors =============================================================

    @Override
    public ObservableList<? extends ReadOnlyLecture> getFilteredLectureList() {
        return filteredLectures;
    }

    @Override
    public void updateFilteredLectureList(Predicate<? super ReadOnlyLecture> predicate, ReadOnlyModule module) {
        requireNonNull(predicate);
        listedLecturesByModule = module;
        filteredLectures = new FilteredList<>(module.getLectureList());
        filteredLectures.setPredicate(predicate);
        setLastListLevel(DisplayListLevel.LECTURE);

        // Hide other list components
        filteredModules.setPredicate(PREDICATE_HIDE_ALL_MODULES);
        if (filteredVideos != null) {
            filteredVideos.setPredicate(PREDICATE_HIDE_ALL_VIDEOS);
        }
    }

    //=========== Filtered Video List Accessors =============================================================

    @Override
    public ObservableList<? extends Video> getFilteredVideoList() {
        return filteredVideos;
    }

    @Override
    public void updateFilteredVideoList(Predicate<Video> predicate, ModuleCode moduleCode,
            ReadOnlyLecture lecture) {
        requireNonNull(predicate);
        listedLecturesByModule = this.tracker.getModule(moduleCode);
        listedVideosByLecture = lecture;
        filteredVideos = new FilteredList<>(lecture.getVideoList());
        filteredVideos.setPredicate(predicate);
        setLastListLevel(DisplayListLevel.VIDEO);

        // Hide other list components
        filteredModules.setPredicate(PREDICATE_HIDE_ALL_MODULES);
        if (filteredLectures != null) {
            filteredLectures.setPredicate(PREDICATE_HIDE_ALL_LECTURES);
        }
    }

    //=========== Navigation =================================================================================

    @Override
    public ReadOnlyNavigation getNavigation() {
        return navigation;
    }

    @Override
    public NavigationContext getCurrentNavContext() {
        return navigation.getCurrentContext();
    }

    @Override
    public void navigateTo(ModuleCode moduleCode) {
        navigation.navigateTo(moduleCode);
    }

    @Override
    public void navigateTo(ModuleCode moduleCode, LectureName lectureName) {
        navigation.navigateTo(moduleCode, lectureName);
    }

    @Override
    public void navigateToModFromRoot(ModuleCode moduleCode) {
        navigation.navigateToModFromRoot(moduleCode);
    }

    @Override
    public void navigateToLecFromMod(LectureName lectureName) {
        navigation.navigateToLecFromMod(lectureName);
    }

    @Override
    public void navigateBack() {
        navigation.back();;
    }

    @Override
    public void navigateToRoot() {
        navigation.goToRoot();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        boolean isTrackerEqual = tracker.equals(other.tracker);
        boolean isUserPrefsEqual = userPrefs.equals(other.userPrefs);
        boolean isNavigationEqual = navigation.equals(other.navigation);
        boolean isLastListEqual = lastListLevel.equals(other.lastListLevel);
        boolean isFilteredModulesEqual = filteredModules.equals(other.filteredModules);
        boolean isFilteredLecturesEqual = filteredLectures == null
                ? other.filteredLectures == null
                : filteredLectures.equals(other.filteredLectures);
        boolean isFilteredVideosEqual = filteredVideos == null
                ? other.filteredVideos == null
                : filteredVideos.equals(other.filteredVideos);
        boolean isListedLecturesByModuleEqual = listedLecturesByModule == null
                ? other.listedLecturesByModule == null
                : listedLecturesByModule.equals(other.listedLecturesByModule);
        boolean isListedVideosByLectureEqual = listedVideosByLecture == null
                ? other.listedVideosByLecture == null
                : listedVideosByLecture.equals(other.listedVideosByLecture);

        return isTrackerEqual && isUserPrefsEqual && isNavigationEqual && isLastListEqual
                && isFilteredModulesEqual && isFilteredLecturesEqual && isFilteredVideosEqual
                && isListedLecturesByModuleEqual && isListedVideosByLectureEqual;

    }

}
