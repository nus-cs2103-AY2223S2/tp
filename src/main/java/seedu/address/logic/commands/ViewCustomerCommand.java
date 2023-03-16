package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entity.person.CustomerIdPredicate;
import seedu.address.model.service.VehicleIdPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCustomerCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewCustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display customer details given the customer id (not"
            + "vehicle plate).\n"
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
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMER_VIEW_OVERVIEW));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCustomerCommand // instanceof handles nulls
                && predicate.equals(((ViewCustomerCommand) other).predicate)); // state check
    }
}
