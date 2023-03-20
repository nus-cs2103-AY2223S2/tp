package seedu.sudohr.logic.commands.employee;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

import java.util.List;

/**
 * Deletes an employee identified using it's displayed index from SudoHR.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

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
        List<Department> departmentList = model.getFilteredDepartmentList();

        if (!model.checkEmployeeExists(targetId)) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_TO_DELETE_NOT_FOUND);
        }

        Employee employeeToDelete = model.getEmployee(targetId);
        model.deleteEmployee(employeeToDelete);

        // cascade delete to department
        // this needs to be abstracted to ModelManager and to SudoHR...
        // add test methods as well.
        departmentList.stream()
                .filter(d -> d.hasEmployee(employeeToDelete))
                .forEach(d -> d.removeEmployee(employeeToDelete));

        // delete user from leaves
        model.cascadeDeleteUserInLeaves(employeeToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetId.equals(((DeleteCommand) other).targetId)); // state check
    }



}
