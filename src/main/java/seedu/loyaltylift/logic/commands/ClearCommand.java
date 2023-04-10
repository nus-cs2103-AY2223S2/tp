package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.REMOVE_INFO_FROM_VIEW;

import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, REMOVE_INFO_FROM_VIEW);
    }
}
