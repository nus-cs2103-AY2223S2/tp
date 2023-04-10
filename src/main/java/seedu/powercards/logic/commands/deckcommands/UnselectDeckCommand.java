package seedu.powercards.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;

/**
 * Selects a deck to operate on.
 */
public class UnselectDeckCommand extends Command {

    public static final String COMMAND_WORD = "unselectDeck";

    public static final String MESSAGE_SUCCESS = "The deck has been unselected.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.unselectDeck();
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false,
                false, true, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnselectDeckCommand); // instanceof handles nulls
    }
}
