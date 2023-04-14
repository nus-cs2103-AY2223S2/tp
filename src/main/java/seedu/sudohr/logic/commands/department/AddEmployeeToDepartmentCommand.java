package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

/**
 * Adds an employee to an existing department inside SudoHR.
 */
public class AddEmployeeToDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "aetd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an employee to an existing department.\n"
            + "Parameters: "
            + PREFIX_EMPLOYEE + "EMPLOYEE_ID "
            + PREFIX_DEPARTMENT_NAME + "DEPARTMENT_NAME \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EMPLOYEE + "100 "
            + PREFIX_DEPARTMENT_NAME + "Software Engineering";

    public static final String MESSAGE_ADD_EMPLOYEE_TO_DEPARTMENT_SUCCESS = "New employee %1$s is added to %2$s";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the department.";
    public static final String MESSAGE_DEPARTMENT_NOT_FOUND = "The given department does not exist in SudoHR.";

    private final Id toAdd;
    private final DepartmentName departmentName;

    /**
     * Creates an AddEmployeeToDepartmentCommand to add the specified person with Id {@code toAdd} to the department
     * with DepartmentName{@code departmentName}
     */
    public AddEmployeeToDepartmentCommand(Id toAdd, DepartmentName departmentName) {
        requireNonNull(toAdd);
        requireNonNull(departmentName);
        this.toAdd = toAdd;
        this.departmentName = departmentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Employee employee = model.getEmployee(toAdd);
        Department department = model.getDepartment(departmentName);

        if (employee == null) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        }

        if (department == null) {
            throw new CommandException(MESSAGE_DEPARTMENT_NOT_FOUND);
        }

        if (department.hasEmployee(employee)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.addEmployeeToDepartment(employee, department);

        // only show the relevant department
        model.updateFilteredDepartmentList(d -> d.equals(department));

        // show the employees within this department
        model.updateFilteredEmployeeList(e -> department.hasEmployee(e));

        model.refresh(); // defensive coding

        return new CommandResult(String.format(MESSAGE_ADD_EMPLOYEE_TO_DEPARTMENT_SUCCESS, employee, department));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEmployeeToDepartmentCommand // instanceof handles nulls
                && toAdd.equals(((AddEmployeeToDepartmentCommand) other).toAdd)
                && departmentName.equals(((AddEmployeeToDepartmentCommand) other).departmentName));
    }


}
