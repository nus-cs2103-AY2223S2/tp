package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseInCategoryPredicate;

/**
 * List all the expenses in the expense tracker.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all expenses";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists expenses based on a filter"
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_TIMESPAN + "TIMESPAN (Week, Month, Year) "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "Food "
            + PREFIX_TIMESPAN + "week ";

    private final Optional<ExpenseInCategoryPredicate> categoryPredicate;
    private final Optional<Predicate<Expense>> timespanPredicate;

    /**
     * Creates a ListCommand to list out {@code Expense} by given filters
     * @param categoryPredicate Predicate to filter by category
     * @param timespanPredicate Predicate to filter by recency
     */
    public ListCommand(Optional<ExpenseInCategoryPredicate> categoryPredicate,
                       Optional<Predicate<Expense>> timespanPredicate) {
        this.categoryPredicate = categoryPredicate;
        this.timespanPredicate = timespanPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
        if (categoryPredicate.isPresent()) {
            model.updateFilteredExpensesList(categoryPredicate.get());
        }
        if (timespanPredicate.isPresent()) {
            model.updateFilteredExpensesList(timespanPredicate.get());
        }
        // TODO: change to expenses listed overview
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
