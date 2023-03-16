package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;

/**
 * Deletes a vehicle identified using it's displayed index from viewcustomer.
 */
public class DeleteCustomerCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "deletecustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the id number displayed by the list command.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer: %1$s";

    private final Index targetIndex;

    public DeleteCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        Customer customerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCustomer(customerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCustomerCommand) other).targetIndex)); // state check
    }
}
