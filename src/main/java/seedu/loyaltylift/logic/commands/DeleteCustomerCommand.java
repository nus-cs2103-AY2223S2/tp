package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.REMOVE_INFO_FROM_VIEW;

import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.customer.Customer;

/**
 * Deletes a customer identified using it's displayed index from the address book.
 */
public class DeleteCustomerCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer: %1$s";

    private final Index targetIndex;

    public DeleteCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Customer customerToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteCustomer(customerToDelete);
        // customer or order belonging to customer is displayed
        boolean isDisplayed = customerToDelete.isSameCustomer(model.getCustomerToDisplay())
                || (model.getOrderToDisplay() != null
                && customerToDelete.isSameCustomer(model.getOrderToDisplay().getCustomer()));
        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerToDelete),
                isDisplayed ? REMOVE_INFO_FROM_VIEW : null);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCustomerCommand) other).targetIndex)); // state check
    }
}
