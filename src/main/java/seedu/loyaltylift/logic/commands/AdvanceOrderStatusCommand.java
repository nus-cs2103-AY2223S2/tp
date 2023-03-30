package seedu.loyaltylift.logic.commands;

import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.time.LocalDate;
import java.util.List;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.CreatedDate;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.Status;

/**
 * Advances the status of the order
 */
public class AdvanceOrderStatusCommand extends Command {

    public static final String COMMAND_WORD = "advo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": advances the order status \n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + " 1 ";

    public static final String MESSAGE_ADVANCE_STATUS_SUCCESS = "Advanced status for order: %1$s \n"
            + "New status is: %2$s";
    private final Index index;

    /**
     * @param index of the order in the filtered order list to advance status
     */
    public AdvanceOrderStatusCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToAdvance = lastShownList.get(index.getZeroBased());
        Order advancedOrder = orderToAdvance.advance();

        model.setOrder(orderToAdvance, advancedOrder);

        return new CommandResult(generateSuccessMessage(advancedOrder));
    }

    /**
     * Generates a command execution success message based on whether
     * the order status is advanced
     */
    private String generateSuccessMessage(Order advancedOrder) {
        String message = MESSAGE_ADVANCE_STATUS_SUCCESS;
        return String.format(message, advancedOrder, advancedOrder.getStatus().getLatestStatus());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AdvanceOrderStatusCommand)) {
            return false;
        }

        // state check
        AdvanceOrderStatusCommand e = (AdvanceOrderStatusCommand) other;
        return index.equals(e.index);
    }
}
