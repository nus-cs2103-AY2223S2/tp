package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.Pair;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Redo the previously undone command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!\n%s";
    public static final String MESSAGE_COMMAND_FAILURE =
            "Error occurred while redoing command:\n%s\nUndo and redo history cleared!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("This method is invalid");
    }

    @Override
    public CommandResult execute(Model model, StackUndoRedo undoRedoStack) throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        if (!undoRedoStack.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        RedoableCommand command = undoRedoStack.popRedo();
        try {
            Pair<CommandResult, RedoableCommand> result = command.executeUndoableCommand(model);
            undoRedoStack.pushUndo(result.second());
            return new CommandResult(String.format(MESSAGE_SUCCESS, result.first().getFeedbackToUser()));
        } catch (CommandException e) {
            undoRedoStack.clear();
            throw new CommandException(String.format(MESSAGE_COMMAND_FAILURE, e.getMessage()));
        }
    }
}
