package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_ORDER;

import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;

/**
 * View a order identified using it's displayed index from the address book.
 */
public class ViewOrderCommand extends Command {

    public static final String COMMAND_WORD = "viewo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View an order's information. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_ORDER_SUCCESS = "Viewing Order:\n %1$s";

    private final Index targetIndex;

    public ViewOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }
        Order orderToView = lastShownList.get(targetIndex.getZeroBased());
        model.setOrderToDisplay(orderToView);

        return new CommandResult(
                String.format(MESSAGE_VIEW_ORDER_SUCCESS, orderToView),
                LIST_AND_SHOW_ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewOrderCommand // instanceof handles nulls
                && targetIndex.equals(((ViewOrderCommand) other).targetIndex)); // state check
    }
}
