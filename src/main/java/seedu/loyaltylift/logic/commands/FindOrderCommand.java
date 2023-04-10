package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_ORDERS_ONLY;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.OrderNameContainsKeywordsPredicate;

/**
 * Finds and lists all orders in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Valrhona Chocolate Cake";

    private final OrderNameContainsKeywordsPredicate predicate;

    public FindOrderCommand(OrderNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredOrderList(Order.SORT_NAME);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                LIST_ORDERS_ONLY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderCommand // instanceof handles nulls
                && predicate.equals(((FindOrderCommand) other).predicate)); // state check
    }
}
