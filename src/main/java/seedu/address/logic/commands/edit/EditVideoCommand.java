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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

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

        ReadOnlyModule module = model.getTracker().getModule(moduleCode);

        if (!model.hasLecture(moduleCode, lectureName)) {
            throw new CommandException(String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        ReadOnlyLecture lecture = module.getLecture(lectureName);

        if (!model.hasVideo(lecture, videoName)) {
            throw new CommandException(String.format(MESSAGE_VIDEO_DOES_NOT_EXIST, videoName, lectureName, moduleCode));
        }

        Video videoToEdit = lecture.getVideo(videoName);
        Video editedVideo = createEditedVideo(videoToEdit);

        if (!videoToEdit.isSameVideo(editedVideo) && model.hasVideo(lecture, editedVideo)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_VIDEO, lectureName, moduleCode));
        }

        model.setVideo(lecture, videoToEdit, editedVideo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, lectureName, moduleCode, editedVideo));
    }

    private Video createEditedVideo(Video videoToEdit) {
        requireNonNull(videoToEdit);

        VideoName updatedName = editVideoDescriptor.getName().orElse(videoToEdit.getName());

        boolean hasWatched = videoToEdit.hasWatched();
        Set<Tag> tags = videoToEdit.getTags();

        return new Video(updatedName, hasWatched, tags);
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

        public EditVideoDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param toCopy The {@code EditVideoDescriptor} to copy.
         */
        public EditVideoDescriptor(EditVideoDescriptor toCopy) {
            setName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return True if at least one field is edited. Otherwise, false.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        public Optional<VideoName> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(VideoName name) {
            this.name = name;
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

            return getName().equals(descriptor.getName());
        }
    }
}
