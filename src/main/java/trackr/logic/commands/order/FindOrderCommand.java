package trackr.logic.commands.order;

import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;

import trackr.logic.commands.FindItemCommand;
import trackr.model.ModelEnum;
import trackr.model.order.Order;
import trackr.model.order.OrderContainsKeywordsPredicate;

/**
 * Finds and lists all order in order list whose description contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindOrderCommand extends FindItemCommand<Order> {
    public static final String COMMAND_WORD = "find_order";
    public static final String COMMAND_WORD_SHORTCUT = "find_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDERNAME + "Chocolate cookie";

    public FindOrderCommand(OrderContainsKeywordsPredicate predicate) {
        super(predicate, ModelEnum.ORDER);
    }
}
