package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {
    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("clear", "c"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, true, true);
    }
}
