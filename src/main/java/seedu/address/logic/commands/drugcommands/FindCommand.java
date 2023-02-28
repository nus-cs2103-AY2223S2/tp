package seedu.address.logic.commands.drugcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.Model;

/**
 * Finds a drug from the drug inventory
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        return null;
    }
}
