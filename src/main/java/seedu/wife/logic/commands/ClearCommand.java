package seedu.wife.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.wife.model.Model;
import seedu.wife.model.Wife;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "WIFE has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setWife(new Wife());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
