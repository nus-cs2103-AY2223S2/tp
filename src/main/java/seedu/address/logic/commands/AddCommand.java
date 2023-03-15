package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Adds a person to the address book.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE_MODULE = COMMAND_WORD + ": (1) Adds a module to the tracker. "
            + "Parameters: "
            + "{module_code} "
            + "[" + PREFIX_NAME + " {name}] "
            + "Example: " + COMMAND_WORD + " CS2040S "
            + PREFIX_NAME + " Data Structures and Algorithms | "
            + "(2) Adds a lecture to a module. "
            + "Parameters: "
            + "{lecture_name} "
            + "[" + PREFIX_MODULE + " {module_code}] "
            + "Example: " + COMMAND_WORD + " Lecture 01 "
            + PREFIX_MODULE + " CS2040S | "
            + "(3) Adds a video to a lecture. "
            + "Parameters: "
            + "{video_name} "
            + "[" + PREFIX_MODULE + " {module_code}] "
            + "[" + PREFIX_LECTURE + " {lecture_name}] "
            + "Example: " + COMMAND_WORD + " Video 01 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Lecture 01";
}
