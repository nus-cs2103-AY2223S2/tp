package seedu.loyaltylift.logic.commands;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;

/**
 * Reverts the status of the order
 */
public class RevertOrderStatusCommand extends Command {

    public static final String COMMAND_WORD = "revo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": reverts the order status \n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + " 1 ";

    public static final String MESSAGE_REVERT_STATUS_SUCCESS = "Reverted status for order: %1$s \n"
            + "New status is: %2$s";
    public static final String MESSAGE_INVALID_COMMAND = "This order status is already at its earliest stage";

    private final Index index;

    /**
     * @param index of the order in the filtered order list to revert status
     */
    public RevertOrderStatusCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Order orderToRevert = lastShownList.get(index.getZeroBased());
        Order revertedOrder = orderToRevert.revert();

        model.setOrder(orderToRevert, revertedOrder);

        return new CommandResult(generateSuccessMessage(revertedOrder));
    }

    /**
     * Generates a command execution success message based on whether
     * the order status was reverted
     */
    private String generateSuccessMessage(Order revertedOrder) {
        String message = MESSAGE_REVERT_STATUS_SUCCESS;
        return String.format(message, revertedOrder, revertedOrder.getStatus());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RevertOrderStatusCommand)) {
            return false;
        }

        // state check
        RevertOrderStatusCommand e = (RevertOrderStatusCommand) other;
        return index.equals(e.index);
    }
}

