package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":\n"
            + DeleteModuleCommand.MESSAGE_USAGE
            + DeleteLectureCommand.MESSAGE_USAGE
            + "(3) Deletes the video of the lecture of the module identified.\n"
            + "Parameters: video name, lecture name, module code\n"
            + "Example: " + COMMAND_WORD + " Video 01 " + PREFIX_MODULE + " CS2102 " + PREFIX_LECTURE + " Lecture 01";
}
