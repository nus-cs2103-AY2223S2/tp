package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.add.AddLectureCommand;
import seedu.address.model.lecture.Lecture;
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

}
