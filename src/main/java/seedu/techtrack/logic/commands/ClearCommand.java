package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.techtrack.model.Model;
import seedu.techtrack.model.RoleBook;

/**
 * Clears the role book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Role book has been cleared!";


    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.setRoleBook(new RoleBook());
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}
