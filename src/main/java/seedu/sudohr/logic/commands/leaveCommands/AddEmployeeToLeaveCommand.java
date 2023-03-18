package seedu.sudohr.logic.commands.leavecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

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
 * Adds a employee using it's displayed index to a specific leave using it's
 * displayed index in sudohr book.
 */
public class AddEmployeeToLeaveCommand extends Command {
    public static final String COMMAND_WORD = "addEmployeeLeave";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a employee's leave in the sudohr book. ";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already has a leave on that day"
            + "in sudohr book";
    public static final String MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX = "The employee index is invalid";
    public static final String MESSAGE_SUCCESS = "New employee %1$s is added on %2$s";

    private final Date leaveDate;
    private final Index employeeToAddIndex;

    /**
     * Creates an AddEmployeeToLeaveCommand to add the leave for a employee at a
     * specified
     * {@code employeeIndex} on the specified {@code date}
     */
    public AddEmployeeToLeaveCommand(Index employeeIndex, Date leaveDate) {
        requireNonNull(employeeIndex);
        requireNonNull(leaveDate);
        this.leaveDate = leaveDate;
        employeeToAddIndex = employeeIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownEmployeeList = model.getFilteredEmployeeList();

        if (employeeToAddIndex.getZeroBased() >= lastShownEmployeeList.size()) {
            throw new CommandException(MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToAdd = lastShownEmployeeList.get(employeeToAddIndex.getZeroBased());

        Leave leaveToAdd = new Leave(leaveDate);

        leaveToAdd = model.getInternalLeaveIfExist(leaveToAdd);

        if (leaveToAdd.hasEmployee(employeeToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.addEmployeeToLeave(leaveToAdd, employeeToAdd);

        List<Employee> employeesToList = leaveToAdd.getEmployees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(employeesToList);

        model.updateFilteredEmployeeList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, employeeToAdd, leaveToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEmployeeToLeaveCommand // instanceof handles nulls
                        && leaveDate.equals(((AddEmployeeToLeaveCommand) other).leaveDate)
                        && employeeToAddIndex
                                .equals(((AddEmployeeToLeaveCommand) other).employeeToAddIndex));
    }

}
