package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.add.AddLectureCommand;
import seedu.address.logic.commands.edit.EditLectureCommand;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * A utility classs for {@code Lecture}.
 */
public class LectureUtil {

    /**
     * Returns an add command string for adding the {@code lecture} to the module with code {@code moduleCode}.
     *
     * @param moduleCode The code of the module the lecture is to be added to.
     * @param lecture The lecture to be added.
     * @return An add command string for adding the {@code lecture} to the module with code {@code moduleCode}.
     */
    public static String getAddCommand(ModuleCode moduleCode, Lecture lecture) {
        return AddLectureCommand.COMMAND_WORD + " " + getLectureDetails(moduleCode, lecture);
    }

    /**
     * Returns the part of the command string for the given {@code lecture}'s details.
     *
     * @param moduleCode The code of the module the lecture is to be in.
     * @param lecture The lecture.
     * @return The part of the command string for the given {@code lecture}'s details.
     */
    public static String getLectureDetails(ModuleCode moduleCode, Lecture lecture) {
        StringBuilder sb = new StringBuilder();
        sb.append(lecture.getName() + " ");
        sb.append(PREFIX_MODULE + moduleCode.toString());

        return sb.toString();
    }

    /**
     * Returns an edit command string for editing the lecture with name {@code lectureName} which belong to the module
     * with code {@code moduleCode} using details in {@code descriptor}.
     *
     * @param moduleCode The code of the module containing the lecture to be edited.
     * @param lectureName The name of the lecture to be edited.
     * @param descriptor The details on how to edit the lecture.
     * @return An edit command string for editing the lecture with name {@code lectureName} which belong to the module
     * with code {@code moduleCode} using details in {@code descriptor}.
     */
    public static String getEditCommand(ModuleCode moduleCode, LectureName lectureName,
            EditLectureDescriptor descriptor) {

        return EditLectureCommand.COMMAND_WORD + " " + lectureName.toString() + " "
                + PREFIX_MODULE + " " + moduleCode.toString() + " "
                + getEditLectureDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of the command string for the given {@code descriptor}'s details.
     *
     * @param descriptor The descriptor.
     * @return The part of the command string for the given {@code descriptor}'s details.
     */
    public static String getEditLectureDescriptorDetails(EditLectureDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(" " + name.name).append(" "));
        return sb.toString();
    }

}
