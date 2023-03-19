package trackr.logic.commands.supplier;

import static java.util.Objects.requireNonNull;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.model.Model;

/**
 * Lists all suppliers in the supplier list to the user.
 */
public class ListSupplierCommand extends Command {

    public static final String COMMAND_WORD = "list_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "list_s";
    public static final String MESSAGE_SUCCESS = "Listed all suppliers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSupplierList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
