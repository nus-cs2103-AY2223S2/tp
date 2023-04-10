package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MultipleEventsParser;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

/**
 * Deletes multiple videos identified using their respective video names
 * multiple videos specified are to exist within the same module lecture
 * If one or more o
 */
public class DeleteMultipleVideosCommand extends DeleteCommand {

    public static final String MESSAGE_SUCCESS = "%1$s Videos deleted from Module %2$s > Lecture %3$s: \n%4$s";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final ArrayList<VideoName> targetVideoNames;

    /**
     * Creates an executable command that deletes multiple videos of {@code videoNames}
     * @param moduleCode identifying module code of module that the lecture that all videos to be deleted are within
     * @param lectureName identifying lecture name of lecture that all videos to be deleted are within
     * @param videoNames any number of video names to identify videos to delte in a select lecture of a select module
     */
    public DeleteMultipleVideosCommand(ModuleCode moduleCode,
            LectureName lectureName, VideoName ... videoNames) {
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;

        ArrayList<VideoName> videoNamesArr = new ArrayList<>();
        for (VideoName each: videoNames) {
            videoNamesArr.add(each);
        }

        this.targetVideoNames = videoNamesArr;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!(model.hasModule(this.moduleCode))) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, this.moduleCode));
        }

        if (!(model.hasLecture(this.moduleCode, this.lectureName))) {
            throw new CommandException(String.format(Messages.MESSAGE_LECTURE_DOES_NOT_EXIST,
                    this.lectureName, this.moduleCode));
        }
        ArrayList<VideoName> invalidVideoNames = new ArrayList<>();
        for (VideoName each: this.targetVideoNames) {
            if (!(model.hasVideo(this.moduleCode, this.lectureName, each))) {
                invalidVideoNames.add(each);
            }
        }

        if (invalidVideoNames.size() == 0) {
            VideoEditInfo[] editedVideos = new VideoEditInfo[this.targetVideoNames.size()];

            for (int i = 0; i < this.targetVideoNames.size(); i++) {
                VideoName videoName = this.targetVideoNames.get(i);
                DeleteVideoCommand dvc = new DeleteVideoCommand(this.moduleCode, this.lectureName, videoName);
                CommandResult r = dvc.execute(model);

                editedVideos[i] = r.getVideoEditInfoList().get(0);
            }

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    targetVideoNames.size(),
                    this.moduleCode,
                    this.lectureName,
                    MultipleEventsParser.convertArrayListToString(targetVideoNames)),
                    editedVideos);
        } else {
            throw new CommandException(String.format((
                        invalidVideoNames.size() == 1
                        ? Messages.MESSAGE_VIDEO_DOES_NOT_EXIST
                        : Messages.MESSAGE_VIDEOS_DO_NOT_EXIST
                    ),
                    MultipleEventsParser.convertArrayListToString(invalidVideoNames),
                    this.moduleCode,
                    this.lectureName));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteMultipleVideosCommand) {
            DeleteMultipleVideosCommand dmvc = (DeleteMultipleVideosCommand) other;
            return this.moduleCode.equals(dmvc.moduleCode)
                    && this.lectureName.equals(dmvc.lectureName)
                    && this.targetVideoNames.equals(dmvc.targetVideoNames);
        } else {
            return false;
        }
    }
}
