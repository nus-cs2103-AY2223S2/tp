package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends PersonCommand {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.PERSON;

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
