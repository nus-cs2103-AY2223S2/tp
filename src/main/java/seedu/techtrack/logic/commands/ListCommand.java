package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.techtrack.model.Model;

/**
 * Lists all roles in the role book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all roles";


    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoleList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}
