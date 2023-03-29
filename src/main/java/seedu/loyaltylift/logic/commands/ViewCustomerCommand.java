package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.loyaltylift.commons.core.Messages;
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
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
        Customer customerToDelete = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(
                String.format(MESSAGE_VIEW_CUSTOMER_SUCCESS, customerToDelete),
                false,
                false,
                targetIndex.getZeroBased(),
                null);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCustomerCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCustomerCommand) other).targetIndex)); // state check
    }
}
