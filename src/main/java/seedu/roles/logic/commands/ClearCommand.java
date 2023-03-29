package seedu.roles.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.roles.model.AddressBook;
import seedu.roles.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Company book has been cleared!";


    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}
