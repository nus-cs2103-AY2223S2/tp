package seedu.sudohr.logic.commands.leavecommands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveContainsEmployeePredicate;
import seedu.sudohr.model.employee.Employee;

/**
 * Lists all employees attending a leave identified using it's displayed index
 * in sudohr book.
 */

public class ListEmployeeInLeaveCommand extends Command {
    public static final String COMMAND_WORD = "listLeaveEmployee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the employees part of the leave identified by the index number "
            + "used in the displayed employees list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Date targetDate;

    /**
     * Creates an ListEmployeeInLeaveCommand to list all employees attending the
     * leave at the specified {@code leaveIndex}
     */
    public ListEmployeeInLeaveCommand(Date date) {
        requireNonNull(date);
        targetDate = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Leave targetLeave = model.getInternalLeaveIfExist(new Leave(this.targetDate));
        Set<Employee> personsToList = targetLeave.getAttendees();
        LeaveContainsEmployeePredicate predicate = new LeaveContainsEmployeePredicate(personsToList);

        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }
}
