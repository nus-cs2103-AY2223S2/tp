package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.DisplayListLevel;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final String currentContext;

    /** Information on the adding, editing, or deleting of modules. */
    private final List<ModuleEditInfo> moduleEditInfoList = new ArrayList<>();

    /** Information on the adding, editing, or deleting of lectures. */
    private final List<LectureEditInfo> lectureEditInfoList = new ArrayList<>();

    /** Information on the adding, editing, or deleting of videos. */
    private final List<VideoEditInfo> videoEditInfoList = new ArrayList<>();

    /** The level list to be displayed */
    private final DisplayListLevel level;

    /** Information on whether the command is to export tracker to archive */
    private final boolean isExporting;
    /** Information on whether the command is to import all modules from archive to current tracker */
    private final boolean isImportingAllModules;
    /** Information on whether the command to importing modules/ export modules will overwrite existing data */
    private final boolean isOverwritingExistingModules;

    /** The path to access the archive of modules */
    private final Optional<Path> path;

    private final Set<ModuleCode> moduleCodesToImport;




    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, DisplayListLevel level, String context,
            List<ModuleEditInfo> moduleEditInfoList, List<LectureEditInfo> lectureEditInfoList,
            List<VideoEditInfo> videoEditInfoList, Path archivePath, boolean isExporting,
                         boolean isImportingAllModules,
                         boolean isOverwritingExistingModules, Set<ModuleCode> moduleCodesToImport) {

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.level = level;
        this.currentContext = context;
        this.moduleEditInfoList.addAll(requireNonNull(moduleEditInfoList));
        this.lectureEditInfoList.addAll(requireNonNull(lectureEditInfoList));
        this.videoEditInfoList.addAll(requireNonNull(videoEditInfoList));
        this.path = Optional.ofNullable(archivePath);
        this.isExporting = isExporting;
        this.isImportingAllModules = isImportingAllModules;
        this.isOverwritingExistingModules = isOverwritingExistingModules;
        this.moduleCodesToImport = moduleCodesToImport;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp}, and {@code exit},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, null, "", Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(), null, false,
                false, false, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code level},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, DisplayListLevel level) {
        this(feedbackToUser, false, false, level, "", Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(), null, false, false,
                false, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code context},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, String context) {
        this(feedbackToUser, false, false, null, context, Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(), null, false, false, false, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code moduleEditInfos},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ModuleEditInfo... moduleEditInfos) {
        this(feedbackToUser, false, false, null, "", Arrays.asList(moduleEditInfos),
                Collections.emptyList(), Collections.emptyList(), null, false,
                false, false, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code lectureEditInfos},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, LectureEditInfo... lectureEditInfos) {
        this(feedbackToUser, false, false, null, "", Collections.emptyList(),
                Arrays.asList(lectureEditInfos), Collections.emptyList(), null,
                false, false, false, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code videoEditInfos},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, VideoEditInfo... videoEditInfos) {
        this(feedbackToUser, false, false, null, "", Collections.emptyList(),
                Collections.emptyList(), Arrays.asList(videoEditInfos), null,
                false, false, false, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} to alert logic manager to export tracker.
     */
    public CommandResult(String feedbackToUser, Path archivedPath, boolean isExporting, boolean isOverwriting) {
        this(feedbackToUser, false, false, null, "", Collections.emptyList(), Collections.emptyList(),
                Collections.emptyList(), archivedPath, isExporting, false, isOverwriting, new HashSet<>());
    }

    /**
     * Constructs a {@code CommandResult} to alert logic manager to import modules
     */
    public CommandResult(String feedbackToUser, Path archivedPath, boolean isImportingWholeArchive,
                         boolean isOverwriting,
                         Set<ModuleCode> moduleCodesToImport) {
        this(feedbackToUser, false, false, null, "",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                archivedPath, false, isImportingWholeArchive, isOverwriting, moduleCodesToImport);
    }

    /**
     * Constructs a {@code CommandResult} to alert TrackerEventSystem to update ObservableList
     */
    public CommandResult(String feedbackToUser, Path archivedPath, boolean isImportingWholeArchive,
                         boolean isOverwriting,
                         Set<ModuleCode> moduleCodesToImport, List<ModuleEditInfo> moduleEditInfoList) {
        this(feedbackToUser, false, false, null, "",
                moduleEditInfoList,
                Collections.emptyList(),
                Collections.emptyList(),
                archivedPath, false, isImportingWholeArchive, isOverwriting, moduleCodesToImport);
    }



    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public DisplayListLevel getLevel() {
        return level;
    }

    public String getCurrentContext() {
        return this.currentContext;
    }

    /** Returns an unmodifiable view of the list of module edit information. */
    public List<ModuleEditInfo> getModuleEditInfoList() {
        return Collections.unmodifiableList(moduleEditInfoList);
    }

    /** Returns an unmodifiable view of the list of lecture edit information. */
    public List<LectureEditInfo> getLectureEditInfoList() {
        return Collections.unmodifiableList(lectureEditInfoList);
    }

    /** Returns an unmodifiable view of the list of video edit information. */
    public List<VideoEditInfo> getVideoEditInfoList() {
        return Collections.unmodifiableList(videoEditInfoList);
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && currentContext.equals(otherCommandResult.currentContext)
                && moduleEditInfoList.equals(otherCommandResult.moduleEditInfoList)
                && lectureEditInfoList.equals(otherCommandResult.lectureEditInfoList)
                && videoEditInfoList.equals(otherCommandResult.videoEditInfoList)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && isExporting == otherCommandResult.isExporting
                && isImportingAllModules == otherCommandResult.isImportingAllModules
                && isOverwritingExistingModules == otherCommandResult.isOverwritingExistingModules
                && moduleCodesToImport.equals(otherCommandResult.moduleCodesToImport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    /**
     * Stores information about a module that was added, edited, or deleted.
     */
    public static class ModuleEditInfo {
        private final ReadOnlyModule originalModule;
        private final ReadOnlyModule editedModule;

        /**
         * Constructs a {@code ModuleEditInfo}.
         *
         * @param originalModule The original module. {@code null} if the module was added.
         * @param editedModule The edited module. {@code null} if the module was deletd.
         */
        public ModuleEditInfo(ReadOnlyModule originalModule, ReadOnlyModule editedModule) {
            this.originalModule = originalModule;
            this.editedModule = editedModule;
        }

        public ReadOnlyModule getOriginalModule() {
            return originalModule;
        }

        public ReadOnlyModule getEditedModule() {
            return editedModule;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof ModuleEditInfo)) {
                return false;
            }

            ModuleEditInfo otherModuleEditInfo = (ModuleEditInfo) other;

            return (originalModule == null
                            ? originalModule == otherModuleEditInfo.originalModule
                            : originalModule.equals(otherModuleEditInfo.originalModule))
                    && (editedModule == null
                            ? editedModule == otherModuleEditInfo.editedModule
                            : editedModule.equals(otherModuleEditInfo.editedModule));
        }
    }

    /**
     * Stores information about a lecture that was added, edited, or deleted.
     */
    public static class LectureEditInfo {
        private final ModuleCode moduleCode;
        private final ReadOnlyLecture originalLecture;
        private final ReadOnlyLecture editedLecture;

        /**
         * Constructs a {@code LectureEditInfo}.
         *
         * @param moduleCode The code of the module that the edited lecture belongs to.
         * @param originalLecture The original lecture. {@code null} if the lecture was added.
         * @param editedLecture The edited lecture. {@code null} if the lecture was deleted.
         */
        public LectureEditInfo(ModuleCode moduleCode, ReadOnlyLecture originalLecture, ReadOnlyLecture editedLecture) {
            this.moduleCode = requireNonNull(moduleCode);
            this.originalLecture = originalLecture;
            this.editedLecture = editedLecture;
        }

        public ModuleCode getModuleCode() {
            return moduleCode;
        }
        public ReadOnlyLecture getOriginaLecture() {
            return originalLecture;
        }

        public ReadOnlyLecture getEditedLecture() {
            return editedLecture;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof LectureEditInfo)) {
                return false;
            }

            LectureEditInfo otherLectureEditInfo = (LectureEditInfo) other;

            return moduleCode.equals(otherLectureEditInfo.moduleCode)
                    && (originalLecture == null
                            ? originalLecture == otherLectureEditInfo.originalLecture
                            : originalLecture.equals(otherLectureEditInfo.originalLecture))
                    && (editedLecture == null
                            ? editedLecture == otherLectureEditInfo.editedLecture
                            : editedLecture.equals(otherLectureEditInfo.editedLecture));
        }
    }

    /**
     * Stores information about a video that was added, edited, or deleted.
     */
    public static class VideoEditInfo {
        private final ModuleCode moduleCode;
        private final LectureName lectureName;
        private final Video originalVideo;
        private final Video editedVideo;

        /**
         * Constructs a {@code VideoEditInfo}.
         *
         * @param moduleCode The code of the module that the lecture with name {@code lectureName} belongs to.
         * @param lectureName The name of the lecture that the edited video belongs to.
         * @param originalVideo The original video. {@code null} if the video was added.
         * @param editedVideo The edited video. {@code null} if the video was deleted.
         */
        public VideoEditInfo(ModuleCode moduleCode, LectureName lectureName, Video originalVideo, Video editedVideo) {
            this.moduleCode = requireNonNull(moduleCode);
            this.lectureName = requireNonNull(lectureName);
            this.originalVideo = originalVideo;
            this.editedVideo = editedVideo;
        }

        public ModuleCode getModuleCode() {
            return moduleCode;
        }

        public LectureName getLectureName() {
            return lectureName;
        }

        public Video getOriginalVideo() {
            return originalVideo;
        }

        public Video getEditedVideo() {
            return editedVideo;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof VideoEditInfo)) {
                return false;
            }

            VideoEditInfo otherVideoEditInfo = (VideoEditInfo) other;

            return moduleCode.equals(otherVideoEditInfo.moduleCode)
                    && lectureName.equals(otherVideoEditInfo.lectureName)
                    && (originalVideo == null
                            ? originalVideo == otherVideoEditInfo.originalVideo
                            : originalVideo.equals(otherVideoEditInfo.originalVideo))
                    && (editedVideo == null
                            ? editedVideo == otherVideoEditInfo.editedVideo
                            : editedVideo.equals(otherVideoEditInfo.editedVideo));
        }
    }

    /**
     * Information on whether the command is to export tracker to archive
     *
     * @return whether the command wants to export files
     */
    public boolean isExporting() {
        return isExporting;
    }

    /**
     * Information on whether the command is to importing all modules from archive to current tracker
     *
     * @return whether the command wants to import files
     */

    public boolean isImportingWholeArchive() {
        return isImportingAllModules;
    }

    /**
     * Information on whether the command to importing modules/ export modules will overwrite existing data
     *
     * @return whether the command will overwrite existing data
     */

    public boolean isOverwriting() {
        return isOverwritingExistingModules;
    }

    /**
     * Return the path to the archive file
     *
     * @return an Optional containing the archive file
     */

    public Optional<Path> getPath() {
        return path;
    }

    /**
     * Return the set of specified modules to import from the archive file
     *
     * @return a set of module codes of specified modules to import from the archive file
     */

    public Set<ModuleCode> getModuleCodesToImport() {
        return moduleCodesToImport;
    }
}
