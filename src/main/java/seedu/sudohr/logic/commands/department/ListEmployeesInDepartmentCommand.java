package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;

/**
 * Lists employees in a given department.
 */
public class ListEmployeesInDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "leid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all employees in the specified department.\n"
            + "Parameters: "
            + PREFIX_DEPARTMENT_NAME + "DEPARTMENT_NAME"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_WORD + PREFIX_DEPARTMENT_NAME + "Human Resources";

    public static final String MESSAGE_SUCCESS = "Listed all employees in %1$s";

    private final Department department;

    /**
     * Creates a ListEmployeesInDepartmentCommand to list all employees in the
     * specified {@code Department}
     * @param department
     */
    public ListEmployeesInDepartmentCommand(Department department) {
        requireNonNull(department);
        this.department = department;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListEmployeesInDepartmentCommand // instanceof handles nulls
                && department.equals(((ListEmployeesInDepartmentCommand) other).department));
    }
}
