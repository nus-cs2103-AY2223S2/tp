package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Undoable;


/**
 * Undoes the last modification command from ModCheck
 * A modification command is any command that successfully changed data in ModCheck.
 * For example: Add, Edit, Clear, etc.
 * A command that would have changed data in ModCheck, but encountered an error, (eg: Adding duplicate persons), will
 * NOT be undone
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Successfully undone command:\n%1$s";
    public static final String MESSAGE_NO_UNDOABLE_COMMAND = "No command to undo!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model instanceof Undoable) {
            Undoable undoableModel = (Undoable) model;
            if (!undoableModel.hasUndoableCommand()) {
                throw new CommandException(MESSAGE_NO_UNDOABLE_COMMAND);
            }
            String returnMessage = undoableModel.executeUndo();
            return new CommandResult(String.format(MESSAGE_SUCCESS, returnMessage));
        } else {
            throw new IllegalArgumentException("Model passed does not support undo!");
        }
    }
}

