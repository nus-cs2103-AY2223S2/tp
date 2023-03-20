package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ApplicationModel;
import seedu.address.model.InternshipBook;


/**
 * Clears the address book.
 */
public class ClearApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Internship Book has been cleared!";

    @Override
    public CommandResult execute(ApplicationModel model) {
        requireNonNull(model);
        model.setInternshipBook(new InternshipBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
