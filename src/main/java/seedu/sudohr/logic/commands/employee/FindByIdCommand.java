package seedu.sudohr.logic.commands.employee;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.FindByIdPredicate;

/**
 * Finds the employee with the specified employee id.
 */
public class FindByIdCommand extends Command {

    public static final String COMMAND_WORD = "feid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the employee with the specified ID.\n"
            + "Parameters: eid/EMPLOYEE_ID\n"
            + "Example: " + COMMAND_WORD + " eid/1";

    private final FindByIdPredicate predicate;

    public FindByIdCommand(FindByIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.checkEmployeeExists(predicate.getId())) {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        }
        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEE_FOUND));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByIdCommand // instanceof handles nulls
                && predicate.equals(((FindByIdCommand) other).predicate)); // state check
    }
}
