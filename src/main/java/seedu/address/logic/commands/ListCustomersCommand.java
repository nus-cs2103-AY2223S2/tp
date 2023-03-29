package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import seedu.address.model.Model;

/**
 * Lists all customers in the AutoM8 system to the user.
 */
public class ListCustomersCommand extends Command {

    public static final String COMMAND_WORD = "listcustomers";

    public static final String MESSAGE_SUCCESS = "Currently listing all customers.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        model.selectCustomer(model.getFilteredCustomerList().get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.CUSTOMERS);
    }
}










