package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes a previously executed command from the user.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes a previously undone command. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Redid previous command";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.redoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
