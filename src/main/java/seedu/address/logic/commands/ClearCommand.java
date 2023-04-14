package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ExecutiveProDb;
import seedu.address.model.Model;
import seedu.address.model.employee.EmployeeId;

/**
 * Clears the ExecutivePro database.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExecutiveProDb(new ExecutiveProDb());
        EmployeeId.setCount(1);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
