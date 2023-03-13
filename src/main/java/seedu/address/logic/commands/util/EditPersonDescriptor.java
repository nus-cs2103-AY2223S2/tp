package seedu.address.logic.commands.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;
/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditPersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Nric nric;
    private Age age;
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
        setNric(toCopy.nric);
        setAge(toCopy.age);
        setTags(toCopy.tags);
    }

    /**
     * Creates and returns a {@code Elderly} with the details of {@code elderlyToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Elderly createEditedElderly(Elderly elderlyToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert elderlyToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(elderlyToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(elderlyToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(elderlyToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(elderlyToEdit.getAddress());
        Nric updatedNric = editPersonDescriptor.getNric().orElse(elderlyToEdit.getNric());
        Age updatedAge = editPersonDescriptor.getAge().orElse(elderlyToEdit.getAge());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(elderlyToEdit.getTags());

        return new Elderly(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, elderlyToEdit.getRiskLevel(), updatedTags);
    }

    /**
     * Creates and returns a {@code Volunteer} with the details of {@code volunteerToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Volunteer createEditedVolunteer(Volunteer volunteerToEdit,
                                                  EditPersonDescriptor editPersonDescriptor) {
        assert volunteerToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(volunteerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(volunteerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(volunteerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(volunteerToEdit.getAddress());
        Nric updatedNric = editPersonDescriptor.getNric().orElse(volunteerToEdit.getNric());
        Age updatedAge = editPersonDescriptor.getAge().orElse(volunteerToEdit.getAge());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(volunteerToEdit.getTags());

        return new Volunteer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, updatedTags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone,
                email, address, nric, age, tags);
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

    public void setNric(Nric nric) {
        this.nric = nric;
    }

    public Optional<Nric> getNric() {
        return Optional.ofNullable(nric);
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Optional<Age> getAge() {
        return Optional.ofNullable(age);
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
                && getNric().equals(e.getNric())
                && getAge().equals(e.getAge())
                && getTags().equals(e.getTags());
    }
}
