package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Undoes the last modification command from ModCheck
 * A modification command is any command that successfully changed data in ModCheck.
 * For example: Add, Edit, Clear, etc.
 * A command that would have changed data in ModCheck, but encountered an error, (eg: Adding duplicate persons), will
 * NOT be undone
 * Only supports ONE undo command - chained undos are not supported
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Successfully undone command %1$s";
    public static final String MESSAGE_NO_UNDOABLE_COMMAND = "No command to undo!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (! model.hasUndoableCommand()) {
            throw new CommandException(MESSAGE_NO_UNDOABLE_COMMAND);
        }
        model.executeUndo();
        return new CommandResult(String.format(MESSAGE_SUCCESS, "UNKNOWN"));
    }
}
