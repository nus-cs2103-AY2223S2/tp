package seedu.address.logic.commands.edit;

import static seedu.address.commons.core.Messages.MESSAGE_CONTEXT_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNWATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WATCH;

import seedu.address.logic.commands.Command;

/**
 * Edits the details of a module, lecture, or video in the tracker.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ":\n"
            + "(1) Edits the details of a module in the tracker.\n"
            + "Parameters: "
            + "{module_code} "
            + "[" + PREFIX_CODE + " {updated_code}] "
            + "[" + PREFIX_NAME + " {updated_name}] "
            + "[" + PREFIX_TAG + " {tag_1}[, {tag_2}[, ...]]]\n"
            + "Example: " + COMMAND_WORD + " CS2040S "
            + PREFIX_CODE + " CS2040 "
            + PREFIX_NAME + " Data Structures and Algorithms "
            + PREFIX_TAG + " Heavy, Math, Analysis\n\n"
            + "(2) Edits the details of a lecture in a module.\n"
            + "Parameters: "
            + "{lecture_name} "
            + PREFIX_MODULE + " {module_code} "
            + "[" + PREFIX_NAME + " {updated_name}] "
            + "[" + PREFIX_TAG + " {tag_1}[, {tag_2}[, ...]]]\n"
            + "Example: " + COMMAND_WORD + " Week 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_NAME + " Week 01 Introduction "
            + PREFIX_TAG + " Intro, Important\n\n"
            + "(3) Edits the details of a video in a lecture.\n"
            + "Parameters: "
            + "{video_name} "
            + PREFIX_MODULE + " {module_code} "
            + PREFIX_LECTURE + " {lecture_name} "
            + "[" + PREFIX_NAME + " {updated_name}] "
            + "[" + PREFIX_TIMESTAMP + " {updated_timestamp}] "
            + "[" + PREFIX_WATCH + "] "
            + "[" + PREFIX_UNWATCH + "] "
            + "[" + PREFIX_TAG + " {tag_1}[, {tag_2}[, ...]]]\n"
            + "Example: " + COMMAND_WORD + " Video 1 "
            + PREFIX_MODULE + " CS2040S "
            + PREFIX_LECTURE + " Week 1 "
            + PREFIX_NAME + " Video 01 Grade Breakdown "
            + PREFIX_WATCH + " "
            + PREFIX_TAG + " Intro, Short\n\n"
            + MESSAGE_CONTEXT_USAGE;

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

}
