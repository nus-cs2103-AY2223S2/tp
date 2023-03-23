package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
public class SortStudentCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all address book students.\n "
            + "Parameters: The order you want (either 'reverse' or 'nonreverse' "
            + PREFIX_SORT + "[SORT]\n"
            + "Example: " + COMMAND_WORD + " reverse "
            + PREFIX_SORT + "Likes to swim.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Sort command not implemented yet";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
