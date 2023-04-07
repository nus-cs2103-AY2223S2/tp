package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.Pair;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undo the previous {@code UndoableCommand}.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE_SUCCESS = "Command undo success!\n%s";
    public static final String MESSAGE_USAGE_FAILURE = "No more commands to undo!";
    public static final String MESSAGE_ERROR =
            "Error occurred while undoing command:\n%s\nUndo and redo history cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("This method is invalid");
    }

    @Override
    public CommandResult execute(Model model, StackUndoRedo undoRedoStack) throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        if (!undoRedoStack.canUndo()) {
            throw new CommandException(MESSAGE_USAGE_FAILURE);
        }
        RedoableCommand command = undoRedoStack.popUndo();
        try {
            Pair<CommandResult, RedoableCommand> result = command.executeUndoableCommand(model);
            undoRedoStack.pushRedo(result.second());
            return new CommandResult(String.format(MESSAGE_USAGE_SUCCESS, result.first().getFeedbackToUser()));
        } catch (CommandException e) {
            undoRedoStack.clear();
            throw new CommandException(String.format(MESSAGE_ERROR, e.getMessage()));
        }
    }
}
