package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;



/**
 * Finds and lists all employees in ExecutivePro whose name contains any or all of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all employees whose names contain any or all"
            + "(if asterisk is present) of the specified keywords (case-insensitive) and displays them as a list "
            + "with index numbers.\n"
            + "Parameters: [*] KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Predicate<Employee> predicate;

    /**
     * Creates a {@code FindCommand} to find employees according to keywords.
     * @param predicate the predicate to be searched on.
     */
    public FindCommand(Predicate<Employee> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        int listSize = model.getFilteredEmployeeList().size();
        if (listSize == 0) {
            return new CommandResult(Messages.MESSAGE_NO_EMPLOYEES_FOUND);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, listSize));
    }

    /**
     * Checks whether one instance of {@code FindCommand} is equal to another.
     * @param other the other instance.
     * @return boolean value.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
