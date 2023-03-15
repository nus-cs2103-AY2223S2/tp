package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.model.Model;
import trackr.model.SupplierList;

/**
 * Clears the supplier list.
 */
public class ClearSupplierCommand extends Command {

    public static final String COMMAND_WORD = "clear_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "clear_s";
    public static final String MESSAGE_SUCCESS = "Supplier list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSupplierList(new SupplierList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
