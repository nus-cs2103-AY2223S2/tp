package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
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
    private final Tag tag;

    private final VideoName parsedVideoName;
    private final LectureName lectureName;
    private final ModuleCode moduleCode;
    private final boolean isTaggingMod;
    private final boolean isTaggingLec;
    private final boolean isTaggingVid;

    /**
     * Creates a TagCommand to tag the specified {@code Video}, {@code Lecture}, or {@code Module}
     */


    public TagCommand(Tag tag, ModuleCode moduleCode) {
        requireNonNull(tag);
        requireNonNull(moduleCode);

        this.tag = tag;
        this.parsedVideoName = new VideoName("dummy");
        this.lectureName = new LectureName("dummy");
        this.moduleCode = moduleCode;
        this.isTaggingMod = true;
        this.isTaggingLec = false;
        this.isTaggingVid = false;
    }

    public TagCommand(Tag tag, ModuleCode moduleCode, LectureName lectureName) {
        requireNonNull(tag);
        requireNonNull(moduleCode);
        requireNonNull(lectureName);

        this.tag = tag;
        this.parsedVideoName = new VideoName("dummy");
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.isTaggingMod = false;
        this.isTaggingLec = true;
        this.isTaggingVid = false;
    }

    public TagCommand(Tag tag, ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        requireNonNull(tag);
        requireNonNull(moduleCode);
        requireNonNull(lectureName);
        requireNonNull(videoName);

        this.tag = tag;
        this.parsedVideoName = videoName;
        this.lectureName = lectureName;
        this.moduleCode = moduleCode;
        this.isTaggingMod = false;
        this.isTaggingLec = false;
        this.isTaggingVid = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        if (this.isTaggingMod) {
            tagModule();
        } else if (this.isTaggingLec) {
            tagLecture();
        } else if (this.isTaggingLec){
            tagVideo();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void tagModule() {

    }

    private void tagLecture() {

    }

    private void tagVideo() {

    }
}