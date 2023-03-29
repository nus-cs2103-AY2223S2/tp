package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.expense.ExpenseInCategoryPredicate;
import seedu.address.model.expense.ExpenseInTimespanPredicate;

/**
 * List all the expenses in the expense tracker.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists expenses based on a filter"
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_TIMESPAN + "TIMESPAN (Week, Month, Year) "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "Food "
            + PREFIX_TIMESPAN + "week ";

    private final Optional<ExpenseInCategoryPredicate> categoryPredicate;
    private final Optional<ExpenseInTimespanPredicate> timespanPredicate;

    /**
     * Creates a ListCommand to list out {@code Expense} by given filters
     * @param categoryPredicate Predicate to filter by category
     * @param timespanPredicate Predicate to filter by recency
     */
    public ListCommand(Optional<ExpenseInCategoryPredicate> categoryPredicate,
                       Optional<ExpenseInTimespanPredicate> timespanPredicate) {
        this.categoryPredicate = categoryPredicate;
        this.timespanPredicate = timespanPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);

        if (categoryPredicate.isPresent() && timespanPredicate.isPresent()) {
            model.updateFilteredExpensesList(categoryPredicate.get().and(timespanPredicate.get()));
        } else if (categoryPredicate.isPresent()) {
            model.updateFilteredExpensesList(categoryPredicate.get());
        } else if (timespanPredicate.isPresent()) {
            model.updateFilteredExpensesList(timespanPredicate.get());
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && categoryPredicate.equals(((ListCommand) other).categoryPredicate)
                && timespanPredicate.equals(((ListCommand) other).timespanPredicate)); // state check
    }
}
