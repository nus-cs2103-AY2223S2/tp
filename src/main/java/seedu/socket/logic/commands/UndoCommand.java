package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;

/**
 * Restores the previous {@code SOCket} state.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n" + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Reverted the previous change to SOCket.";
    public static final String MESSAGE_CANNOT_UNDO = "No changes to revert.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoSocket()) {
            throw new CommandException(MESSAGE_CANNOT_UNDO);
        }
        model.undoSocket();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
