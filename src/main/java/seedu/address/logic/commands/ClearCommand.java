package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.commandresult.ClearCommandResult;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;

/**
 * Asks for user confirmation before clearing the master deck.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Please confirm before continue!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new ClearCommandResult(MESSAGE_SUCCESS);
    }
}
