package seedu.loyaltylift.logic.commands;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.customer.*;
import seedu.loyaltylift.model.tag.Tag;

import java.util.List;
import java.util.Set;

import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

/**
 * Sets the reward points of a customer
 */
public class SetPointsCommand extends Command {

    public static final String COMMAND_WORD = "setpoints";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets points of the customer identified by the index number used in the displayed customer list. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_POINTS + "[POINTS]\n"
            + "Example: " + COMMAND_WORD
            + " 1 "
            + "pt/100";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, Points: %2$s";

    public static final String MESSAGE_SET_POINTS_SUCCESS = "Set points for Customer: %1$s";

    private final Index index;
    private final Points points;

    /**
     * @param index of the customer in the filtered person list to set points
     * @param points of the customer to be set
     */
    public SetPointsCommand(Index index, Points points) {
        requireAllNonNull(index, points);

        this.index = index;
        this.points = points;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Customer editedCustomerWithPoints = createEditedCustomer(customerToEdit);

        model.setCustomer(customerToEdit, editedCustomerWithPoints);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        return new CommandResult(generateSuccessMessage(editedCustomerWithPoints));
    }

    /**
     * Creates and returns a {@code Customer} with the details of {@code customerToEdit}
     * edited with {@code editCustomerDescriptor}.
     */
    private Customer createEditedCustomer(Customer customerToEdit) {
        assert customerToEdit != null;
        CustomerType customerType = customerToEdit.getCustomerType();
        Name name = customerToEdit.getName();
        Phone phone = customerToEdit.getPhone();
        Email email = customerToEdit.getEmail();
        Address address = customerToEdit.getAddress();
        Set<Tag> tags = customerToEdit.getTags();

        return new Customer(customerType, name, phone, email, address, tags, this.points);
    }

    /**
     * Generates a command execution success message based on whether
     * the points are set
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Customer editedCustomer) {
        String message = MESSAGE_SET_POINTS_SUCCESS;
        return String.format(message, editedCustomer);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetPointsCommand)) {
            return false;
        }

        // state check
        SetPointsCommand e = (SetPointsCommand) other;
        return index.equals(e.index)
                && points.equals(e.points);
    }
}
