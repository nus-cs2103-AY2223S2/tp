package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

import java.util.List;
import java.util.Set;

/**
 * Tag a video, a lecture, or a module.
 */

public class UntagCommand extends Command {
    public static final String COMMAND_WORD = "untag";

    //TODO: MODIFY THIS
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Untag a specified video, module, or lecture ";
    //TODO: MODIFY THIS
    public static final String MESSAGE_SUCCESS = "Item untagged";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module doesn't exist in Le Tracker";
    public static final String MESSAGE_LECTURE_NOT_FOUND = "Lecture doesn't exist in this module";
    public static final String MESSAGE_VIDEO_NOT_FOUND = "Video doesn't exist in this lecture";


    private final Tag tag;

    private final VideoName videoName;
    private final LectureName lectureName;
    private final ModuleCode moduleCode;
    private final boolean isUntaggingMod;
    private final boolean isUntaggingLec;
    private final boolean isUntaggingVid;

    /**
     * Creates a TagCommand to tag the specified {@code Video}, {@code Lecture}, or {@code Module}
     */


    public UntagCommand(Tag tag, ModuleCode moduleCode) {
        requireAllNonNull(tag, moduleCode);

        this.tag = tag;
        this.videoName = new VideoName("dummy");
        this.lectureName = new LectureName("dummy");
        this.moduleCode = moduleCode;
        this.isUntaggingMod = true;
        this.isUntaggingLec = false;
        this.isUntaggingVid = false;
    }

    public UntagCommand(Tag tag, ModuleCode moduleCode, LectureName lectureName) {
        requireAllNonNull(tag, moduleCode, lectureName);

        this.tag = tag;
        this.videoName = new VideoName("dummy");
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.isUntaggingMod = false;
        this.isUntaggingLec = true;
        this.isUntaggingVid = false;
    }

    public UntagCommand(Tag tag, ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        requireAllNonNull(tag, moduleCode, lectureName, videoName);

        this.tag = tag;
        this.videoName = videoName;
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.isUntaggingMod = false;
        this.isUntaggingLec = false;
        this.isUntaggingVid = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.isUntaggingMod) {
            untagModule(model);
        } else if (this.isUntaggingLec) {
            untagLecture(model);
        } else if (this.isUntaggingLec){
            untagVideo(model);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void untagModule(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        ReadOnlyModule taggingModule = model.getModule(this.moduleCode);

        Set<Tag> currentTags = taggingModule.getTags();
        currentTags.remove(this.tag);

        List<Lecture> currentLectureList = (List<Lecture>) taggingModule.getLectureList();

        Module taggedModule = new Module(taggingModule.getCode(),
                taggingModule.getName(), currentTags, currentLectureList);
        model.setModule(taggingModule, taggedModule);
    }

    private void untagLecture(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        ReadOnlyModule targetModule = model.getModule(this.moduleCode);

        if (!model.hasLecture(targetModule, this.lectureName)) {
            throw new CommandException(MESSAGE_LECTURE_NOT_FOUND);
        }

        ReadOnlyLecture taggingLecture = targetModule.getLecture(this.lectureName);

        Set<Tag> currentTags = taggingLecture.getTags();
        currentTags.remove(this.tag);

        Lecture taggedLecture = new Lecture(taggingLecture.getName(), currentTags, taggingLecture.getVideoList());
        model.setLecture(targetModule, taggingLecture, taggedLecture);
    }

    private void untagVideo(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        ReadOnlyModule targetModule = model.getModule(this.moduleCode);

        if (!model.hasLecture(targetModule, this.lectureName)) {
            throw new CommandException(MESSAGE_LECTURE_NOT_FOUND);
        }

        ReadOnlyLecture targetLecture = targetModule.getLecture(this.lectureName);

        if (!model.hasVideo(targetLecture, this.videoName)) {
            throw new CommandException(MESSAGE_VIDEO_NOT_FOUND);
        }

        Video taggingVideo = targetLecture.getVideo(this.videoName);

        Set<Tag> currentTags = taggingVideo.getTags();
        currentTags.remove(this.tag);

        Video taggedVideo = new Video(taggingVideo.getName(), taggingVideo.hasWatched(), currentTags);
        model.setVideo(targetLecture, taggingVideo, taggedVideo);
    }
}