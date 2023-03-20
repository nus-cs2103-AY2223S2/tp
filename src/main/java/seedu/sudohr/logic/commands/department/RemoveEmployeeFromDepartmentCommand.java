package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

/**
 * Removes an employee from an existing department inside SudoHR.
 */
public class RemoveEmployeeFromDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "refd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an employee from an existing department.\n"
            + "Parameters: EMPLOYEE_ID, DEPARTMENT_NAME "
            + "[" + PREFIX_DEPARTMENT_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_ID + " 100 "
            + PREFIX_DEPARTMENT_NAME + "Software Engineering";

    public static final String MESSAGE_REMOVE_EMPLOYEE_FROM_DEPARTMENT_SUCCESS = "Employee %1$s is removed from %2$s";
    public static final String MESSAGE_DEPARTMENT_NOT_FOUND = "The given department does not exist in SudoHR.";
    public static final String MESSAGE_EMPLOYEE_NOT_FOUND = "The given employee does not exist in SudoHR.";
    public static final String MESSAGE_EMPLOYEE_NOT_FOUND_IN_DEPARTMENT =
            "The given employee does not exist in the given department";

    private final Id toRemove;
    private final DepartmentName departmentName;

    /**
     * Creates an RemoveEmployeeFromDepartmentCommand to remove the specified person with Id {@code toRemove} from the
     * department with DepartmentName{@code departmentName}
     */
    public RemoveEmployeeFromDepartmentCommand(Id toRemove, DepartmentName departmentName) {
        requireNonNull(toRemove);
        requireNonNull(departmentName);
        this.toRemove = toRemove;
        this.departmentName = departmentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Employee employee = model.getEmployee(toRemove);
        Department department = model.getDepartment(departmentName);

        if (employee == null) {
            throw new CommandException(MESSAGE_EMPLOYEE_NOT_FOUND);
        }

        if (department == null) {
            throw new CommandException(MESSAGE_DEPARTMENT_NOT_FOUND);
        }

        if (!department.hasEmployee(employee)) {
            throw new CommandException(MESSAGE_EMPLOYEE_NOT_FOUND_IN_DEPARTMENT);
        }

        department.removeEmployee(employee);
        return new CommandResult(String.format(MESSAGE_REMOVE_EMPLOYEE_FROM_DEPARTMENT_SUCCESS, employee, department));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveEmployeeFromDepartmentCommand // instanceof handles nulls
                && toRemove.equals(((RemoveEmployeeFromDepartmentCommand) other).toRemove)
                && departmentName.equals(((RemoveEmployeeFromDepartmentCommand) other).departmentName));
    }
}
