package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Undoable;


/**
 * Redoes the undo command from ModCheck
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Successfully redone command:\n%1$s";
    public static final String MESSAGE_NO_REDOABLE_COMMAND = "No command to redo!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model instanceof Undoable) {
            Undoable undoableModel = (Undoable) model;
            if (!undoableModel.hasRedoableCommand()) {
                throw new CommandException(MESSAGE_NO_REDOABLE_COMMAND);
            }
            String returnMessage = undoableModel.executeRedo();
            return new CommandResult(String.format(MESSAGE_SUCCESS, returnMessage));
        } else {
            throw new IllegalArgumentException("Model passed does not support undo!");
        }
    }
}

