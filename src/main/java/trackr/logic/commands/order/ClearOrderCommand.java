package trackr.logic.commands.order;

import trackr.logic.commands.ClearItemCommand;
import trackr.model.ModelEnum;
import trackr.model.order.Order;

/**
 * Clears the order list.
 */
public class ClearOrderCommand extends ClearItemCommand<Order> {

    public static final String COMMAND_WORD = "clear_order";
    public static final String COMMAND_WORD_SHORTCUT = "clear_o";

    /**
     * Creates a ClearOrderCommand to clear the order list.
     */
    public ClearOrderCommand() {
        super(ModelEnum.ORDER);
    }
}
