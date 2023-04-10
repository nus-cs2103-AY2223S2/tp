package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_CUSTOMERS_ONLY;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.customer.Customer;

/**
 * Lists all customers in the address book to the user.
 */
public class ListCustomerCommand extends Command {

    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all customers with an optional sort "
            + "(name by default) and filter option and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_SORT + "{name|points}] + [" + PREFIX_FILTER + "{marked|ind|ent}]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT + "points " + PREFIX_FILTER + "marked";

    public static final String MESSAGE_SUCCESS = "Listed all customers";
    public static final String MESSAGE_INVALID_SORT = "Unrecognized sort option";
    public static final String MESSAGE_INVALID_FILTER = "Unrecognized filter option";

    private final Comparator<Customer> comparator;
    private final Predicate<Customer> predicate;

    /**
     * Constructs a default {@code ListCustomerCommand}
     */
    public ListCustomerCommand() {
        this(Customer.SORT_NAME, PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    /**
     * Constructs a {@code ListCustomerCommand} with the given {@code comparator}
     */
    public ListCustomerCommand(Comparator<Customer> comparator, Predicate<Customer> predicate) {
        this.comparator = comparator;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredCustomerList(comparator);
        model.updateFilteredCustomerList(predicate);
        return new CommandResult(MESSAGE_SUCCESS, false, false,
                LIST_CUSTOMERS_ONLY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCustomerCommand // instanceof handles nulls
                && comparator.equals(((ListCustomerCommand) other).comparator)
                && predicate.equals(((ListCustomerCommand) other).predicate)); // state check
    }
}
