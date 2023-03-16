package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.experimental.model.Model;
import seedu.address.experimental.model.Reroll;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setReroll(new Reroll());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
