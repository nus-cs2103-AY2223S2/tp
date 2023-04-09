package trackr.logic.commands.order;

import trackr.commons.core.index.Index;
import trackr.logic.commands.DeleteItemCommand;
import trackr.model.ModelEnum;
import trackr.model.order.Order;

/**
 * Deletes an order identified using it's displayed index from the order list.
 */
public class DeleteOrderCommand extends DeleteItemCommand<Order> {
    public static final String COMMAND_WORD = "delete_order";
    public static final String COMMAND_WORD_SHORTCUT = "delete_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * Creates a DeleteOrderCommand to delete the order at given index.
     *
     * @param targetIndex The index of the order to be deleted.
     */
    public DeleteOrderCommand(Index targetIndex) {
        super(targetIndex, ModelEnum.ORDER);
    }
}
