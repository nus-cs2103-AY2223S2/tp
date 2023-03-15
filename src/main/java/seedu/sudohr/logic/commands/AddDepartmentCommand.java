
package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.Department;

/**
 * Adds a department to SudoHR.
 */
public class AddDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "adep";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a department to the SudoHR. "
            + "Parameters: "
            + PREFIX_DEPARTMENT_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DEPARTMENT_NAME + "Human Resources Department ";

    public static final String MESSAGE_SUCCESS = "New department added: %1$s";
    public static final String MESSAGE_DUPLICATE_DEPARTMENT = "This department already exists in SudoHR";

    private final Department toAdd;

    /**
     * Creates an AddDepartmentCommand to add the specified {@code Department}
     */
    public AddDepartmentCommand(Department department) {
        requireNonNull(department);
        toAdd = department;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDepartment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEPARTMENT);
        }

        model.addDepartment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDepartmentCommand // instanceof handles nulls
                && toAdd.equals(((AddDepartmentCommand) other).toAdd));
    }
}
