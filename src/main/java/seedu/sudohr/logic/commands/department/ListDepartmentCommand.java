package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.model.Model.PREDICATE_SHOW_ALL_DEPARTMENTS;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;

/**
 * Lists all departments in the SudoHr to the user.
 */
public class ListDepartmentCommand extends Command {
    public static final String COMMAND_WORD = "ldep";

    public static final String MESSAGE_SUCCESS = "Listed all departments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDepartmentList(PREDICATE_SHOW_ALL_DEPARTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
