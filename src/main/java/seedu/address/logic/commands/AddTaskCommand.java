package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the task of an existing person in the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the task of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing task will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ADD_TASK + "[TASK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ADD_TASK + "Time to swim.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "AddTask command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}