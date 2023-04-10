package seedu.address.logic.parser.editpersoncommandsparser;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;

/**
 * Stores the details of the person.
 */
public class PersonDescriptor {

    private Optional<Index> index;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Tags tags;
    private Gender gender;
    private Major major;
    private Race race;
    private Modules modules;
    private CommunicationChannel comms;
    private Faculty faculty;

    public PersonDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public PersonDescriptor(PersonDescriptor toCopy) {
        setIndex(toCopy.index);
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
        setFaculty(toCopy.faculty);
    }



    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil
                .isAnyNonNull(name, phone, email, address, tags, gender, major, race, modules, comms, faculty);
    }

    public void setIndex(Optional<Index> index) {
        this.index = index;
    }

    public Optional<Index> getIndex() {
        return this.index;
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
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Tags> getTags() {
        return (tags != null) ? Optional.of(tags) : Optional.empty();
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

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Optional<Faculty> getFaculty() {
        return Optional.ofNullable(this.faculty);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDescriptor)) {
            return false;
        }

        // state check
        PersonDescriptor e = (PersonDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getTags().equals(e.getTags())
                && getGender().equals(e.getGender())
                && getMajor().equals(e.getMajor())
                && getRace().equals(e.getRace())
                && getModules().equals(e.getModules())
                && getComms().equals(e.getComms())
                && getFaculty().equals(e.getFaculty());
    }
}
