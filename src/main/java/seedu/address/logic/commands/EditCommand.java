package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.INDEX_PLACEHOLDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Module;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(
            INDEX_PLACEHOLDER,
            PREFIX_NAME.asOptional(),
            PREFIX_PHONE.asOptional(),
            PREFIX_EMAIL.asOptional(),
            PREFIX_ADDRESS.asOptional(),
            PREFIX_EDUCATION.asOptional(),
            PREFIX_TELEGRAM.asOptional(),
            PREFIX_TAG.asOptional().asRepeatable(),
            PREFIX_MODULE.asOptional().asRepeatable()
    ));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + ARGUMENT_PREFIXES.stream()
                    .map(Prefix::toString)
                    .collect(Collectors.joining(" "))
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_TELEGRAM + "@john_goh"
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

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
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (model.canReplacePerson(personToEdit, editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateShowPerson(new FullNamePredicate(editedPerson.getName().fullName));
        model.commitAddressBook(COMMAND_WORD);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getOptionalPhone()).orElse(null);
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getOptionalEmail()).orElse(null);
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getOptionalAddress())
                .orElse(null);
        Education updatedEducation = editPersonDescriptor.getEducation()
                .orElse(personToEdit.getOptionalEducation()).orElse(null);
        Remark oldRemark = personToEdit.getOptionalRemark().orElse(null); // edit command does not allow editing remarks
        Telegram updatedTelegram = editPersonDescriptor.getTelegram()
                .orElse(personToEdit.getOptionalTelegram()).orElse(null);
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Module> updatedModules = editPersonDescriptor.getModules().orElse(personToEdit.getModules());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedEducation, oldRemark,
                    updatedTelegram, updatedModules, updatedTags);
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
        private Optional<Phone> phone;
        private Optional<Email> email;
        private Optional<Address> address;
        private Optional<Education> education;
        private Optional<Remark> remark;
        private Optional<Telegram> telegram;
        private Set<Module> modules;
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
            setEducation(toCopy.education);
            setAddress(toCopy.address);
            setModules(toCopy.modules);
            setTelegram(toCopy.telegram);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, education, telegram, modules, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = Optional.ofNullable(phone);
        }

        public void setPhone(Optional<Phone> phone) {
            this.phone = phone;
        }

        public Optional<Optional<Phone>> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = Optional.ofNullable(email);
        }

        public void setEmail(Optional<Email> email) {
            this.email = email;
        }

        public Optional<Optional<Email>> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = Optional.ofNullable(address);
        }

        public void setAddress(Optional<Address> address) {
            this.address = address;
        }

        public Optional<Optional<Address>> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setEducation(Education education) {
            this.education = Optional.ofNullable(education);
        }

        public void setEducation(Optional<Education> education) {
            this.education = education;
        }

        public Optional<Optional<Education>> getEducation() {
            return Optional.ofNullable(education);
        }

        public void setRemark(Remark remark) {
            this.remark = Optional.ofNullable(remark);
        }

        public void setRemark(Optional<Remark> remark) {
            this.remark = remark;
        }

        public Optional<Optional<Remark>> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = Optional.ofNullable(telegram);
        }

        public void setTelegram(Optional<Telegram> telegram) {
            this.telegram = telegram;
        }

        public Optional<Optional<Telegram>> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Module> modules) {
            this.modules = (modules != null) ? new HashSet<>(modules) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable module set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
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
                    && getTelegram().equals(e.getTelegram())
                    && getModules().equals(e.getModules())
                    && getTags().equals(e.getTags());
        }
    }
}
