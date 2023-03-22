package seedu.sudohr.logic.commands.leave;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;


public class ListLeavesByEmployeeCommand extends Command {
    public static final String COMMAND_WORD = "listEmployeeLeave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the leaves taken by the specified employee"
            + "Parameters: EMPLOYEE_ID \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EMPLOYEE + "101";

    public static final String MESSAGE_SUCCESS = "Listed all leaves by employee %1$s";

    private final Id employeeId;

    public ListLeavesByEmployeeCommand(Id employeeId) {
        requireNonNull(employeeId);
        this.employeeId = employeeId;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Employee employee = model.getEmployee(employeeId);
        model.updateFilteredLeaveList(l -> l.hasEmployee(employee));
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, employeeId)
        );
    }

}
