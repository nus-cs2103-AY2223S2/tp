package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * Deletes a video identified using its name, within a lecture, within a module
 */
public class DeleteVideoCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            "(3) Deletes one or more videos from a lecture.\n"
            + "Parameters: {video_name_1}[, {video_name_2}[, ...]] " + PREFIX_MODULE + " {module_code} "
            + PREFIX_LECTURE + " {lecture_name}\n"
            + "Example: " + COMMAND_WORD + " Video 01 " + PREFIX_MODULE + " CS2102 " + PREFIX_LECTURE + " Lecture 01";

    public static final String MESSAGE_DELETE_VIDEO_SUCCESS = "Deleted Video: %1$s from Lecture %2$s in Module %3$s";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final VideoName targetVideoName;

    /**
     * Creates a Delete Video Command executable that deletes a video with {@code targetVideoName}
     * from lecture with {@code lectureName} in module of {@code moduleCode}
     *
     * @param targetVideoName Name of Video to delete
     * @param moduleCode Module Code of module that contains lecture that video is within
     * @param lectureName Name of Lecture that video is within
     */
    public DeleteVideoCommand(ModuleCode moduleCode, LectureName lectureName, VideoName targetVideoName) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
        this.targetVideoName = targetVideoName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: could try to encapsulate this to reuse
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        if (!model.hasLecture(moduleCode, lectureName)) {
            throw new CommandException(String.format(Messages.MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        ReadOnlyLecture lecture = model.getLecture(moduleCode, lectureName);

        if (!model.hasVideo(moduleCode, lectureName, targetVideoName)) {
            throw new CommandException(String.format(Messages.MESSAGE_VIDEO_DOES_NOT_EXIST,
                                                        targetVideoName,
                                                        lectureName,
                                                        moduleCode));
        }

        Video targetVideo = model.getVideo(moduleCode, lectureName, targetVideoName);

        // TODO: repetition ends here

        model.deleteVideo(lecture, targetVideo);

        return new CommandResult(String.format(MESSAGE_DELETE_VIDEO_SUCCESS,
                        targetVideoName,
                        lectureName,
                        moduleCode),
                new VideoEditInfo(moduleCode, lectureName, targetVideo, null));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteVideoCommand
                && moduleCode.equals(((DeleteVideoCommand) other).moduleCode)
                && lectureName.equals(((DeleteVideoCommand) other).lectureName)
                && targetVideoName.equals(((DeleteVideoCommand) other).targetVideoName));
    }
}
