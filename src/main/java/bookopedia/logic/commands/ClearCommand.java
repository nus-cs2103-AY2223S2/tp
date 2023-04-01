package bookopedia.logic.commands;

import static java.util.Objects.requireNonNull;

import bookopedia.model.AddressBook;
import bookopedia.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Bookopedia has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, true, null, -1);
    }
}
