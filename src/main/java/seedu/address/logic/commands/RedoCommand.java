package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>();

    public static final String MESSAGE_SUCCESS = "The command %s has been redone!";
    public static final String MESSAGE_FAILURE =
            "TeachMeSenpai is already in the latest version! There is nothing to redo";
    private String commandToRedo;
    private final boolean isModifying = false;

    @Override
    public boolean checkModifiable() {
        return isModifying;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        if (!model.checkRedoable()) {
            throw new CommandException(MESSAGE_FAILURE);
        } else {
            model.redoAddressBook();
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            commandToRedo = commandHistory.getLatestModifyingCommand();
            String message = String.format(MESSAGE_SUCCESS, commandToRedo);
            commandHistory.updateCommandHistory(COMMAND_WORD);
            return new CommandResult(message);
        }
    }
}
