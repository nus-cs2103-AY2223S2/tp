package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.TimeStampComment;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Add a comment with timestamp to the video.
 */

public class TimeStampCommentCommand extends Command {
    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Comment a specified video at a specified time" + "\n"
            + "\n"
            + "*** Command Format *** " + "\n"
            + "Tag Module: " + COMMAND_WORD + " {module_code} " + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]" + "\n"
            + "Tag Lecture: " + COMMAND_WORD + " {lecture_name} " + PREFIX_MODULE + " {module_code} "
            + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]" + "\n"
            + "Tag Video: " + COMMAND_WORD + " {video_name} " + PREFIX_LECTURE
            + " {lecture_name} " + PREFIX_MODULE + " {module_code} "
            + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]" + "\n"
            + "\n"
            + "*** Example *** " + "\n"
            + "Tag Module: " + COMMAND_WORD + " EG2310 " + PREFIX_TAG + " fun, hard" + "\n"
            + "Tag Lecture: " + COMMAND_WORD + " Lecture_1 " + PREFIX_MODULE + " EG2310 "
            + PREFIX_TAG + " fun, hard" + "\n"
            + "Tag Video: " + COMMAND_WORD + " Video_1 " + PREFIX_LECTURE + " Lecture_1 " + PREFIX_MODULE + " EG2310 "
            + PREFIX_TAG + " fun, hard";

    public static final String MESSAGE_SUCCESS = "Added comment to %1$s at %2$s";

    private final Set<TimeStampComment> comments;
    private final VideoTimestamp timeStamp;
    private final VideoName videoName;
    private final LectureName lectureName;
    private final ModuleCode moduleCode;


    /**
     * Creates a TagCommand to tag the specified {@code Video}
     */
    public TimeStampCommentCommand(Set<TimeStampComment> comments, ModuleCode moduleCode, LectureName lectureName,
                      VideoName videoName, VideoTimestamp timeStamp) {
        requireAllNonNull(comments, moduleCode, lectureName, videoName, timeStamp);

        this.comments = comments;
        this.videoName = videoName;
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.timeStamp = timeStamp;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.comments.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_TIMESTAMP_COMMENT);
        }
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }
        if (!model.hasLecture(this.moduleCode, this.lectureName)) {
            throw new CommandException(String.format(Messages.MESSAGE_LECTURE_DOES_NOT_EXIST, this.lectureName,
                    moduleCode));
        }
        if (!model.hasVideo(this.moduleCode, this.lectureName, this.videoName)) {
            throw new CommandException(String.format(Messages.MESSAGE_VIDEO_DOES_NOT_EXIST, this.videoName,
                    this.lectureName,
                    this.moduleCode));
        }

        ReadOnlyModule module = model.getModule(this.moduleCode);
        ReadOnlyLecture lecture = module.getLecture(this.lectureName);
        Video commentingVideo = lecture.getVideo(this.videoName);
        VideoTimestamp timestamp = commentingVideo.getTimestamp();

        Set<TimeStampComment> currentComments = timestamp.getComments();
        Set<TimeStampComment> newComments = new HashSet<>();
        newComments.addAll(this.comments);
        newComments.addAll(currentComments);

        String time = timestamp.hours + ":" + timestamp.minutes + ":" + timestamp.seconds;

        VideoTimestamp newTimeStamp = new VideoTimestamp(time, newComments);

        Video commentedVideo = new Video(commentingVideo.getName(), commentingVideo.hasWatched(),
                newTimeStamp, commentingVideo.getTags());

        model.setVideo(lecture, commentingVideo, commentedVideo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, videoName),
                new CommandResult.VideoEditInfo(moduleCode, lectureName,
                        commentingVideo, commentedVideo));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeStampCommentCommand)) {
            return false;
        }

        TimeStampCommentCommand otherCommand = (TimeStampCommentCommand) other;

        return comments.equals(otherCommand.comments)
                && videoName.equals(otherCommand.videoName)
                && lectureName.equals(otherCommand.lectureName)
                && moduleCode.equals(otherCommand.moduleCode)
                && timeStamp.equals(otherCommand.timeStamp);
    }
}
