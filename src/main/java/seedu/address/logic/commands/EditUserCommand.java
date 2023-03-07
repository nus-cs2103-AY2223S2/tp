package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.User;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "edituser";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited User: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    final EditUserDescriptor editUserDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditUserCommand(EditUserDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editUserDescriptor = new EditUserDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<User> lastShownList = model.getUserData().getUser();

        User user = lastShownList.get(0);
        User editedUser = createEditedUser(user, editUserDescriptor);


        model.setUser(editedUser);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedUser));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static User createEditedUser(User user, EditUserDescriptor editPersonDescriptor) {
        assert user != null;

        Name updatedName = editPersonDescriptor.getName().orElse(user.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(user.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(user.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(user.getAddress());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(user.getGender());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(user.getMajor());
        Modules updatedModules = editPersonDescriptor.getModules().orElse(user.getModules());
        Race updatedRace = editPersonDescriptor.getRace().orElse(user.getRace());
        CommunicationChannel updatedComms = editPersonDescriptor.getComms().orElse(user.getComms());
        Favorite currentFavorite = user.getIsFavorite();

        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(user.getTags());

        return new User(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedGender, updatedMajor, updatedModules, updatedRace, updatedTags, updatedComms, currentFavorite);
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
        return this.editUserDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditUserDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Gender gender;
        private Major major;
        private Race race;
        private Modules modules;
        private CommunicationChannel comms;

        public EditUserDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditUserDescriptor(EditUserDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setGender(toCopy.gender);
            setMajor(toCopy.major);
            setRace(toCopy.race);
            setModules(toCopy.modules);
            setComms(toCopy.comms);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, gender, major, race, modules, comms);
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
            if (!(other instanceof EditUserDescriptor)) {
                return false;
            }

            // state check
            EditUserDescriptor e = (EditUserDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public void setRace(Race race) {
            this.race = race;
        }

        public void setModules(Modules modules) {
            this.modules = modules;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(this.gender);
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(this.major);
        }

        public Optional<Modules> getModules() {
            return Optional.ofNullable(this.modules);
        }

        public Optional<Race> getRace() {
            return Optional.ofNullable(this.race);
        }

        public Optional<CommunicationChannel> getComms() {
            return Optional.ofNullable(this.comms);
        }

        public void setComms(CommunicationChannel comms) {
            this.comms = comms;
        }
    }
}
