package seedu.address.logic.commands.mark;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MultipleEventsParser;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

/**
 * Marks multiple videos identified using its name, within a lecture, within a module, as unwatched
 */
public class MarkMultipleAsUnwatchedCommand extends MarkCommand {

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final ArrayList<VideoName> videoNames;

    /**
     * Creates a Mark Multiple As Unwatched Command that marks multiple videos with {@videoNames}
     * from lecture with {@code lectureName} in module of {@code moduleCode} as unwatched
     *
     * @param videonames
     * @param moduleCode
     * @param lectureName
     */
    public MarkMultipleAsUnwatchedCommand(VideoName[] videoNames, ModuleCode moduleCode, LectureName lectureName) {
        requireNonNull(videoNames);
        requireNonNull(moduleCode);
        requireNonNull(lectureName);

        this.moduleCode = moduleCode;
        this.lectureName = lectureName;

        ArrayList<VideoName> vids = new ArrayList<>();
        for (VideoName each: videoNames) {
            vids.add(each);
        }

        this.videoNames = vids;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        if (!model.hasLecture(moduleCode, lectureName)) {
            throw new CommandException(String.format(Messages.MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
        }

        ArrayList<VideoName> invalidVideoNames = new ArrayList<>();
        for (VideoName each: this.videoNames) {
            if (!model.hasVideo(moduleCode, lectureName, each)) {
                invalidVideoNames.add(each);
            }
        }

        if (invalidVideoNames.size() != 0) {
            throw new CommandException(String.format(Messages.MESSAGE_VIDEOS_DO_NOT_EXIST,
                    MultipleEventsParser.convertArrayListToString(invalidVideoNames),
                    this.moduleCode, this.lectureName));
        }

        int successSize = this.videoNames.size() - invalidVideoNames.size();
        return new CommandResult(String.format(MESSAGE_MARK_VIDEO_SUCCESS,
                    MultipleEventsParser.convertArrayListToString(this.videoNames),
                    MarkAsUnwatchedCommand.COMMAND_WORD,
                    successSize + " ",
                    successSize == 1
                        ? ""
                        : "s"));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MarkMultipleAsUnwatchedCommand)) {
            return false;
        }

        MarkMultipleAsUnwatchedCommand markCommand = (MarkMultipleAsUnwatchedCommand) other;
        return this.videoNames.equals(markCommand.videoNames)
                && this.lectureName.equals(markCommand.lectureName)
                && this.moduleCode.equals(markCommand.moduleCode);
    }

}
