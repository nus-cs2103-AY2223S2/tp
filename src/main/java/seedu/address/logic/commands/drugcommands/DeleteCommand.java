package seedu.address.logic.commands.drugcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a drug from the drug inventory
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
