package seedu.loyaltylift.logic.commands;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_ORDER;

import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;

/**
 * Changes status of order to "cancelled"
 */
public class CancelOrderCommand extends Command {

    public static final String COMMAND_WORD = "cancelo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": changes the order status to 'Cancelled' \n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + " 1 ";


    public static final String MESSAGE_CANCEL_ORDER_SUCCESS = "The order: %1$s\n has been cancelled!";
    public static final String MESSAGE_INVALID_STATE = "This order is already completed or cancelled";

    private final Index index;

    /**
     * @param index of the order in the filtered order list to advance status
     */
    public CancelOrderCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Order orderToCancel = lastShownList.get(index.getZeroBased());
        Order cancelledOrder = orderToCancel.cancel();

        model.setOrder(orderToCancel, cancelledOrder);
        model.setOrderToDisplay(cancelledOrder);
        return new CommandResult(generateSuccessMessage(cancelledOrder),
                LIST_AND_SHOW_ORDER);
    }

    /**
     * Generates a command execution success message based on whether
     * the order is successfully cancelled
     */
    private String generateSuccessMessage(Order advancedOrder) {
        String message = MESSAGE_CANCEL_ORDER_SUCCESS;
        return String.format(message, advancedOrder);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CancelOrderCommand)) {
            return false;
        }

        // state check
        CancelOrderCommand e = (CancelOrderCommand) other;
        return index.equals(e.index);
    }
}

