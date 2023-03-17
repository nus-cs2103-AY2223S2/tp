package seedu.loyaltylift.logic.commands;

import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.List;
import java.util.Set;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.model.tag.Tag;

/**
 * Adds the reward points of a customer
 * Points added could be negative
 */
public class AddPointsCommand extends Command {

    public static final String COMMAND_WORD = "addpoints";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": edits points of the customer identified by the index number used in the displayed customer list. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_POINTS + "[POINTS]\n"
            + "Example: " + COMMAND_WORD
            + " 1 "
            + "pt/-100";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, Points: %2$s";

    public static final String MESSAGE_SET_POINTS_SUCCESS = "Current points for Customer: %1$s";
    public static final String MESSAGE_INVALID_POINTS = "This customer will have invalid points";

    private final Index index;
    private final Points.AddPoints addPoints;

    /**
     * @param index of the customer in the filtered person list to set points
     * @param addPoints of the customer to be set
     */
    public AddPointsCommand(Index index, Points.AddPoints addPoints) {
        requireAllNonNull(index, addPoints);

        this.index = index;
        this.addPoints = addPoints;
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
     */
    private Customer createEditedCustomer(Customer customerToEdit) throws CommandException {
        assert customerToEdit != null;
        CustomerType customerType = customerToEdit.getCustomerType();
        Name name = customerToEdit.getName();
        Phone phone = customerToEdit.getPhone();
        Email email = customerToEdit.getEmail();
        Address address = customerToEdit.getAddress();
        Set<Tag> tags = customerToEdit.getTags();
        Points points = customerToEdit.getPoints();

        Points newPoints;
        try {
            newPoints = points.editPoints(this.addPoints);
        } catch (IllegalValueException e) {
            throw new CommandException(MESSAGE_INVALID_POINTS);
        }

        return new Customer(customerType, name, phone, email, address, tags, newPoints);
    }



    /**
     * Generates a command execution success message based on whether
     * the points are added
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
        if (!(other instanceof AddPointsCommand)) {
            return false;
        }

        // state check
        AddPointsCommand e = (AddPointsCommand) other;
        return index.equals(e.index)
                && addPoints.equals(e.addPoints);
    }
}

