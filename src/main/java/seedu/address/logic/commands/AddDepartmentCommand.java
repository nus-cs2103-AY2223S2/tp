package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.department.Department;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

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
}
