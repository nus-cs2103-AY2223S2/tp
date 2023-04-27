package trackr.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import trackr.commons.util.CollectionUtil;
import trackr.model.commons.Tag;
import trackr.model.item.ItemDescriptor;

/**
 * Stores the details to edit the supplier with. Each non-empty field value will replace the corresponding field
 * value of the person.
 */
//@@author liumc-sg-reused
public class PersonDescriptor implements ItemDescriptor<Person> {
    private PersonName name;
    private PersonPhone personPhone;
    private PersonEmail personEmail;
    private PersonAddress personAddress;
    private Set<Tag> tags;

    public PersonDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public PersonDescriptor(PersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.personPhone);
        setEmail(toCopy.personEmail);
        setAddress(toCopy.personAddress);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, personPhone, personEmail, personAddress, tags);
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public Optional<PersonName> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(PersonPhone personPhone) {
        this.personPhone = personPhone;
    }

    public Optional<PersonPhone> getPhone() {
        return Optional.ofNullable(personPhone);
    }

    public void setEmail(PersonEmail personEmail) {
        this.personEmail = personEmail;
    }

    public Optional<PersonEmail> getEmail() {
        return Optional.ofNullable(personEmail);
    }

    public void setAddress(PersonAddress personAddress) {
        this.personAddress = personAddress;
    }

    public Optional<PersonAddress> getAddress() {
        return Optional.ofNullable(personAddress);
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
        if (!(other instanceof PersonDescriptor)) {
            return false;
        }

        // state check
        PersonDescriptor otherPersonDescriptor = (PersonDescriptor) other;

        return getName().equals(otherPersonDescriptor.getName())
                && getPhone().equals(otherPersonDescriptor.getPhone())
                && getEmail().equals(otherPersonDescriptor.getEmail())
                && getAddress().equals(otherPersonDescriptor.getAddress())
                && getTags().equals(otherPersonDescriptor.getTags());
    }
}
