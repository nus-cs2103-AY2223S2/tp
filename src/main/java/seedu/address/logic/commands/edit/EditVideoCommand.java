package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_VIDEO_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
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

    public static final String MESSAGE_SUCCESS = "Edited video of lecture %s of module %s: %s";
    public static final String MESSAGE_DUPLICATE_VIDEO = "This video already exists in lecture %s of module %s.";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final VideoName videoName;
    private final EditVideoDescriptor editVideoDescriptor;

    /**
     * Creates an {@code EditVideoDescriptor} to edit the details of a video whose name is {@code videoName} in the
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

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        if (!model.hasLecture(moduleCode, lectureName)) {
            throw new CommandException(String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        if (!model.hasVideo(moduleCode, lectureName, videoName)) {
            throw new CommandException(String.format(MESSAGE_VIDEO_DOES_NOT_EXIST, videoName, lectureName, moduleCode));
        }

        ReadOnlyModule module = model.getModule(moduleCode);
        ReadOnlyLecture lecture = module.getLecture(lectureName);
        Video videoToEdit = lecture.getVideo(videoName);
        Video editedVideo = createEditedVideo(videoToEdit);

        if (!videoToEdit.isSameVideo(editedVideo) && lecture.hasVideo(editedVideo)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_VIDEO, lectureName, moduleCode));
        }

        model.setVideo(lecture, videoToEdit, editedVideo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, lectureName, moduleCode, editedVideo),
                new VideoEditInfo(moduleCode, lectureName, videoToEdit, editedVideo));
    }

    private Video createEditedVideo(Video videoToEdit) {
        requireNonNull(videoToEdit);

        VideoName updatedName = editVideoDescriptor.getName().orElse(videoToEdit.getName());
        boolean hasWatchedUpdated = editVideoDescriptor.hasWatched().orElse(videoToEdit.hasWatched());
        VideoTimestamp updatedTimestamp = editVideoDescriptor.getTimestamp().orElse(videoToEdit.getTimestamp());
        Set<Tag> updatedTags = editVideoDescriptor.getTags().orElse(videoToEdit.getTags());

        return new Video(updatedName, hasWatchedUpdated, updatedTimestamp, updatedTags);
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

    /**
     * Stores the details to edit the video with.<p>
     * Each non-empty field value will replace the corresponding field value of the video.
     */
    public static class EditVideoDescriptor {
        private VideoName name;
        private Boolean hasWatched;
        private VideoTimestamp timestamp;
        private Set<Tag> tags;

        public EditVideoDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditVideoDescriptor} to copy.
         */
        public EditVideoDescriptor(EditVideoDescriptor toCopy) {
            setName(toCopy.name);
            setWatched(toCopy.hasWatched);
            setTimestamp(toCopy.timestamp);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return True if at least one field is edited. Otherwise, false.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, hasWatched, timestamp, tags);
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

        /**
         * If {@code tags} is non-null, returns an {@code Optional} containing an immutable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.<p>
         *
         * Else, returns an {@code Optional#empty()}.
         *
         * @return An {@code Optional} containing an immutable tag set if {@code tags} is non-null. Otherwise,
         *         {@code Optional#empty()}.
         */
        public Optional<Set<Tag>> getTags() {
            return tags == null ? Optional.empty() : Optional.of(Collections.unmodifiableSet(tags));
        }

        /**
         * Replace the elements in this object's {@code tags} with the elements in {@code newTags}.
         *
         * @param newTags The new tags.
         */
        public void setTags(Set<Tag> newTags) {
            this.tags = newTags == null ? null : new HashSet<>(newTags);
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

            return getName().equals(descriptor.getName())
                    && hasWatched().equals(descriptor.hasWatched())
                    && getTimestamp().equals(descriptor.getTimestamp())
                    && getTags().equals(descriptor.getTags());
        }
    }
}
