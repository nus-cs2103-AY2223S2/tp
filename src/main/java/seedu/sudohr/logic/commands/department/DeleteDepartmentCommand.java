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
 * Deletes a department identified by the department name.
 */
public class DeleteDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "ddep";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the department identified by the department name.\n"
            + "Parameters: " + PREFIX_DEPARTMENT_NAME + "DEPARTMENT_NAME \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DEPARTMENT_NAME + "Human Resources";

    public static final String MESSAGE_DELETE_DEPARTMENT_SUCCESS = "Deleted department: %1$s";

    public static final String MESSAGE_DEPARTMENT_NOT_EXIST = "The given department does not exist.";

    private final DepartmentName targetDepartment;

    public DeleteDepartmentCommand(DepartmentName targetDepartment) {
        this.targetDepartment = targetDepartment;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Department toDelete = model.getDepartment(targetDepartment);

        if (toDelete == null) {
            throw new CommandException(MESSAGE_DEPARTMENT_NOT_EXIST);
        }

        model.removeDepartment(toDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_DEPARTMENT_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDepartmentCommand // instanceof handles nulls
                && targetDepartment.equals(((DeleteDepartmentCommand) other).targetDepartment)); // state check
    }


}
