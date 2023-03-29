package seedu.event.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.event.model.ContactList;
import seedu.event.model.EventBook;
import seedu.event.model.Model;

/**
 * Clears the event book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Event book and contact list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setEventBook(new EventBook());
        model.setContactList(new ContactList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
