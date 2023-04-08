package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.policy.UniquePolicyList;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the index number used in the displayed client list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] " + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book.";

    private final Index index;
    private final EditClientDescriptor editClientDescriptor;

    /**
     * @param index                of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditCommand(Index index, EditClientDescriptor editClientDescriptor) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Phone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Email updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        //UniquePolicyList updatedPolicyList = editClientDescriptor.getPolicyList().orElse(clientToEdit.getAddress());

        UniquePolicyList policyList = clientToEdit.getPolicyList(); // To change policyList you must use EditPolicy
        Appointment appointment = clientToEdit.getAppointment();
        return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, policyList,
                appointment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient));
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
                && editClientDescriptor.equals(e.editClientDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;

        public EditClientDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress());
        }
    }
}
