package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.department.DepartmentNameContainsKeywordsPredicate;

/**
 * Finds and lists all departments in SudoHr whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "fdep";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all departments whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Human Engineering Finance";


    private final DepartmentNameContainsKeywordsPredicate predicate;

    public FindDepartmentCommand(DepartmentNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDepartmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DEPARTMENT_LISTED_OVERVIEW, model.getFilteredDepartmentList().size())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDepartmentCommand // instanceof handles nulls
                && predicate.equals(((FindDepartmentCommand) other).predicate)); // state check
    }


}
