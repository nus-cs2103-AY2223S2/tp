package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_CUSTOMER;

import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.customer.Customer;

/**
 * View a customer identified using it's displayed index from the address book.
 */
public class ViewCustomerCommand extends Command {

    public static final String COMMAND_WORD = "viewc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a customer's information. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_CUSTOMER_SUCCESS = "Viewing Customer:\n %1$s";

    private final Index targetIndex;

    public ViewCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }
        Customer customerToView = lastShownList.get(targetIndex.getZeroBased());
        model.setCustomerToDisplay(customerToView);

        return new CommandResult(
                String.format(MESSAGE_VIEW_CUSTOMER_SUCCESS, customerToView),
                LIST_AND_SHOW_CUSTOMER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCustomerCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCustomerCommand) other).targetIndex)); // state check
    }
}
