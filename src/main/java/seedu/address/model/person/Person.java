package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, remark);
        //requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.address = Optional.ofNullable(address);
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getOptionalPhone() {
        return phone;
    }

    public Optional<Email> getOptionalEmail() {
        return email;
    }

    public Optional<Address> getOptionalAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.getOptionalPhone().equals(getOptionalPhone())
                && otherPerson.getOptionalEmail().equals(getOptionalEmail())
                && otherPerson.getOptionalAddress().equals(getOptionalAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        String phone = String.format("; Phone: %s", getOptionalPhone().map(Phone::toString).orElse(""));
        String email = String.format("; Email: %s", getOptionalEmail().map(Email::toString).orElse(""));
        String address = String.format("; Address: %s", getOptionalAddress().map(Address::toString).orElse(""));
        builder.append(getName())
                .append(phone)
                .append(email)
                .append(address)
                .append(getRemark())
                .append(" Tags: ");
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
