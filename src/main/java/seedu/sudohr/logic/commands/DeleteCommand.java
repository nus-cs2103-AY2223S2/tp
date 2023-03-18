package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;

/**
 * Deletes an employee identified using it's displayed index from SudoHR.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by their id, if it exists in SudoHR.\n"
            + "Parameters: id/EMPLOYEE_ID\n"
            + "Example: " + COMMAND_WORD + " id/1";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted Employee: %1$s";

    private final Id targetId;

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
        model.deleteEmployee(employeeToDelete);
        /*
        Need to remove employees from any departments it belongs to + any leave tracked for this person
         */
        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetId.equals(((DeleteCommand) other).targetId)); // state check
    }
}
