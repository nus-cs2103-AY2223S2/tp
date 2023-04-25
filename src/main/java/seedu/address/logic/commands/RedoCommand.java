package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

//@@author wendy0107-reused
// Reused from https://github.com/AY2223S1-CS2103T-W17-4 and adapted from proposed implementation in AB3's Developer
// Guide https://se-education.org/addressbook-level3/DeveloperGuide.html
// with minor modifications such as renaming and some different implementation.
/**
 * Redo the changes done by a prior undo command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>();

    public static final String MESSAGE_SUCCESS = "The command %s has been redone!";
    public static final String MESSAGE_FAILURE =
            "TeachMeSenpai is already in the latest version! There is no recent undo to redo";
    private String commandToRedo;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.checkRedoable()) {
            throw new CommandException(MESSAGE_FAILURE);
        } else {
            commandToRedo = model.redoAddressBook();
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            String message = String.format(MESSAGE_SUCCESS, commandToRedo);
            return new CommandResult(message);
        }
    }
}
//@@author
