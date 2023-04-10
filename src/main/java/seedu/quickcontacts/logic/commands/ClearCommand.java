package seedu.quickcontacts.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.QuickBook;

/**
 * Clears the quick book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all contacts and meetings from the address book.\n" + "Example: " + COMMAND_WORD;
    public static final String COMMAND_DESCRIPTION = "Clears all contacts and meetings from the address book.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setQuickBook(new QuickBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
