package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.order.Order;

/**
 * Deletes an order identitfied using it's displayed index from the order list.
 */
public class DeleteOrderCommand extends Command {
    public static final String COMMAND_WORD = "delete_order";
    public static final String COMMAND_WORD_SHORTCUT = "delete_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final Index targetIndex;

    public DeleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOrder(taskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrderCommand) other).targetIndex)); // state check
    }
}
