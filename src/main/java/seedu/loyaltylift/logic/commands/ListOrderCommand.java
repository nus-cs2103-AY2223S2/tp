package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_ORDERS_ONLY;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;

/**
 * Lists all orders in LoyaltyLift to the user.
 */
public class ListOrderCommand extends Command {

    public static final String COMMAND_WORD = "listo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all orders with an optional sort "
        + "(created date by default) and filter option and displays them as a list with index numbers.\n"
        + "Parameters: [" + PREFIX_SORT + "{created|name|status}] + [" + PREFIX_FILTER + "STATUS]\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_SORT + "name " + PREFIX_FILTER + "pending";

    public static final String MESSAGE_SUCCESS = "Listed all orders";
    public static final String MESSAGE_INVALID_SORT = "Unrecognized sort option";
    public static final String MESSAGE_INVALID_FILTER = "Unrecognized filter option";

    private final Comparator<Order> comparator;
    private final Predicate<Order> predicate;

    /**
     * Constructs a default {@code ListOrderCommand}
     */
    public ListOrderCommand() {
        this(Order.SORT_CREATED_DATE, PREDICATE_SHOW_ALL_ORDERS);
    }

    /**
     * Constructs a {@code ListOrderCommand} with the given {@code comparator} and {@code predicate}
     */
    public ListOrderCommand(Comparator<Order> comparator, Predicate<Order> predicate) {
        this.comparator = comparator;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredOrderList(comparator);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(MESSAGE_SUCCESS, false, false,
                LIST_ORDERS_ONLY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListOrderCommand // instanceof handles nulls
                && comparator.equals(((ListOrderCommand) other).comparator)
                && predicate.equals(((ListOrderCommand) other).predicate)); // state check
    }
}
