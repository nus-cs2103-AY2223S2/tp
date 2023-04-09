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
 * Adds a employee to a leave on a specific date in SudoHR.
 */
public class AddEmployeeToLeaveCommand extends Command {
    public static final String COMMAND_WORD = "aetl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a leave on a specific day to SudoHR. \n"
            + "Parameters: "
            + PREFIX_EMPLOYEE + "ID "
            + PREFIX_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMPLOYEE + "1 "
            + PREFIX_DATE + "2022-03-04 ";

    // maybe rename to duplicate leave? not sure if the right naming is used here
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee has already taken leave on this day "
            + "in SudoHR";
    public static final String MESSAGE_ADD_LEAVE_SUCCESS = "New employee %1$s has taken a new %2$s";

    private final LeaveDate leaveDate;
    private final Id employeeId;

    /**
     * Creates an AddEmployeeToLeaveCommand to add the leave for a employee with a
     * {@code employeeId} on the specified {@code leaveDate}
     */
    public AddEmployeeToLeaveCommand(Id employeeId, LeaveDate leaveDate) {
        requireNonNull(employeeId);
        requireNonNull(leaveDate);
        this.leaveDate = leaveDate;
        this.employeeId = employeeId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Employee employeeToAdd = model.getEmployee(employeeId);

        if (employeeToAdd == null) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        }

        Leave leaveToAdd = new Leave(leaveDate);

        leaveToAdd = model.getInternalLeaveIfExist(leaveToAdd);

        if (leaveToAdd.hasEmployee(employeeToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.addEmployeeToLeave(leaveToAdd, employeeToAdd);

        List<Employee> employeesToList = leaveToAdd.getEmployees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(employeesToList);

        model.updateFilteredEmployeeList(predicate);
        final Leave leaveToFilter = leaveToAdd;
        model.updateFilteredLeaveList(l -> l.equals(leaveToFilter));
        model.refresh(); // defensive coding

        return new CommandResult(String.format(MESSAGE_ADD_LEAVE_SUCCESS, employeeToAdd, leaveToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEmployeeToLeaveCommand // instanceof handles nulls
                        && leaveDate.equals(((AddEmployeeToLeaveCommand) other).leaveDate)
                        && employeeId.equals(((AddEmployeeToLeaveCommand) other).employeeId));
    }

}
