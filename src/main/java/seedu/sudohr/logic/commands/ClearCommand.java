package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.Model;

/**
 * Clears the sudohr book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new SudoHr());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
