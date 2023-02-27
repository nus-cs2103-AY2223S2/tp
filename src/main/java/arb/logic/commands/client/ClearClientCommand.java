package arb.logic.commands.client;

import static java.util.Objects.requireNonNull;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.AddressBook;
import arb.model.Model;

/**
 * Clears the address book.
 */
public class ClearClientCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
