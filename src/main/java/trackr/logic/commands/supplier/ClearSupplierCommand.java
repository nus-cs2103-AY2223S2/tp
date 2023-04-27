package trackr.logic.commands.supplier;

import trackr.logic.commands.ClearItemCommand;
import trackr.model.ModelEnum;
import trackr.model.person.Supplier;

/**
 * Clears the supplier list.
 */
public class ClearSupplierCommand extends ClearItemCommand<Supplier> {

    public static final String COMMAND_WORD = "clear_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "clear_s";

    /**
     * Creates a ClearSupplierCommand to clear the supplier list.
     */
    public ClearSupplierCommand() {
        super(ModelEnum.SUPPLIER);
    }
}
