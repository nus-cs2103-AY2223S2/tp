package trackr.logic.commands.supplier;

import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;

import trackr.logic.commands.AddItemCommand;
import trackr.model.ModelEnum;
import trackr.model.person.Supplier;

/**
 * Adds a supplier to the supplier list.
 */
public class AddSupplierCommand extends AddItemCommand<Supplier> {

    public static final String COMMAND_WORD = "add_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "add_s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a supplier to Trackr. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "wheat "
            + PREFIX_TAG + "eggs";

    /**
     * Creates an AddSupplierCommand to add the specified {@code Supplier}.
     *
     * @param supplier The supplier to be added.
     */
    public AddSupplierCommand(Supplier supplier) {
        super(supplier, ModelEnum.SUPPLIER);
    }
}
