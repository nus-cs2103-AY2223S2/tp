package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All internship application has been cleared!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<InternshipApplication> lastShownList = model.getFilteredInternshipList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL);
        }

        model.setInternEase(new AddressBook());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
