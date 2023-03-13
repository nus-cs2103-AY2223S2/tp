package codoc.logic.commands;

import static codoc.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import codoc.commons.util.CollectionUtil;
import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.CliSyntax;
import codoc.model.Model;
import codoc.model.module.Module;
import codoc.model.person.Address;
import codoc.model.person.Email;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.person.Phone;
import codoc.model.skill.Skill;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person in the view panel "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_ADDRESS + "ADDRESS] "
            + "[" + CliSyntax.PREFIX_SKILL + "SKILL]... "
            + "[" + CliSyntax.PREFIX_MODULE + "MODULE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + "91234567 "
            + CliSyntax.PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit = model.getProtagonist();
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.setProtagonist(editedPerson);
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
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Skill> updatedSkills = editPersonDescriptor.getSkills().orElse(personToEdit.getSkills());
        Set<Module> updatedModules = editPersonDescriptor.getModules().orElse(personToEdit.getModules());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedSkills, updatedModules);
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
        return editPersonDescriptor.equals(e.editPersonDescriptor);
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
        private Set<Skill> skills;
        private Set<Module> modules;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSkills(toCopy.skills);
            setModules(toCopy.modules);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, skills, modules);
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
         * Sets {@code skills} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Module> modules) {
            this.modules = (modules != null) ? new HashSet<>(modules) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable module list, which throws {@code UnsupportedOperationException}
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
                    && getSkills().equals(e.getSkills())
                    && getModules().equals(e.getModules());
        }
    }
}
