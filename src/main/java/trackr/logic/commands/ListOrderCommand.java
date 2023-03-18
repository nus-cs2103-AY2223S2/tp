package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import trackr.model.Model;

/**
 * Lists all orders in the order list to the user.
 */
public class ListOrderCommand extends Command {

    public static final String COMMAND_WORD = "list_order";
    public static final String COMMAND_WORD_SHORTCUT = "list_o";
    public static final String MESSAGE_SUCCESS = "Listed all orders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
