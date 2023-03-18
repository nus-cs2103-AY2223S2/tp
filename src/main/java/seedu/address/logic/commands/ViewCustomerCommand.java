package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.CustomerIdPredicate;

/**
 * Finds and returns the customer details of the provided id.
 */
public class ViewCustomerCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewcustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display customer details given the customer id."
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final CustomerIdPredicate predicate;

    public ViewCustomerCommand(CustomerIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        Customer current = model.getFilteredCustomerList().get(0);
        // TODO: Show all nested objects associated with customer
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMER_VIEW_OVERVIEW, current), ResultType.LISTED_CUSTOMERS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCustomerCommand // instanceof handles nulls
                && predicate.equals(((ViewCustomerCommand) other).predicate)); // state check
    }
}
