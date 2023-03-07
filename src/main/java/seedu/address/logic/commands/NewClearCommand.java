package seedu.address.logic.commands;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Clears the openings list.
 */
public class NewClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Openings list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        // model.setOpeningsList(new OpeningsList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
