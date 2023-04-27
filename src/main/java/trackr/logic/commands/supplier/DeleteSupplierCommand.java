package trackr.logic.commands.supplier;

import trackr.commons.core.index.Index;
import trackr.logic.commands.DeleteItemCommand;
import trackr.model.ModelEnum;
import trackr.model.person.Supplier;

/**
 * Deletes a supplier identified using it's displayed index from the supplier list.
 */
public class DeleteSupplierCommand extends DeleteItemCommand<Supplier> {

    public static final String COMMAND_WORD = "delete_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "delete_s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the supplier identified by the index number used in the displayed supplier list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * Creates a DeleteSupplierCommand to delete the supplier at the given index.
     *
     * @param targetIndex The index of the supplier to be deleted.
     */
    public DeleteSupplierCommand(Index targetIndex) {
        super(targetIndex, ModelEnum.SUPPLIER);
    }
}
