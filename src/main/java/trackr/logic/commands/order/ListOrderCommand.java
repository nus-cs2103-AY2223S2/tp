package trackr.logic.commands.order;

import trackr.logic.commands.ListItemCommand;
import trackr.model.ModelEnum;
import trackr.model.order.Order;

/**
 * Lists all orders in the order list to the user.
 */
public class ListOrderCommand extends ListItemCommand<Order> {

    public static final String COMMAND_WORD = "list_order";
    public static final String COMMAND_WORD_SHORTCUT = "list_o";

    /**
     * Creates a ListOrderCommand to list all the orders.
     */
    public ListOrderCommand() {
        super(ModelEnum.ORDER);
    }
}
