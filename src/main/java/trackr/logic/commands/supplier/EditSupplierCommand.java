package trackr.logic.commands.supplier;

import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditItemCommand;
import trackr.model.ModelEnum;
import trackr.model.commons.Tag;
import trackr.model.item.ItemDescriptor;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonDescriptor;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;

/**
 * Edits the details of an existing supplier in the supplier list.
 */
public class EditSupplierCommand extends EditItemCommand<Supplier> {

    public static final String COMMAND_WORD = "edit_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "edit_s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the supplier identified "
            + "by the index number used in the displayed supplier list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    /**
     * Creates an EditSupplierCommand to edit the supplier at given index.
     * @param index              of the supplier in the filtered supplier list to edit
     * @param supplierDescriptor details to edit the supplier with
     */
    public EditSupplierCommand(Index index, PersonDescriptor supplierDescriptor) {
        super(index, new PersonDescriptor(supplierDescriptor), ModelEnum.SUPPLIER);
    }

    @Override
    protected Supplier createEditedItem(Supplier itemToEdit, ItemDescriptor<? super Supplier> itemDescriptor) {
        assert itemToEdit != null;

        PersonDescriptor supplierDescriptor = (PersonDescriptor) itemDescriptor;

        PersonName updatedName = supplierDescriptor.getName().orElse(itemToEdit.getPersonName());
        PersonPhone updatedPersonPhone = supplierDescriptor.getPhone().orElse(itemToEdit.getPersonPhone());
        PersonEmail updatedPersonEmail = supplierDescriptor.getEmail().orElse(itemToEdit.getPersonEmail());
        PersonAddress updatedPersonAddress = supplierDescriptor.getAddress().orElse(itemToEdit.getPersonAddress());
        Set<Tag> updatedTags = supplierDescriptor.getTags().orElse(itemToEdit.getPersonTags());

        return new Supplier(updatedName, updatedPersonPhone, updatedPersonEmail, updatedPersonAddress, updatedTags);
    }
}
