package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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
