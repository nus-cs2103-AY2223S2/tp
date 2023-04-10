package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes a previously executed command from the user.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes a previously entered command. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Undid previous command";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
