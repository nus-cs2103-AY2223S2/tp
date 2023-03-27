package seedu.sudohr.logic.commands.leave;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

/**
 * Lists leaves by a specified employee.
 */
public class ListLeavesByEmployeeCommand extends Command {
    public static final String COMMAND_WORD = "llbe";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the leaves taken by the specified employee"
            + "Parameters: EMPLOYEE_ID \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EMPLOYEE + "101";

    public static final String MESSAGE_SUCCESS = "Listed all leaves by employee %1$s";

    public static final String MESSAGE_EMPLOYEE_NOT_FOUND = "No employee exists with the given employee id.";

    private final Id employeeId;

    /**
     * Creates a ListLeavesByEmployeeCommand to list all leaves by the
     * specified {@code Employee}
     * @param employeeId
     */
    public ListLeavesByEmployeeCommand(Id employeeId) {
        requireNonNull(employeeId);
        this.employeeId = employeeId;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Employee employee = model.getEmployee(employeeId);

        if (employee == null) {
            throw new CommandException(MESSAGE_EMPLOYEE_NOT_FOUND);
        }

        model.updateFilteredLeaveList(l -> l.hasEmployee(employee));
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, employeeId)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListLeavesByEmployeeCommand // instanceof handles nulls
                && employeeId.equals(((ListLeavesByEmployeeCommand) other).employeeId));
    }

}
