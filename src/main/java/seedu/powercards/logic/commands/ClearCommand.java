package seedu.powercards.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.commandresult.ClearCommandResult;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;

/**
 * Asks for user confirmation before clearing the master deck.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Please confirm before continuing!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new ClearCommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearCommand); // instanceof handles nulls
    }
}
