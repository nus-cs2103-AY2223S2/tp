package seedu.address.logic.commands.mark;

import static java.util.Objects.requireNonNull;

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
 * Marks a video identified using its name, within a lecture, within a module, as unwatched
 */
public class MarkAsUnwatchedCommand extends MarkCommand {

    public static final String COMMAND_WORD = "unmark";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final VideoName targetVideoName;

    /**
     * Creates a Mark As Unwatched Command that marks a video with {@code targetVideoname}
     * from lecture with {@code lectureName} in module of {@code moduleCode} as unwatched.
     *
     * @param targetVideoName
     * @param moduleCode
     * @param lectureName
     */
    public MarkAsUnwatchedCommand(VideoName targetVideoName, ModuleCode moduleCode, LectureName lectureName) {
        requireNonNull(targetVideoName);
        requireNonNull(moduleCode);
        requireNonNull(lectureName);

        this.targetVideoName = targetVideoName;
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
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

        // TODO: ends here

        if (!targetVideo.hasWatched()) {
            throw new CommandException(String.format(MESSAGE_VIDEO_MARK_NOT_CHANGED,
                    targetVideoName,
                    COMMAND_WORD,
                    "",
                    "",
                    lectureName,
                    moduleCode));
        }

        Video newVideo = new Video(targetVideoName, false, targetVideo.getTimestamp(), targetVideo.getTags());
        model.setVideo(lecture, targetVideo, newVideo);

        return new CommandResult(String.format(MESSAGE_MARK_VIDEO_SUCCESS,
                        targetVideoName, COMMAND_WORD, "", "", lectureName, moduleCode),
                new VideoEditInfo(moduleCode, lectureName, targetVideo, newVideo));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MarkAsUnwatchedCommand)) {
            return false;
        }

        MarkAsUnwatchedCommand markCommand = (MarkAsUnwatchedCommand) other;
        return this.targetVideoName.equals(markCommand.targetVideoName)
                && this.lectureName.equals(markCommand.lectureName)
                && this.moduleCode.equals(markCommand.moduleCode);
    }
}
