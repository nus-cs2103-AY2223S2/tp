package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * Creates a TagCommand to tag the specified {@code Module}
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

    /**
     * Creates a TagCommand to tag the specified {@code Lecture}
     */
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

    /**
     * Creates a TagCommand to tag the specified {@code Video}
     */
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
        } else if (this.isTaggingVid) {
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

        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(this.tags);
        newTags.addAll(currentTags);

        List<Lecture> currentLectureList = (List<Lecture>) taggingModule.getLectureList();

        Module taggedModule = new Module(taggingModule.getCode(),
                taggingModule.getName(), newTags, currentLectureList);
        model.setModule(taggingModule, taggedModule);
    }

    private void tagLecture(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        if (!model.hasLecture(this.moduleCode, this.lectureName)) {
            throw new CommandException(MESSAGE_LECTURE_NOT_FOUND);
        }

        ReadOnlyModule targetModule = model.getModule(this.moduleCode);
        ReadOnlyLecture taggingLecture = targetModule.getLecture(this.lectureName);

        Set<Tag> currentTags = taggingLecture.getTags();
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(this.tags);
        newTags.addAll(currentTags);

        Lecture taggedLecture = new Lecture(taggingLecture.getName(), newTags, taggingLecture.getVideoList());
        model.setLecture(targetModule, taggingLecture, taggedLecture);
    }

    private void tagVideo(Model model) throws CommandException {
        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        if (!model.hasLecture(this.moduleCode, this.lectureName)) {
            throw new CommandException(MESSAGE_LECTURE_NOT_FOUND);
        }

        ReadOnlyModule targetModule = model.getModule(this.moduleCode);
        ReadOnlyLecture targetLecture = targetModule.getLecture(this.lectureName);

        if (!model.hasVideo(targetLecture, this.videoName)) {
            throw new CommandException(MESSAGE_VIDEO_NOT_FOUND);
        }

        Video taggingVideo = targetLecture.getVideo(this.videoName);

        Set<Tag> currentTags = taggingVideo.getTags();
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(this.tags);
        newTags.addAll(currentTags);

        Video taggedVideo = new Video(taggingVideo.getName(), taggingVideo.hasWatched(), newTags);
        model.setVideo(targetLecture, taggingVideo, taggedVideo);
    }
}
