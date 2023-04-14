package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;


/**
 * Filters and lists all the employees who satisfy the condition specified by the user.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters and lists all employees who satisfy the given "
            + "filter for the payroll amount.\n"
            + "Parameters: FILTER_PARAMETER BOOLEAN_OPERATOR COMPARISON_AMOUNT \n"
            + "FILTER_PARAMETER can accept 'pr' or 'l' depicting Payroll and Leaves respectively\n"
            + "BOOLEAN_OPERATOR can accept '>','<' or '=' \n"
            + "COMPARISON_AMOUNT must be a non-negative integer \n"
            + "Example: " + COMMAND_WORD + " pr > 1000";
    private final Predicate<Employee> predicate;

    /**
     * Creates a {@code FilterCommand} to filter employees according to the {@code Predicate}
     * @param predicate the condition to filter employees.
     */
    public FilterCommand(Predicate<Employee> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        int listSize = model.getFilteredEmployeeList().size();
        if (listSize == 0) {
            return new CommandResult(Messages.MESSAGE_NO_EMPLOYEES_FILTERED);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, listSize));
    }

    /**
     * Checks whether one instance of {@code FilterCommand} is equal to another.
     * @param other the other instance.
     * @return boolean value.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
