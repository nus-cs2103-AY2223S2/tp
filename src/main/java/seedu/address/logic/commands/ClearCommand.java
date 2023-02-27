package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FitBook;
import seedu.address.model.FitBookModel;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.setFitBook(new FitBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
