package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "clear";

    private static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all entries from the address book.\n"
            + "Example: " + COMMAND_WORD;
    private static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }
    public static String getMessageSuccess() {
        return MESSAGE_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
