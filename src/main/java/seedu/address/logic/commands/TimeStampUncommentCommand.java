package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.TimeStampComment;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Delete the time stamped comment of a video.
 */

public class TimeStampUncommentCommand extends Command {
    public static final String COMMAND_WORD = "uncomment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete comment a specified video at a specified " +
            //TODO: CHANGE LATER
            "time" + "\n"
            + "\n"
            + "*** Command Format *** " + "\n"
            + "Untag Module: " + COMMAND_WORD + " {module_code} " + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]" + "\n"
            + "Untag Lecture: " + COMMAND_WORD + " {lecture_name} " + PREFIX_MODULE + " {module_code} "
            + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]" + "\n"
            + "Untag Video: " + COMMAND_WORD + " {video_name} " + PREFIX_LECTURE
            + " {lecture_name} " + PREFIX_MODULE + " {module_code} "
            + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]" + "\n"
            + "\n"
            + "*** Example *** " + "\n"
            + "Untag Module: " + COMMAND_WORD + " EG2310 " + PREFIX_TAG + " fun, hard" + "\n"
            + "Untag Lecture: " + COMMAND_WORD + " Lecture_1 " + PREFIX_MODULE + " EG2310 "
            + PREFIX_TAG + " fun, hard" + "\n"
            + "Untag Video: " + COMMAND_WORD + " Video_1 " + PREFIX_LECTURE + " Lecture_1 " + PREFIX_MODULE + " EG2310 "
            + PREFIX_TAG + " fun, hard";

    public static final String MESSAGE_SUCCESS = "Delete comment for %1$s at %2$s";
    private final Set<TimeStampComment> deletingComments;
    private final VideoTimestamp timeStamp;
    private final VideoName videoName;
    private final LectureName lectureName;
    private final ModuleCode moduleCode;

    /**
     * Creates an TimeStampUncommentCommand to untag the specified {@code Module}
     */


    public TimeStampUncommentCommand(Set<TimeStampComment> deletingComments, ModuleCode moduleCode,
                                     LectureName lectureName,
                                   VideoName videoName, VideoTimestamp timeStamp) {
        requireAllNonNull(deletingComments, moduleCode, lectureName, videoName, timeStamp);

        this.deletingComments = deletingComments;
        this.videoName = videoName;
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.timeStamp = timeStamp;
    }

    /**
     * Creates an TimeStampUncommentCommand to delete the specified time stamped comment
     */


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.deletingComments.isEmpty()) {
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
        Video uncommentingVideo = lecture.getVideo(this.videoName);
        VideoTimestamp timestamp = uncommentingVideo.getTimestamp();

        Set<TimeStampComment> currentComments = timestamp.getComments();

        List<String> listOfUnfoundComments = deletingComments.stream()
                .filter(comment -> !currentComments.contains(comment))
                .map(comment -> comment.getComment()).collect(Collectors.toList());

        if (listOfUnfoundComments.size() > 0) {
            throw new CommandException(String.format(Messages.MESSAGE_VIDEO_TAG_DOES_NOT_EXIST,
                    String.join(", ", listOfUnfoundComments)));
        }

        Set<TimeStampComment> newComments = new HashSet<>();
        newComments.addAll(currentComments);
        newComments = newComments.stream().filter(comment -> !deletingComments.contains(comment))
                .collect(Collectors.toSet());

        String time = timestamp.hours + ":" + timestamp.minutes + ":" + timestamp.seconds;

        VideoTimestamp newTimeStamp = new VideoTimestamp(time, newComments);

        Video uncommentedVideo = new Video(uncommentingVideo.getName(), uncommentingVideo.hasWatched(),
                newTimeStamp, uncommentingVideo.getTags());

        model.setVideo(lecture, uncommentingVideo, uncommentedVideo);

        return new CommandResult(String.format(MESSAGE_SUCCESS, videoName, time),
                new CommandResult.VideoEditInfo(moduleCode, lectureName, uncommentingVideo, uncommentedVideo));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeStampUncommentCommand)) {
            return false;
        }

        TimeStampUncommentCommand otherCommand = (TimeStampUncommentCommand) other;

        return deletingComments.equals(otherCommand.deletingComments)
                && videoName.equals(otherCommand.videoName)
                && lectureName.equals(otherCommand.lectureName)
                && moduleCode.equals(otherCommand.moduleCode)
                && timeStamp.equals(otherCommand.timeStamp);
    }
}
