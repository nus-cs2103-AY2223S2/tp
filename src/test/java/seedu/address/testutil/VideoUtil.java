package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.add.AddVideoCommand;
import seedu.address.logic.commands.edit.EditVideoCommand;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * A utility class for {@code Video}.
 */
public class VideoUtil {

    /**
     * Returns an add command string for adding the {@code video} to the lecture with name {@code lectureName} which
     * belongs to the module with code {@code moduleCode}.
     *
     * @param moduleCode The code of the module containing the lecture which the video will be added to.
     * @param lectureName The name of the lecture the video is to be added to.
     * @param video The video to be added.
     * @return An add command string for adding the {@code video} to the lecture with name {@code lectureName} which
     *         belongs to the module with code {@code moduleCode}.
     */
    public static String getAddCommand(ModuleCode moduleCode, LectureName lectureName, Video video) {
        return AddVideoCommand.COMMAND_WORD + " " + getVideoDetails(moduleCode, lectureName, video);
    }

    /**
     * Returns the part of the command string for the given {@code video}'s details.
     *
     * @param moduleCode The code of the module containing the lecture which the video is to be in.
     * @param lectureName The name of the lecture the video is to be in.
     * @param video The video.
     * @return The part of the command string for the given {@code video}'s details.
     */
    public static String getVideoDetails(ModuleCode moduleCode, LectureName lectureName, Video video) {
        StringBuilder sb = new StringBuilder();
        sb.append(video.getName() + " ");
        sb.append(PREFIX_MODULE + moduleCode.toString() + " ");
        sb.append(PREFIX_LECTURE + lectureName.toString());

        return sb.toString();
    }

    /**
     * Returns an edit command string for editing the video with name {@code videoName} which belongs to the lecture
     * with name {@code lectureName} contained in the moduel with code {@code moduleCode} using details in
     * {@code descriptor}.
     *
     * @param moduleCode The code of the module containing the lecture.
     * @param lectureName The name of the lecture containing the video to be edited.
     * @param videoName The name of  the video to be edited.
     * @param descriptor The details on how to edit the video.
     * @return An edit command string for editing the video with name {@code videoName} which belongs to the lecture
     * with name {@code lectureName} contained in the moduel with code {@code moduleCode} using details in
     * {@code descriptor}.
     */
    public static String getEditCommand(ModuleCode moduleCode, LectureName lectureName, VideoName videoName,
            EditVideoDescriptor descriptor) {

        return EditVideoCommand.COMMAND_WORD + " " + videoName.toString() + " "
                + PREFIX_MODULE + " " + moduleCode.toString() + " "
                + PREFIX_LECTURE + " " + lectureName.toString() + " "
                + getEditLectureDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of the command string for the given {@code descriptor}'s details.
     *
     * @param descriptor The descriptor.
     * @return The part of the command string for the given {@code descriptor}'s details.
     */
    public static String getEditLectureDescriptorDetails(EditVideoDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(" " + name.name).append(" "));
        return sb.toString();
    }

}
