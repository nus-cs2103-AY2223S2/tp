package trackr.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
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
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
