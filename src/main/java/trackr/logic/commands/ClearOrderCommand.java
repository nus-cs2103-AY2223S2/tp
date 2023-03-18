package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.model.Model;
import trackr.model.OrderList;

/**
 * Clears the order list.
 */
public class ClearOrderCommand extends Command {

    public static final String COMMAND_WORD = "clear_order";
    public static final String COMMAND_WORD_SHORTCUT = "clear_0";

    public static final String MESSAGE_SUCCESS = "Order has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setOrderList(new OrderList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
