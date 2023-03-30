package seedu.address.logic.commands.mark;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MultipleEventsParser;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;


/**
 * Marks a video identified using its name, within a lecture, within a module, as watched
 */
public class MarkAsWatchedCommand extends MarkCommand {

    public static final String COMMAND_WORD = "mark";

    private final ModuleCode moduleCode;
    private final LectureName lectureName;
    private final ArrayList<VideoName> targetVideoNames;

    /**
     * Creates a Mark As Watched Command that marks a video with {@code targetVideoName}
     * from lecture with {@code lectureName} in module of {@code moduleCode} as watched.
     *
     * @param videoNames Names of the Videos to mark
     * @param moduleCode Module Code of module that contains lecture that video is within
     * @param lectureName Name of Lecture that video is within
     */
    public MarkAsWatchedCommand(ModuleCode moduleCode, LectureName lectureName, VideoName... videoNames) {

        requireNonNull(moduleCode);
        requireNonNull(lectureName);
        requireNonNull(videoNames);

        ArrayList<VideoName> videoNamesArrayList = new ArrayList<>();
        for (VideoName each: videoNames) {
            videoNamesArrayList.add(each);
        }
        this.targetVideoNames = videoNamesArrayList;
        this.moduleCode = moduleCode;
        this.lectureName = lectureName;
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

        ReadOnlyLecture lecture = model.getLecture(moduleCode, lectureName);

        int inputLength = targetVideoNames.size();
        ArrayList<VideoName> nonExistentVideosNames = new ArrayList<>();
        ArrayList<VideoName> alreadyMarkedVideosNames = new ArrayList<>();
        Video[] originalVideos = new Video[inputLength];
        Video[] newVideos = new Video[inputLength];
        for (int i = 0; i < inputLength; i++) {

            VideoName videoName = targetVideoNames.get(i);
            if (!model.hasVideo(moduleCode, lectureName, videoName)) {
                nonExistentVideosNames.add(targetVideoNames.get(i));
                continue;
            }

            Video targetVideo = model.getVideo(moduleCode, lectureName, videoName);
            originalVideos[i] = targetVideo;

            if (targetVideo.hasWatched()) {
                alreadyMarkedVideosNames.add(videoName);
                continue;
            }

            Video newVideo = new Video(videoName, true, targetVideo.getTimestamp(), targetVideo.getTags());
            newVideos[i] = newVideo;
        }


        if (nonExistentVideosNames.size() != 0) {
            throw new CommandException(String.format((
                    nonExistentVideosNames.size() == 1
                            ? Messages.MESSAGE_VIDEO_DOES_NOT_EXIST
                            : Messages.MESSAGE_VIDEOS_DO_NOT_EXIST
                    ),
                    MultipleEventsParser.convertArrayListToString(nonExistentVideosNames),
                    this.moduleCode,
                    this.lectureName));
        }

        if (alreadyMarkedVideosNames.size() != 0) {
            throw new CommandException(String.format(MESSAGE_VIDEO_MARK_NOT_CHANGED,
                    MultipleEventsParser.convertArrayListToString(alreadyMarkedVideosNames),
                    COMMAND_WORD,
                    "",
                    alreadyMarkedVideosNames.size() == 1 ? "" : "s",
                    lectureName,
                    moduleCode));
        }

        ArrayList<VideoName> videoNamesArrayList = new ArrayList<>();
        VideoEditInfo[] editedVideosInfos = new VideoEditInfo[inputLength];
        for (int i = 0; i < inputLength; i++) {
            videoNamesArrayList.add(targetVideoNames.get(i));

            Video targetVideo = originalVideos[i];
            Video newVideo = newVideos[i];

            model.setVideo(lecture, targetVideo, newVideo);
            editedVideosInfos[i] = new VideoEditInfo(this.moduleCode, this.lectureName, targetVideo, newVideo);
        }

        return new CommandResult(String.format(MESSAGE_MARK_VIDEO_SUCCESS,
                        MultipleEventsParser.convertArrayListToString(videoNamesArrayList),
                        COMMAND_WORD,
                        inputLength + " ",
                        inputLength == 1 ? "" : "s",
                        lectureName,
                        moduleCode),
                editedVideosInfos);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MarkAsWatchedCommand)) {
            return false;
        }

        MarkAsWatchedCommand command = (MarkAsWatchedCommand) other;
        return this.targetVideoNames.equals(command.targetVideoNames)
                && this.moduleCode.equals(command.moduleCode)
                && this.lectureName.equals(command.lectureName);
    }
}
