package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.model.EduMate;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public ViewCommandResult execute(Model model) {
        requireNonNull(model);
        model.setEduMate(new EduMate());
        return new ViewCommandResult(MESSAGE_SUCCESS, model.getUser());
    }
}
