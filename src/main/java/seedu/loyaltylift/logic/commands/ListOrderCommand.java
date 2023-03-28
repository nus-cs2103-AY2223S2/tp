package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.Comparator;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;

/**
 * Lists all orders in LoyaltyLift to the user.
 */
public class ListOrderCommand extends Command {

    public static final String COMMAND_WORD = "listo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all orders with an optional sort option "
        + "(created date by default) and displays them as a list with index numbers.\n"
        + "Parameters: [" + PREFIX_SORT + "{created|name|status}]\n"
        + "Example: " + COMMAND_WORD + " s/status";

    public static final String MESSAGE_SUCCESS = "Listed all orders";
    public static final String MESSAGE_INVALID_SORT = "Unrecognized sort option";

    private final Comparator<Order> comparator;

    public ListOrderCommand(Comparator<Order> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredOrderList(comparator);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListOrderCommand // instanceof handles nulls
                && comparator.equals(((ListOrderCommand) other).comparator)); // state check
    }
}
