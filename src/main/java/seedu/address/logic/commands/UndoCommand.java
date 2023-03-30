package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "The last command %s has successfully been undone!";
    public static final String MESSAGE_FAILURE = "There has not been a recent modification to TeachMeSenpai to undo!";
    private String commandToUndo;
    private final boolean isModifying = false;

    @Override
    public boolean checkModifiable() {
        return isModifying;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.checkUndoable()) {
            throw new CommandException(MESSAGE_FAILURE);
        } else {
            model.undoAddressBook();
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            commandToUndo = commandHistory.getLastExecutedCommand();
            String message = String.format(MESSAGE_SUCCESS, commandToUndo);
//            commandHistory.updateCommandHistory(COMMAND_WORD);
            return new CommandResult(message);
        }
    }
}
