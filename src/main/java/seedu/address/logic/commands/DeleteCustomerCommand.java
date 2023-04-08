package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a customer identified using its id.
 */
public class DeleteCustomerCommand extends Command {

    public static final String COMMAND_WORD = "deletecustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the customer identified by the id number displayed by the list command.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer %d";

    private final int id;

    public DeleteCustomerCommand(int id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().removeCustomer(id);
            model.resetSelected();
            return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, id), Tab.CUSTOMERS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCustomerCommand // instanceof handles nulls
            && id == ((DeleteCustomerCommand) other).id); // state check
    }
}
