package seedu.sudohr.logic.commands.employee;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

/**
 * Deletes an employee identified using it's displayed index from SudoHR.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by their id, if it exists in SudoHR.\n"
            + "Parameters: eid/EMPLOYEE_ID\n"
            + "Example: " + COMMAND_WORD + " eid/1";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted Employee: %1$s";

    private final Id targetId;

    /**
     * Creates an DeleteCommand to delete employee with the specified id
     * {@code targetId}
     */
    public DeleteCommand(Id targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.checkEmployeeExists(targetId)) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_TO_DELETE_NOT_FOUND);
        }

        Employee employeeToDelete = model.getEmployee(targetId);
        model.cascadeDeleteEmployeeToDepartments(employeeToDelete);
        model.cascadeDeleteUserInLeaves(employeeToDelete);
        model.deleteEmployee(employeeToDelete);
        model.updateFilteredDepartmentList(Model.PREDICATE_SHOW_ALL_DEPARTMENTS);
        model.updateFilteredLeaveList(Model.PREDICATE_SHOW_ALL_NON_EMPTY_LEAVES);
        model.refresh();
        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetId.equals(((DeleteCommand) other).targetId)); // state check
    }



}
