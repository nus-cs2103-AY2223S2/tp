package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;
import static seedu.connectus.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.commons.core.index.Index;
import seedu.connectus.commons.util.CollectionUtil;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Birthday;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Edits the details of an existing person in the ConnectUS.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SOCMED_INSTAGRAM + "INSTAGRAM] "
            + "[" + PREFIX_SOCMED_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_SOCMED_WHATSAPP + "WHATSAPP] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in ConnectUS.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
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

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Person p = new Person(updatedName);

        p.setPhone(editPersonDescriptor.getPhone().orElse(personToEdit.getPhone().orElse(null)));
        p.setEmail(editPersonDescriptor.getEmail().orElse(personToEdit.getEmail().orElse(null)));
        p.setAddress(editPersonDescriptor.getAddress().orElse(personToEdit.getAddress().orElse(null)));
        p.setSocialMedia(editPersonDescriptor.getSocialMedia().map(s -> personToEdit.getSocialMedia()
                .orElse(SocialMedia.create()).updateWith(s)).orElse(personToEdit.getSocialMedia().orElse(null)));
        p.setBirthday(editPersonDescriptor.getBirthday().orElse(personToEdit.getBirthday().orElse(null)));
        p.setRemarks(editPersonDescriptor.getRemarks().orElse(personToEdit.getRemarks()));
        p.setModules(editPersonDescriptor.getModules().orElse(personToEdit.getModules()));
        p.setCcas(editPersonDescriptor.getCcas().orElse(personToEdit.getCcas()));
        p.setMajors(editPersonDescriptor.getMajors().orElse(personToEdit.getMajors()));

        return p;
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
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private SocialMedia socialMedia;
        private Set<Remark> remarks;
        private Birthday birthday;
        private Set<Module> modules;
        private Set<Cca> ccas;
        private Set<Major> majors;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSocialMedia(toCopy.socialMedia);
            setRemarks(toCopy.remarks);
            setModules(toCopy.modules);
            setCcas(toCopy.ccas);
            setMajors(toCopy.majors);
            setBirthday(toCopy.birthday);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, socialMedia, birthday);
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
            return (address != null)
                    ? Optional.of(address)
                    : Optional.empty();
        }

        public void setBirthday(Birthday birthday) {
            this.birthday = birthday;
        }

        public Optional<Birthday> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public void setSocialMedia(SocialMedia socialMedia) {
            this.socialMedia = socialMedia;
        }

        public Optional<SocialMedia> getSocialMedia() {
            return Optional.ofNullable(socialMedia);
        }

        /**
         * Sets {@code remarks} to this object's {@code remarks}.
         * A defensive copy of {@code remarks} is used internally.
         */
        public void setRemarks(Set<Remark> remarks) {
            this.remarks = (remarks != null) ? new HashSet<>(remarks) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code remarks} is null.
         */
        public Optional<Set<Remark>> getRemarks() {
            return (remarks != null) ? Optional.of(Collections.unmodifiableSet(remarks)) : Optional.empty();
        }


        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Module> modules) {
            this.modules = (modules != null) ? new HashSet<>(modules) : null;
        }

        /**
         * Returns an unmodifiable modules set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
        }

        /**
         * Sets {@code ccas} to this object's {@code ccas}.
         * A defensive copy of {@code ccas} is used internally.
         */
        public void setCcas(Set<Cca> ccas) {
            this.ccas = (ccas != null) ? new HashSet<>(ccas) : null;
        }

        /**
         * Returns an unmodifiable ccas set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code ccas} is null.
         */
        public Optional<Set<Cca>> getCcas() {
            return (ccas != null) ? Optional.of(Collections.unmodifiableSet(ccas)) : Optional.empty();
        }

        /**
         * Sets {@code majors} to this object's {@code majors}.
         * A defensive copy of {@code majors} is used internally.
         */
        public void setMajors(Set<Major> majors) {
            this.majors = (majors != null) ? new HashSet<>(majors) : null;
        }

        /**
         * Returns an unmodifiable majors set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code majors} is null.
         */
        public Optional<Set<Major>> getMajors() {
            return (majors != null) ? Optional.of(Collections.unmodifiableSet(majors)) : Optional.empty();
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
                    && getRemarks().equals(e.getRemarks());
        }
    }
}
