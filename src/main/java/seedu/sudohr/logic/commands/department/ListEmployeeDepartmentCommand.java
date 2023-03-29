package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.DepartmentContainsEmployeePredicate;

/**
 * List a given employee's departments.
 */
public class ListEmployeeDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "led";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all of a given employee's departments\n"
            + "Parameters: " + PREFIX_EMPLOYEE + "Employee ID\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EMPLOYEE + "100";

    private final DepartmentContainsEmployeePredicate predicate;

    public ListEmployeeDepartmentCommand(DepartmentContainsEmployeePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredDepartmentList(predicate);

        if (model.getEmployee(predicate.getId()) == null) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_DEPARTMENTS_LISTED_OVERVIEW, model.getFilteredDepartmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListEmployeeDepartmentCommand // instanceof handles nulls
                && predicate.equals(((ListEmployeeDepartmentCommand) other).predicate)); // state check
    }
}
