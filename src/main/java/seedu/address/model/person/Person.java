package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.socialmedia.SocialMedia;
import seedu.address.model.tag.Module;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private Optional<Phone> phone;
    private Optional<Email> email;

    // Data fields
    private Optional<Address> address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();
    private Optional<Birthday> birthday;

    // Social media fields
    private Optional<SocialMedia> socialMedia;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Set<Tag> tags, Set<Module> modules) {
        requireAllNonNull(name, tags, modules);
        this.name = name;
        this.phone = Optional.empty();
        this.email = Optional.empty();
        this.address = Optional.empty();
        this.socialMedia = Optional.empty();
        this.tags.addAll(tags);
        this.birthday = Optional.empty();
        this.modules.addAll(modules);
    }

    public void setPhone(Phone phone) {
        this.phone = Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = Optional.ofNullable(address);
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = Optional.ofNullable(socialMedia);
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = Optional.ofNullable(birthday);
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<Birthday> getBirthday() {
        return birthday;
    }

    public Optional<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable module set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getModules().equals(getModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, modules);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Birthday: ")
                .append(getBirthday())
                .append("; Social media: ")
                .append(getSocialMedia())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        Set<Module> modules = getModules();
        if (!modules.isEmpty()) {
            builder.append("; Modules: ");
            modules.forEach(builder::append);
        }
        return builder.toString();
    }

}
