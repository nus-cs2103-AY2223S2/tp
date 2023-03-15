package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Subject;
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
    private final Set<Subject> subjects = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark,
                  Set<Subject> subjects, Set<Tag> tags) {
        requireAllNonNull(name, remark);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.address = Optional.ofNullable(address);
        this.remark = remark;
        this.tags.addAll(tags);
        this.subjects.addAll(subjects);
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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
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
                && otherPerson.getSubjects().equals(getSubjects())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, subjects, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        String phone = getOptionalPhone().isEmpty() ? "" : String.format("; Phone: %s", getOptionalPhone().get());
        String email = getOptionalEmail().isEmpty() ? "" : String.format("; Email: %s",getOptionalEmail().get());
        String address = getOptionalAddress().isEmpty() ? "" : String.format("; Address: %s", getOptionalAddress().get());
        builder.append(getName())
                .append(phone)
                .append(email)
                .append(address)
                .append(getRemark());

        Set<Subject> subjects = getSubjects();
        if (!subjects.isEmpty()) {
            builder.append(" ; Subjects: ");
            subjects.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}
