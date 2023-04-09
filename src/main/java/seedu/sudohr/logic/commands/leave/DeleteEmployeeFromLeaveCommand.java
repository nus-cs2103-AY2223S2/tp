package seedu.sudohr.logic.commands.leave;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import java.util.List;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveContainsEmployeePredicate;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * Deletes a employee from a specific leave in sudohr book.
 */
public class DeleteEmployeeFromLeaveCommand extends Command {
    public static final String COMMAND_WORD = "defl";

    public static final String MESSAGE_SUCCESS = "Employee %1$s is deleted from %2$s \n";

    public static final String MESSAGE_INVALID_DATE = "The employee has not taken leave on the input date ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an employee from a leave in SudoHR. \n"
            + "Parameters: "
            + PREFIX_EMPLOYEE + "ID "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMPLOYEE + "1 "
            + PREFIX_DATE + "2022-03-04 ";;

    private final LeaveDate dateToDelete;
    private final Id employeeId;

    /**
     * Creates an DeleteEmployeeFromLeaveCommand to delete the employee with 
     * {@code employeeId} from the leave on the specified {@code dateToDelete}
     */
    public DeleteEmployeeFromLeaveCommand(Id employeeId, LeaveDate dateToDelete) {
        requireNonNull(employeeId);
        requireNonNull(dateToDelete);
        this.dateToDelete = dateToDelete;
        this.employeeId = employeeId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Employee employeeToDelete = model.getEmployee(employeeId);

        if (employeeToDelete == null) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        }

        Leave leaveToDelete = new Leave(dateToDelete);

        if (!model.hasEmployeeOnLeave(dateToDelete, employeeToDelete)) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        leaveToDelete = model.getInternalLeaveIfExist(leaveToDelete);
        model.deleteEmployeeFromLeave(leaveToDelete, employeeToDelete);

        List<Employee> employeesToList = leaveToDelete.getEmployees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(employeesToList);

        model.updateFilteredEmployeeList(predicate);
        final Leave leaveToFilter = leaveToDelete;
        model.updateFilteredLeaveList(l -> l.equals(leaveToFilter));

        model.refresh(); // defensive coding
        return new CommandResult(String.format(MESSAGE_SUCCESS, employeeToDelete, leaveToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEmployeeFromLeaveCommand // instanceof handles nulls
                        && dateToDelete.equals(((DeleteEmployeeFromLeaveCommand) other).dateToDelete)
                        && employeeId.equals(((DeleteEmployeeFromLeaveCommand) other).employeeId));
    }

}
