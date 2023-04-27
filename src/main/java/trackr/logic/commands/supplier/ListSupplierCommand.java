package trackr.logic.commands.supplier;

import trackr.logic.commands.ListItemCommand;
import trackr.model.ModelEnum;
import trackr.model.person.Supplier;

/**
 * Lists all suppliers in the supplier list to the user.
 */
public class ListSupplierCommand extends ListItemCommand<Supplier> {

    public static final String COMMAND_WORD = "list_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "list_s";

    /**
     * Creates a ListSupplierCommand to list all the suppliers.
     */
    public ListSupplierCommand() {
        super(ModelEnum.SUPPLIER);
    }
}
