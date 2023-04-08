package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.ModifyFrozenStateException;

/**
 * Freezes the address book.
 */
public class FreezeCommand extends Command {
    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("freeze", "fr"));
    //CHECKSTYLE.ON: VisibilityModifier
    public static final String MESSAGE_SUCCESS = "Address book has been frozen!";
    public static final String MESSAGE_FAILURE = "Address book is already frozen!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.freezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        return new CommandResult(MESSAGE_SUCCESS, true, true);
    }
}
