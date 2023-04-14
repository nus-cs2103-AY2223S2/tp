package seedu.sudohr.logic.commands.employee;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;

/**
 * Lists all employees in the SudoHr to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "All employees listed!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
