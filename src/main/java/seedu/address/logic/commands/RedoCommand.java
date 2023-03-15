package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Restores a previously undone {@code SOCket} state.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n" + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Restored the previously undone change to SOCket.";
    public static final String MESSAGE_CANNOT_REDO = "No undone changes to restore.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoSocket()) {
            throw new CommandException(MESSAGE_CANNOT_REDO);
        }
        model.redoSocket();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
