package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE_COUNT;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.LeaveCounter;

/**
 * Takes leave for an existing employee in the address book.
 */
public class LeaveCommand extends Command {

    public static final String COMMAND_WORD = "leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Helps the employee identified by the EMPLOYEE_ID take leave.\n"
            + "Parameters: EMPLOYEE_ID (must be a positive integer)\n"
            + PREFIX_LEAVE_COUNT + "LEAVE_COUNT "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LEAVE_COUNT + "3";

    public static final String MESSAGE_LEAVE_SUCCESS = "Employee: %1$s took %2$s days of leave.";
    public static final String MESSAGE_NOT_ENOUGH_LEAVE = "Employee: %1$s does not have enough leave.";
    public static final String MESSAGE_INVALID_LEAVE = LeaveCounter.MESSAGE_CONSTRAINTS;

    private final EmployeeId employeeId;
    private final int numberOfDaysLeave;

    /**
     * Creates an LeaveCommand for an employee to take leave
     */
    public LeaveCommand(EmployeeId employeeId, int numberOfDaysLeave) {
        this.employeeId = employeeId;
        this.numberOfDaysLeave = numberOfDaysLeave;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!EmployeeId.isValidEmployeeId(employeeId.toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }
        if (numberOfDaysLeave < 0 || numberOfDaysLeave > 365) {
            throw new CommandException(MESSAGE_INVALID_LEAVE);
        }

        Optional<Employee> optionalEmployee = model.getEmployee(employeeId);
        Employee employeeToTakeLeave = optionalEmployee.orElseThrow(() -> new
                CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX));

        if (!employeeToTakeLeave.getLeaveCounter().hasEnoughLeave(numberOfDaysLeave)) {
            throw new CommandException(String.format(MESSAGE_NOT_ENOUGH_LEAVE, employeeToTakeLeave.getName()));
        }

        LeaveCounter updatedLeaveCounter = employeeToTakeLeave.getLeaveCounter().takeLeave(numberOfDaysLeave);

        EditCommand.EditEmployeeDescriptor editEmployeeDescriptor = new EditCommand.EditEmployeeDescriptor();
        editEmployeeDescriptor.setLeaveCounter(updatedLeaveCounter);
        EditCommand editCommand = new EditCommand(employeeId, editEmployeeDescriptor);
        editCommand.execute(model);

        return new CommandResult(String.format(MESSAGE_LEAVE_SUCCESS, employeeToTakeLeave.getName(),
                numberOfDaysLeave));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LeaveCommand // instanceof handles nulls
                && employeeId.equals(((LeaveCommand) other).employeeId))
                && numberOfDaysLeave == (((LeaveCommand) other).numberOfDaysLeave); // state check
    }
}
