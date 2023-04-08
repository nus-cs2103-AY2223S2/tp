package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_CUSTOMER;

import java.util.List;
import java.util.Set;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Marked;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.model.tag.Tag;

/**
 * Un-bookmarks an existing customer in the address book.
 */
public class UnmarkCustomerCommand extends Command {
    public static final String COMMAND_WORD = "unmarkc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Un-bookmarks the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_CUSTOMER_SUCCESS = "Un-bookmarked Customer: %1$s";

    private final Index index;

    /**
     * @param index of the customer in the filtered customer list to un-bookmark.
     */
    public UnmarkCustomerCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Customer customerToUnmark = lastShownList.get(index.getZeroBased());
        Customer unmarkedCustomer = createUnmarkedCustomer(customerToUnmark);

        model.setCustomer(customerToUnmark, unmarkedCustomer);
        model.setCustomerToDisplay(unmarkedCustomer);
        return new CommandResult(String.format(MESSAGE_UNMARK_CUSTOMER_SUCCESS, unmarkedCustomer),
                LIST_AND_SHOW_CUSTOMER);
    }

    /**
     * Creates and returns a {@code Customer} with the same details as {@code customerToMark}
     * but with the boolean {@code marked} set to false.
     */
    private static Customer createUnmarkedCustomer(Customer customerToUnmark) {
        assert customerToUnmark != null;

        CustomerType customerType = customerToUnmark.getCustomerType();
        Name name = customerToUnmark.getName();
        Phone phone = customerToUnmark.getPhone();
        Email email = customerToUnmark.getEmail();
        Address address = customerToUnmark.getAddress();
        Set<Tag> tags = customerToUnmark.getTags();
        Points points = customerToUnmark.getPoints();
        Note note = customerToUnmark.getNote();
        Marked marked = new Marked(false);

        return new Customer(customerType, name, phone, email, address, tags, points, marked, note);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCustomerCommand // instanceof handles nulls
                && index.equals(((UnmarkCustomerCommand) other).index)); // state check
    }
}
