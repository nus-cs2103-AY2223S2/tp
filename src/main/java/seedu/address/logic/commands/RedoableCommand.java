package seedu.address.logic.commands;

import seedu.address.commons.util.Pair;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * This class enables command to be able to undo / redo
 */
public abstract class RedoableCommand extends Command {

    /**
     * Executes the undoable command on the model
     *
     * @param model Model to execute the command on
     * @return Pair of CommandResult and a new RedoableCommand to be pushed onto the undo stack.
     *         This command should be the inverse of the current command.
     * @throws CommandException If an error occurs during command execution.
     */
    protected abstract Pair<CommandResult, RedoableCommand> executeUndoableCommand(Model model) throws CommandException;

    @Override
    public final CommandResult execute(Model model, StackUndoRedo undoRedoStack) throws CommandException {
        Pair<CommandResult, RedoableCommand> result = executeUndoableCommand(model);
        undoRedoStack.pushUndo(result.second());
        return result.first();
    }

}
