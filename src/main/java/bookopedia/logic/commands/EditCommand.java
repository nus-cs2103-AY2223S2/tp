package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static bookopedia.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import bookopedia.commons.core.index.Index;
import bookopedia.commons.util.CollectionUtil;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.logic.parser.CliSyntax;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Person;
import bookopedia.model.person.Phone;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_ADDRESS + "ADDRESS] "
            + "[" + CliSyntax.PREFIX_PARCEL + "PARCEL]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + "91234567 "
            + CliSyntax.PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_RETURN_STATUS_EDIT = "Cannot edit a return delivery!";
    public static final String MESSAGE_DONE_STATUS_EDIT = "Cannot edit a done delivery!";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.getDeliveryStatus() == DeliveryStatus.RETURN) {
            throw new CommandException(MESSAGE_RETURN_STATUS_EDIT);
        } else if (personToEdit.getDeliveryStatus() == DeliveryStatus.DONE) {
            throw new CommandException(MESSAGE_DONE_STATUS_EDIT);
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson),
                true, editedPerson, model.getIndexOf(editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Parcel> updatedParcels = editPersonDescriptor.getParcels().orElse(personToEdit.getParcels());
        DeliveryStatus updatedDeliveryStatus = editPersonDescriptor.getDeliveryStatus()
                .orElse(personToEdit.getDeliveryStatus());
        int updatedNoOfDeliveryAttempts = editPersonDescriptor.getNoOfDeliveryAttempts()
                .orElse(personToEdit.getNoOfDeliveryAttempts());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedParcels,
                updatedDeliveryStatus, updatedNoOfDeliveryAttempts);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Parcel> parcels;
        private DeliveryStatus deliveryStatus;
        private int noOfDeliveryAttempts;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code parcels} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setParcels(toCopy.parcels);
            setDeliveryStatus(toCopy.deliveryStatus);
            setNoOfDeliveryAttempts(toCopy.noOfDeliveryAttempts);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, parcels);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public Optional<DeliveryStatus> getDeliveryStatus() {
            return Optional.ofNullable(deliveryStatus);
        }

        public void setNoOfDeliveryAttempts(int noOfDeliveryAttempts) {
            this.noOfDeliveryAttempts = noOfDeliveryAttempts;
        }

        public Optional<Integer> getNoOfDeliveryAttempts() {
            return Optional.ofNullable(noOfDeliveryAttempts);
        }

        /**
         * Sets {@code parcels} to this object's {@code parcels}.
         * A defensive copy of {@code parcels} is used internally.
         */
        public void setParcels(Set<Parcel> parcels) {
            this.parcels = (parcels != null) ? new HashSet<>(parcels) : null;
        }

        /**
         * Returns an unmodifiable parcel set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code parcels} is null.
         */
        public Optional<Set<Parcel>> getParcels() {
            return (parcels != null) ? Optional.of(Collections.unmodifiableSet(parcels)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getParcels().equals(e.getParcels());
        }
    }
}
