package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Edits the details of a module, lecture, or video in the tracker.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": (1) Edits the details of a module in the tracker. "
            + "Parameters: "
            + "{module_code} "
            + "[" + PREFIX_CODE + " {new_code}] "
            + "[" + PREFIX_NAME + " {new_name}] "
            + "Example: " + COMMAND_WORD + " CS2040S "
            + PREFIX_CODE + " CS2040 "
            + PREFIX_NAME + " Data Structures & Algorithms | "
            + "(2) Edits the details of a lecture in a module. "
            + "Parameters: "
            + "{lecture_name} "
            + "[" + PREFIX_MODULE + " {module_code}] "
            + "[" + PREFIX_NAME + " {new_name}] "
            + "Example: " + COMMAND_WORD + " Lecture 01 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_NAME + " Lecture 01 Introduction | "
            + "(3) Edits the details of a video in a lecture. "
            + "Parameters: "
            + "{video_name} "
            + "[" + PREFIX_MODULE + " {module_code}] "
            + "[" + PREFIX_LECTURE + " {lecture_name}] "
            + "[" + PREFIX_NAME + " {new_name}] "
            + "Example: " + COMMAND_WORD + " Video 01 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Lecture 01 "
            + PREFIX_NAME + " Video 01 Grade Breakdown";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

}
