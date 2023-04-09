package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;

/**
 * Adds a video to a lecture.
 */
public class AddVideoCommand extends AddCommand {

    /** The message for when a {@code Video} is successfully added. */
    public static final String MESSAGE_SUCCESS = "New video added to lecture %s of module %s: %s";

    /** The error message for when a duplicate {@code Video} is detected. */
    public static final String MESSAGE_DUPLICATE_VIDEO = "This video already exists in lecture %s of module %s.";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final Video toAdd;

    /**
     * Constructs an {@code AddVideoCommand} to add {@code video} to the lecture with name {@code lectureName} which
     * belongs to module with code {@code moduleCode}
     *
     * @param moduleCode The code of the module which the lecture with name {@code lectureName} belongs to.
     * @param lectureName The name of the lecture to add the video to.
     * @param video The video to be added.
     */
    public AddVideoCommand(ModuleCode moduleCode, LectureName lectureName, Video video) {
        requireAllNonNull(moduleCode, lectureName, video);

        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
        toAdd = video;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyModule module = getContainingModule(model);
        ReadOnlyLecture lecture = getContainingLecture(module);

        validateVideoIsNotDuplicate(lecture);

        model.addVideo(lecture, toAdd);

        return createSuccessResult();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddVideoCommand)) {
            return false;
        }

        AddVideoCommand otherCommand = (AddVideoCommand) other;

        return moduleCode.equals(otherCommand.moduleCode)
                && lectureName.equals(otherCommand.lectureName)
                && toAdd.equals(otherCommand.toAdd);
    }

    private ReadOnlyModule getContainingModule(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        return model.getModule(moduleCode);
    }

    private ReadOnlyLecture getContainingLecture(ReadOnlyModule module) throws CommandException {
        if (!module.hasLecture(lectureName)) {
            throw new CommandException(String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        return module.getLecture(lectureName);
    }

    private void validateVideoIsNotDuplicate(ReadOnlyLecture lecture) throws CommandException {
        if (lecture.hasVideo(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_VIDEO, lectureName, moduleCode));
        }
    }

    private CommandResult createSuccessResult() {
        String message = String.format(MESSAGE_SUCCESS, lectureName, moduleCode, toAdd);
        VideoEditInfo editInfo = new VideoEditInfo(moduleCode, lectureName, null, toAdd);

        return new CommandResult(message, editInfo);
    }

}
