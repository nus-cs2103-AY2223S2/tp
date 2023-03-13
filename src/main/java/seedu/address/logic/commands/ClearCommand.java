package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    // public static final String MESSAGE_CONFIRMATION = "Are you sure that you want to clear all the entries? ";
    public static final String MESSAGE_SUCCESS = "All internship application has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternEase(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
