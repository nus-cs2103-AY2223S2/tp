package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Lists employees in a given department.
 */
public class ListEmployeesInDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "leid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all employees in the given department.\n"
            + "Parameters: "
            + PREFIX_DEPARTMENT_NAME + "DEPARTMENT_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DEPARTMENT_NAME + "Human Resources";

    public static final String MESSAGE_SUCCESS = "Listed all employees in %1$s";

    public static final String MESSAGE_SUCCESS_NO_EMPLOYEE = "There are currently no employees in %1$s";
    public static final String MESSAGE_DEPARTMENT_NOT_FOUND = "The given department does not exist in SudoHR.";


    private final DepartmentName departmentName;

    /**
     * Creates a ListEmployeesInDepartmentCommand to list all employees in the
     * specified {@code Department}
     * @param departmentName
     */
    public ListEmployeesInDepartmentCommand(DepartmentName departmentName) {
        requireNonNull(departmentName);
        this.departmentName = departmentName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Department department = model.getDepartment(departmentName);

        if (department == null) {
            throw new CommandException(MESSAGE_DEPARTMENT_NOT_FOUND);
        }

        model.updateFilteredEmployeeList(e -> department.hasEmployee(e));

        if (model.getFilteredEmployeeList().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_NO_EMPLOYEE, departmentName));
        }


        return new CommandResult(String.format(MESSAGE_SUCCESS, departmentName));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListEmployeesInDepartmentCommand // instanceof handles nulls
                && departmentName.equals(((ListEmployeesInDepartmentCommand) other).departmentName));
    }
}
