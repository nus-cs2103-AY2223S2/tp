package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.patientist.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.commons.util.CollectionUtil;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.tag.Tag;

/**
 * Edits the details of an existing person in the patientist book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "ID cannot be changed.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the patientist book.";
    public static final String MESSAGE_NOT_SHOWING_PERSON_LIST = "Edit Command does not work with wards.";

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

        Patientist patientist = (Patientist) model.getPatientist();
        if (!patientist.isShowingPersonList()) {
            throw new CommandException(MESSAGE_NOT_SHOWING_PERSON_LIST);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson;
        if (personToEdit instanceof Patient) {
            editedPerson = createEditedPatient((Patient) personToEdit, editPersonDescriptor);
        } else if (personToEdit instanceof Staff) {
            editedPerson = createEditedStaff((Staff) personToEdit, editPersonDescriptor);
        } else {
            throw new CommandException("Person that is being edited is neither a patient or a staff");
        }

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (editedPerson instanceof Patient) {
            model.setPatient((Patient) personToEdit, (Patient) editedPerson);
        } else if (editedPerson instanceof Staff) {
            model.setStaff((Staff) personToEdit, (Staff) editedPerson);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code patientToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPatient(Patient patientToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(patientToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(patientToEdit.getTags());

        return new Patient(patientToEdit.getIdNumber(), updatedName, updatedPhone, updatedEmail, updatedAddress,
                patientToEdit.getPriority(), patientToEdit.getPatientStatusDetails(),
                patientToEdit.getPatientToDoList(), updatedTags);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code patientToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedStaff(Staff staffToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert staffToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(staffToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(staffToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(staffToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(staffToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(staffToEdit.getTags());

        return new Staff(updatedName, updatedPhone, updatedEmail, staffToEdit.getIdNumber(), updatedAddress,
                updatedTags);
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
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
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
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
