package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + Prefix.NAME + "NAME] "
            + "[" + Prefix.PHONE + "PHONE] "
            + "[" + Prefix.EMAIL + "EMAIL] "
            + "[" + Prefix.ADDRESS + "ADDRESS] "
            + "[" + Prefix.GROUP_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + Prefix.PHONE + "91234567 "
            + Prefix.EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_USER_SUCCESS = "Edited User: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    protected final ContactIndex contactIndex;
    protected final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param contactIndex of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(ContactIndex contactIndex, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.contactIndex = contactIndex;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (contactIndex == null) {
            return editUser(model);
        }

        return editPerson(model);
    }

    /**
     * Edits person at the given index
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    protected CommandResult editPerson(Model model) throws CommandException {
        IndexHandler indexHandler = new IndexHandler(model);
        Optional<Person> personToEditOption = indexHandler.getPersonByIndex(contactIndex);

        if (personToEditOption.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = personToEditOption.get();
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        editedPerson.setCommonModules(userModuleTags);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Edits the user information
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    protected CommandResult editUser(Model model) {
        User editedUser = createEditedUser(model.getUser(), editPersonDescriptor);

        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        model.getFilteredPersonList().forEach(person -> person.setCommonModules(userModuleTags));

        model.setUser(editedUser);
        return new CommandResult(String.format(MESSAGE_EDIT_USER_SUCCESS, editedUser));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    protected static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        ContactIndex unchangedContactIndex = personToEdit.getContactIndex();
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        TelegramHandle updatedTelegramHandle = editPersonDescriptor.getTelegramHandle()
                .orElse(personToEdit.getTelegramHandle());
        Set<GroupTag> updatedGroupTags = editPersonDescriptor.getGroupTags()
                .orElse(personToEdit.getImmutableGroupTags());
        Set<ModuleTag> updatedModuleTags = editPersonDescriptor.getModuleTags()
                .orElse(personToEdit.getImmutableModuleTags());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTelegramHandle,
                unchangedContactIndex, updatedGroupTags, updatedModuleTags);
    }

    /**
     * Creates and returns a {@code User} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    protected static User createEditedUser(User userToEdit, EditPersonDescriptor editPersonDescriptor) {
        Name updatedName = editPersonDescriptor.getName().orElse(userToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(userToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(userToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(userToEdit.getAddress());
        TelegramHandle updatedTelegramHandle = editPersonDescriptor.getTelegramHandle()
                .orElse(userToEdit.getTelegramHandle());
        Set<GroupTag> updatedGroupTags = editPersonDescriptor.getGroupTags()
                .orElse(userToEdit.getImmutableGroupTags());
        Set<ModuleTag> updatedModuleTags = editPersonDescriptor.getModuleTags()
                .orElse(userToEdit.getImmutableModuleTags());

        return new User(updatedName, updatedPhone, updatedEmail,
                updatedAddress, updatedTelegramHandle, new ContactIndex(0),
                updatedGroupTags, updatedModuleTags);
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
        return contactIndex.equals(e.contactIndex)
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
        private ContactIndex contactIndex;
        private TelegramHandle telegramHandle;
        private Set<GroupTag> groupTags;
        private Set<ModuleTag> moduleTags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code groupTags} and {@code moduleTags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setContactIndex(toCopy.contactIndex);
            setTelegramHandle(toCopy.telegramHandle);
            setGroupTags(toCopy.groupTags);
            setModuleTags(toCopy.moduleTags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, groupTags, moduleTags, telegramHandle);
        }

        public void setContactIndex(ContactIndex contactIndex) {
            this.contactIndex = contactIndex;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public ContactIndex getContactIndex() {
            return contactIndex;
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

        public void setTelegramHandle(TelegramHandle telegramHandle) {
            this.telegramHandle = telegramHandle;
        }

        public Optional<TelegramHandle> getTelegramHandle() {
            return Optional.ofNullable(telegramHandle);
        }

        /**
         * Sets {@code groupTags} to this object's {@code groupTags}.
         * A defensive copy of {@code groupTags} is used internally.
         */
        public void setGroupTags(Set<GroupTag> groupTags) {
            this.groupTags = (groupTags != null) ? new HashSet<>(groupTags) : null;
        }

        /**
         * Sets {@code moduleTags} to this object's {@code moduleTags}.
         * A defensive copy of {@code moduleTags} is used internally.
         */
        public void setModuleTags(Set<ModuleTag> moduleTags) {
            this.moduleTags = (moduleTags != null) ? new HashSet<>(moduleTags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code groupTags} is null.
         */
        public Optional<Set<GroupTag>> getGroupTags() {
            return (groupTags != null) ? Optional.of(Collections.unmodifiableSet(groupTags)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code moduleTags} is null.
         */
        public Optional<Set<ModuleTag>> getModuleTags() {
            return (moduleTags != null) ? Optional.of(Collections.unmodifiableSet(moduleTags)) : Optional.empty();
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
                    && getTelegramHandle().equals(e.getTelegramHandle())
                    && getGroupTags().equals(e.getGroupTags())
                    && getModuleTags().equals(e.getModuleTags());
        }
    }
}
