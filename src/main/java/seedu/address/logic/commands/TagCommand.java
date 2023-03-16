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

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    //TODO: MODIFY THIS
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tag a specified video, module, or lecture ";
    //TODO: MODIFY THIS
    public static final String MESSAGE_SUCCESS = "Item tagged";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module doesn't exist in Le Tracker";
    public static final String MESSAGE_LECTURE_NOT_FOUND = "Lecture doesn't exist in this module";
    public static final String MESSAGE_VIDEO_NOT_FOUND = "Video doesn't exist in this lecture";


    private final Set<Tag> tags;

    private final VideoName videoName;
    private final LectureName lectureName;
    private final ModuleCode moduleCode;
    private final boolean isTaggingMod;
    private final boolean isTaggingLec;
    private final boolean isTaggingVid;

    /**
     * Creates a TagCommand to tag the specified {@code Video}, {@code Lecture}, or {@code Module}
     */


    public TagCommand(Set<Tag> tags, ModuleCode moduleCode) {
        requireAllNonNull(tags, moduleCode);

        this.tags = tags;
        this.videoName = new VideoName("dummy");
        this.lectureName = new LectureName("dummy");
        this.moduleCode = moduleCode;
        this.isTaggingMod = true;
        this.isTaggingLec = false;
        this.isTaggingVid = false;
    }

    public TagCommand(Set<Tag> tags, ModuleCode moduleCode, LectureName lectureName) {
        requireAllNonNull(tags, moduleCode, lectureName);

        this.tags = tags;
        this.videoName = new VideoName("dummy");
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.isTaggingMod = false;
        this.isTaggingLec = true;
        this.isTaggingVid = false;
    }

    public TagCommand(Set<Tag> tags, ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        requireAllNonNull(tags, moduleCode, lectureName, videoName);

        this.tags = tags;
        this.videoName = videoName;
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.isTaggingMod = false;
        this.isTaggingLec = false;
        this.isTaggingVid = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.isTaggingMod) {
            tagModule(model);
        } else if (this.isTaggingLec) {
            tagLecture(model);
        } else if (this.isTaggingLec){
            tagVideo(model);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void tagModule(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        ReadOnlyModule taggingModule = model.getModule(this.moduleCode);

        Set<Tag> currentTags = taggingModule.getTags();
        currentTags.addAll(this.tags);

        List<Lecture> currentLectureList = (List<Lecture>) taggingModule.getLectureList();

        Module taggedModule = new Module(taggingModule.getCode(),
                taggingModule.getName(), currentTags, currentLectureList);
        model.setModule(taggingModule, taggedModule);
    }

    private void tagLecture(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        ReadOnlyModule targetModule = model.getModule(this.moduleCode);

        if (!targetModule.hasLecture(this.lectureName)) {
            throw new CommandException(MESSAGE_LECTURE_NOT_FOUND);
        }

        ReadOnlyLecture taggingLecture = targetModule.getLecture(this.lectureName);

        Set<Tag> currentTags = taggingLecture.getTags();
        currentTags.addAll(this.tags);

        Lecture taggedLecture = new Lecture(taggingLecture.getName(), currentTags, taggingLecture.getVideoList());
        model.setLecture(targetModule, taggingLecture, taggedLecture);
    }

    private void tagVideo(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        ReadOnlyModule targetModule = model.getModule(this.moduleCode);

        if (!targetModule.hasLecture(this.lectureName)) {
            throw new CommandException(MESSAGE_LECTURE_NOT_FOUND);
        }

        ReadOnlyLecture targetLecture = targetModule.getLecture(this.lectureName);

        if (!targetLecture.hasVideo(this.videoName)) {
            throw new CommandException(MESSAGE_VIDEO_NOT_FOUND);
        }

        Video taggingVideo = targetLecture.getVideo(this.videoName);

        Set<Tag> currentTags = taggingVideo.getTags();
        currentTags.addAll(this.tags);

        Video taggedVideo = new Video(taggingVideo.getName(), taggingVideo.hasWatched(), currentTags);
        model.setVideo(targetLecture, taggingVideo, taggedVideo);
    }
}