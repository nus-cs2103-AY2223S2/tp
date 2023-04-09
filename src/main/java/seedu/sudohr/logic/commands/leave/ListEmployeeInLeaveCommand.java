package seedu.sudohr.logic.commands.leave;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveContainsEmployeePredicate;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * Lists all employees attending a leave identified using its date
 * in SudoHR.
 */

public class ListEmployeeInLeaveCommand extends Command {
    public static final String COMMAND_WORD = "leol";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the employees part of the leave identified by the index number "
            + "used in the displayed employees list.\n"
            + "Parameters: DATE\n"
            + "Example: " + COMMAND_WORD + " 2022-03-04";

    private final LeaveDate targetDate;

    /**
     * Creates an ListEmployeeInLeaveCommand to list all employees taking
     * leave on the specified {@code date}
     */
    public ListEmployeeInLeaveCommand(LeaveDate date) {
        requireNonNull(date);
        targetDate = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Leave targetLeave = model.getInternalLeaveIfExist(new Leave(this.targetDate));
        List<Employee> employeesToList = targetLeave.getEmployees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(employeesToList);

        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListEmployeeInLeaveCommand // instanceof handles nulls
                        && targetDate.equals(((ListEmployeeInLeaveCommand) other).targetDate));
    }
}
