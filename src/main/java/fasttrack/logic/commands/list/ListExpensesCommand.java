package fasttrack.logic.commands.list;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static fasttrack.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Predicate;

import fasttrack.commons.core.Messages;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.model.Model;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.ExpenseInCategoryPredicate;
import fasttrack.model.expense.ExpenseInTimespanPredicate;
import fasttrack.ui.ScreenType;

/**
 * List all the expenses in the expense tracker.
 */
public class ListExpensesCommand implements ListCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists expenses based on a filter"
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_TIMESPAN + "TIMESPAN (Week, Month, Year)\n"
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
    public ListExpensesCommand(Optional<ExpenseInCategoryPredicate> categoryPredicate,
                       Optional<ExpenseInTimespanPredicate> timespanPredicate) {
        this.categoryPredicate = categoryPredicate;
        this.timespanPredicate = timespanPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        categoryPredicate.ifPresentOrElse(filter -> model.updateCategoryFilter(
                filter.getCategory()), () -> model.updateCategoryFilter(null));
        timespanPredicate.ifPresentOrElse(filter -> model.updateTimeSpanFilter(
                filter.getTimespan()), () -> model.updateTimeSpanFilter(ParserUtil.Timespan.ALL));
        Predicate<Expense> combinedPredicate = PREDICATE_SHOW_ALL_EXPENSES;
        if (categoryPredicate.isPresent()) {
            combinedPredicate = combinedPredicate.and(categoryPredicate.get());
            model.updateFilteredExpensesList(categoryPredicate.get());
        }
        if (timespanPredicate.isPresent()) {
            combinedPredicate = combinedPredicate.and(timespanPredicate.get());
            model.updateFilteredExpensesList(timespanPredicate.get());
        }
        model.updateFilteredExpensesList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()),
                ScreenType.EXPENSE_SCREEN);

    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListExpensesCommand // instanceof handles nulls
                && categoryPredicate.equals(((ListExpensesCommand) other).categoryPredicate)
                && timespanPredicate.equals(((ListExpensesCommand) other).timespanPredicate)); // state check
    }

    @Override
    public String toString() {
        return COMMAND_WORD + " " + categoryPredicate + " " + timespanPredicate;
    }
}
