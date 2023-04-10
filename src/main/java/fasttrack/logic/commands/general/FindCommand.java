package fasttrack.logic.commands.general;

import static java.util.Objects.requireNonNull;

import fasttrack.commons.core.Messages;
import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.model.expense.ExpenseContainsKeywordsPredicate;
import fasttrack.ui.ScreenType;

/**
 * Finds and lists all expenses in the expense tracker whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand implements GeneralCommand {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all expense which names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " apple orange cherry";

    private final ExpenseContainsKeywordsPredicate predicate;

    public FindCommand(ExpenseContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExpensesList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW,
                model.getFilteredExpenseList().size()), ScreenType.EXPENSE_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
