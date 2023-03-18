package seedu.sudohr.logic.commands.leavecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveContainsEmployeePredicate;

/**
 * Deletes a employee from a specific leave in sudohr book.
 */
public class DeleteEmployeeFromLeaveCommand extends Command {
    public static final String COMMAND_WORD = "deleteEmployeeLeave";

    public static final String MESSAGE_EMPLOYEE_DOES_NOT_EXIST = "This employee does not exists"
            + "in the leave in sudohr book";

    public static final String MESSAGE_SUCCESS = "employee %1$s is deleted from %2$s";

    public static final String MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX = "The employee index is invalid";

    public static final String MESSAGE_INVALID_DATE = "The employee has not taken a leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a employee from leave in the sudohr book. ";

    private final Date dateToDelete;
    private final Index employeeToDeleteIndex;

    /**
     * Creates an DeleteEmployeeFromLeaveCommand to delete the employee at specified
     * {@code employeeIndex} from the leave at the specified {@code Index}
     */
    public DeleteEmployeeFromLeaveCommand(Index employeeIndex, Date dateToDelete) {
        requireNonNull(employeeIndex);
        requireNonNull(dateToDelete);
        this.dateToDelete = dateToDelete;
        employeeToDeleteIndex = employeeIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownEmployeeList = model.getFilteredEmployeeList();

        if (employeeToDeleteIndex.getZeroBased() >= lastShownEmployeeList.size()) {
            throw new CommandException(MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToDelete = lastShownEmployeeList.get(employeeToDeleteIndex.getZeroBased());

        Leave leaveToDelete = new Leave(dateToDelete);

        if (!model.hasEmployeeOnLeave(dateToDelete, employeeToDelete)) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        leaveToDelete = model.getInternalLeaveIfExist(leaveToDelete);
        model.deleteEmployeeFromLeave(leaveToDelete, employeeToDelete);

        Set<Employee> employeesToList = leaveToDelete.getAttendees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(employeesToList);

        model.updateFilteredEmployeeList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, employeeToDelete, leaveToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEmployeeFromLeaveCommand // instanceof handles nulls
                        && dateToDelete.equals(((DeleteEmployeeFromLeaveCommand) other).dateToDelete)
                        && employeeToDeleteIndex
                                .equals(((DeleteEmployeeFromLeaveCommand) other).employeeToDeleteIndex));
    }

}
