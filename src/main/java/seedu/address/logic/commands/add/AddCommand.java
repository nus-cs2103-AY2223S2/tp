package seedu.address.logic.commands.add;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;

import seedu.address.logic.commands.Command;

/**
 * Adds a module, lecture, or video to the tracker.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "(1) Adds a module to the tracker. "
            + "Parameters: "
            + "{module_code} "
            + "[" + PREFIX_NAME + " {name}] "
            + "[" + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]] "
            + "Example: " + COMMAND_WORD + " CS2040S "
            + PREFIX_NAME + " Data Structures and Algorithms "
            + PREFIX_TAG + " Heavy, Math, Analysis\n"
            + "(2) Adds a lecture to a module. "
            + "Parameters: "
            + "{lecture_name} "
            + "[" + PREFIX_MODULE + " {module_code}] "
            + "[" + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]] "
            + "Example: " + COMMAND_WORD + " Week 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_TAG + " Intro, Important\n"
            + "(3) Adds a video to a lecture. "
            + "Parameters: "
            + "{video_name} "
            + "[" + PREFIX_MODULE + " {module_code}] "
            + "[" + PREFIX_LECTURE + " {lecture_name}] "
            + "[" + PREFIX_WATCH + "] "
            + "[" + PREFIX_TAG + " {tag_1}, [{tag_2}, ...]] "
            + "Example: " + COMMAND_WORD + " Video 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Week 1 "
            + PREFIX_WATCH + " "
            + PREFIX_TAG + " Intro, Short";
}
