package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.commons.core.Messages;
import trackr.model.Model;
import trackr.model.order.OrderContainsKeywordsPredicate;

/**
 * Finds and lists all order in order list whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindOrderCommand extends Command {
    public static final String COMMAND_WORD = "find_order";
    public static final String COMMAND_WORD_SHORTCUT = "find_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Chocolate cookie";

    private final OrderContainsKeywordsPredicate predicate;

    public FindOrderCommand(OrderContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderCommand // instanceof handles nulls
                && predicate.equals(((FindOrderCommand) other).predicate)); // state check
    }

}
