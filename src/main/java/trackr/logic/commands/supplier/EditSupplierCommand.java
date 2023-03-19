package trackr.logic.commands.supplier;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.commons.util.CollectionUtil;
import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.commons.Tag;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;

/**
 * Edits the details of an existing supplier in the supplier list.
 */
public class EditSupplierCommand extends Command {

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

    public static final String MESSAGE_EDIT_SUPPLIER_SUCCESS = "Edited Supplier: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplier already exists in the address book.";

    private final Index index;
    private final EditSupplierDescriptor editSupplierDescriptor;

    /**
     * @param index                  of the supplier in the filtered supplier list to edit
     * @param editSupplierDescriptor details to edit the supplier with
     */
    public EditSupplierCommand(Index index, EditSupplierDescriptor editSupplierDescriptor) {
        requireNonNull(index);
        requireNonNull(editSupplierDescriptor);

        this.index = index;
        this.editSupplierDescriptor = new EditSupplierDescriptor(editSupplierDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Supplier> lastShownList = model.getFilteredSupplierList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
        }

        Supplier supplierToEdit = lastShownList.get(index.getZeroBased());
        Supplier editedSupplier = createEditedSupplier(supplierToEdit, editSupplierDescriptor);

        if (!supplierToEdit.isSameItem(editedSupplier) && model.hasSupplier(editedSupplier)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
        }

        model.setSupplier(supplierToEdit, editedSupplier);
        model.updateFilteredSupplierList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(MESSAGE_EDIT_SUPPLIER_SUCCESS, editedSupplier));
    }

    /**
     * Creates and returns a {@code Supplier} with the details of {@code supplierToEdit}
     * edited with {@code editSupplierDescriptor}.
     */
    private static Supplier createEditedSupplier(Supplier supplierToEdit,
                                                 EditSupplierDescriptor editSupplierDescriptor) {
        assert supplierToEdit != null;

        PersonName updatedName = editSupplierDescriptor.getName().orElse(supplierToEdit.getPersonName());
        PersonPhone updatedPersonPhone = editSupplierDescriptor.getPhone().orElse(supplierToEdit.getPersonPhone());
        PersonEmail updatedPersonEmail = editSupplierDescriptor.getEmail().orElse(supplierToEdit.getPersonEmail());
        PersonAddress updatedPersonAddress = editSupplierDescriptor.getAddress()
                .orElse(supplierToEdit.getPersonAddress());
        Set<Tag> updatedTags = editSupplierDescriptor.getTags().orElse(supplierToEdit.getPersonTags());

        return new Supplier(updatedName, updatedPersonPhone, updatedPersonEmail, updatedPersonAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSupplierCommand)) {
            return false;
        }

        // state check
        EditSupplierCommand e = (EditSupplierCommand) other;
        return index.equals(e.index)
                && editSupplierDescriptor.equals(e.editSupplierDescriptor);
    }

    /**
     * Stores the details to edit the supplier with. Each non-empty field value will replace the
     * corresponding field value of the supplier.
     */
    public static class EditSupplierDescriptor {
        private PersonName name;
        private PersonPhone personPhone;
        private PersonEmail personEmail;
        private PersonAddress personAddress;
        private Set<Tag> tags;

        public EditSupplierDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSupplierDescriptor(EditSupplierDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.personPhone);
            setEmail(toCopy.personEmail);
            setAddress(toCopy.personAddress);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, personPhone, personEmail, personAddress, tags);
        }

        public void setName(PersonName name) {
            this.name = name;
        }

        public Optional<PersonName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(PersonPhone personPhone) {
            this.personPhone = personPhone;
        }

        public Optional<PersonPhone> getPhone() {
            return Optional.ofNullable(personPhone);
        }

        public void setEmail(PersonEmail personEmail) {
            this.personEmail = personEmail;
        }

        public Optional<PersonEmail> getEmail() {
            return Optional.ofNullable(personEmail);
        }

        public void setAddress(PersonAddress personAddress) {
            this.personAddress = personAddress;
        }

        public Optional<PersonAddress> getAddress() {
            return Optional.ofNullable(personAddress);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSupplierDescriptor)) {
                return false;
            }

            // state check
            EditSupplierDescriptor e = (EditSupplierDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
