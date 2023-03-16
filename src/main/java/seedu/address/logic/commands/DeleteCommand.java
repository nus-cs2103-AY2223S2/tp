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
            + "(2) Deletes the lecture of the module identified by the lecture name and module code.\n"
            + "Parameters: lecture name, module code\n"
            + "Example: " + COMMAND_WORD + " Lecture 01 " + PREFIX_MODULE + " CS2103/T\n"
            + "(3) Deletes the video of the lecture of the module identified.\n"
            + "Parameters: video name, lecture name, module code\n"
            + "Example: " + COMMAND_WORD + " Video 01 " + PREFIX_MODULE + " CS2102 " + PREFIX_LECTURE + " Lecture 01";


    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

}
