package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_VIDEO_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Edits the details of a video in a lecture.
 */
public class EditVideoCommand extends EditCommand {

    /** The message for when a {@code Video} is successfully edited. */
    public static final String MESSAGE_SUCCESS = "Edited video of lecture %s of module %s: %s";

    /** The error message for when a duplicate {@code Video} is detected. */
    public static final String MESSAGE_DUPLICATE_VIDEO = "This video already exists in lecture %s of module %s.";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final VideoName videoName;
    private final EditVideoDescriptor editVideoDescriptor;

    /**
     * Constructs an {@code EditVideoCommand} to edit the details of a video whose name is {@code videoName} in the
     * lecture whose name is {@code lectureName} in the module whose code is {@code moduleCode}.
     *
     * @param moduleCode The code of the module containing the lecture which contains the video.
     * @param lectureName The name of the lecture which contains the video.
     * @param videoName The name of the video to be edited.
     * @param editVideoDescriptor The details to edit the video with.
     */
    public EditVideoCommand(ModuleCode moduleCode, LectureName lectureName, VideoName videoName,
            EditVideoDescriptor editVideoDescriptor) {

        requireAllNonNull(moduleCode, lectureName, videoName, editVideoDescriptor);

        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
        this.videoName = videoName;
        this.editVideoDescriptor = editVideoDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyModule module = getModuleContainingLecture(model);
        ReadOnlyLecture lecture = getLectureContainingVideo(module);
        Video videoToEdit = getVideoToEdit(lecture);
        Video editedVideo = createEditedVideo(videoToEdit);

        validateVideoIsNotDuplicate(lecture, videoToEdit, editedVideo);

        model.setVideo(lecture, videoToEdit, editedVideo);

        return createSuccessResult(videoToEdit, editedVideo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditVideoCommand)) {
            return false;
        }

        EditVideoCommand otherCommand = (EditVideoCommand) other;

        return moduleCode.equals(otherCommand.moduleCode)
                && lectureName.equals(otherCommand.lectureName)
                && videoName.equals(otherCommand.videoName)
                && editVideoDescriptor.equals(otherCommand.editVideoDescriptor);
    }

    private ReadOnlyModule getModuleContainingLecture(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        return model.getModule(moduleCode);
    }

    private ReadOnlyLecture getLectureContainingVideo(ReadOnlyModule module) throws CommandException {
        requireNonNull(module);

        if (!module.hasLecture(lectureName)) {
            throw new CommandException(String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        return module.getLecture(lectureName);
    }

    private Video getVideoToEdit(ReadOnlyLecture lecture) throws CommandException {
        requireNonNull(lecture);

        if (!lecture.hasVideo(videoName)) {
            throw new CommandException(String.format(MESSAGE_VIDEO_DOES_NOT_EXIST, videoName, lectureName, moduleCode));
        }

        return lecture.getVideo(videoName);
    }

    private Video createEditedVideo(Video videoToEdit) {
        requireNonNull(videoToEdit);

        VideoName updatedName = editVideoDescriptor.getName().orElse(videoToEdit.getName());
        boolean hasWatchedUpdated = editVideoDescriptor.hasWatched().orElse(videoToEdit.hasWatched());
        VideoTimestamp updatedTimestamp = editVideoDescriptor.getTimestamp().orElse(videoToEdit.getTimestamp());
        Set<Tag> updatedTags = editVideoDescriptor.getTags().orElse(videoToEdit.getTags());

        return new Video(updatedName, hasWatchedUpdated, updatedTimestamp, updatedTags);
    }

    private void validateVideoIsNotDuplicate(ReadOnlyLecture lecture, Video videoToEdit, Video editedVideo)
            throws CommandException {

        requireAllNonNull(lecture, videoToEdit, editedVideo);

        if (!videoToEdit.isSameVideo(editedVideo)
                && lecture.hasVideo(editedVideo)) {

            throw new CommandException(String.format(MESSAGE_DUPLICATE_VIDEO, lectureName, moduleCode));
        }
    }

    private CommandResult createSuccessResult(Video videoToEdit, Video editedVideo) {
        requireAllNonNull(videoToEdit, editedVideo);

        return new CommandResult(String.format(MESSAGE_SUCCESS, lectureName, moduleCode, editedVideo),
                new VideoEditInfo(moduleCode, lectureName, videoToEdit, editedVideo));
    }

    /**
     * Stores the details to edit the video with.<p>
     * Each non-empty field value will replace the corresponding field value of the video.
     */
    public static class EditVideoDescriptor extends EditEntityDescriptor {
        private VideoName name;
        private Boolean hasWatched;
        private VideoTimestamp timestamp;

        /**
         * Constructs an {@code EditVideoDescriptor}.
         */
        public EditVideoDescriptor() {
            super();
        }

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditVideoDescriptor} to copy.
         */
        public EditVideoDescriptor(EditVideoDescriptor toCopy) {
            super(toCopy);

            requireNonNull(toCopy);

            setName(toCopy.name);
            setWatched(toCopy.hasWatched);
            setTimestamp(toCopy.timestamp);
        }

        public Optional<VideoName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(VideoName name) {
            this.name = name;
        }

        public Optional<Boolean> hasWatched() {
            return Optional.ofNullable(hasWatched);
        }

        public void setWatched(Boolean hasWatched) {
            this.hasWatched = hasWatched;
        }

        public Optional<VideoTimestamp> getTimestamp() {
            return Optional.ofNullable(timestamp);
        }

        public void setTimestamp(VideoTimestamp timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited()
                    || CollectionUtil.isAnyNonNull(name, hasWatched, timestamp);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditVideoDescriptor)) {
                return false;
            }

            EditVideoDescriptor descriptor = (EditVideoDescriptor) other;

            return super.equals(other)
                    && getName().equals(descriptor.getName())
                    && hasWatched().equals(descriptor.hasWatched())
                    && getTimestamp().equals(descriptor.getTimestamp());
        }
    }
}
