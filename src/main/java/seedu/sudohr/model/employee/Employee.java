package seedu.sudohr.model.employee;

import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.sudohr.model.tag.Tag;

/**
 * Represents a Employee in the sudohr book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final Id id;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(Id id, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(id, name, phone, email, address, tags);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both employees have the same id.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getId().equals(getId());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return otherEmployee.getId().equals(getId())
                && otherEmployee.getName().equals(getName())
                && otherEmployee.getPhone().equals(getPhone())
                && otherEmployee.getEmail().equals(getEmail())
                && otherEmployee.getAddress().equals(getAddress())
                && otherEmployee.getTags().equals(getTags());
    }

    /**
     * Returns true if a different person shares the same email.
     */
    public boolean emailClashes(Employee otherPerson) {
        if (!(otherPerson instanceof Employee)) {
            return false;
        }

        if (isSameEmployee(otherPerson)) {
            return false;
        }

        return otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if a different person shares the same phone number.
     */
    public boolean phoneClashes(Employee otherPerson) {
        if (!(otherPerson instanceof Employee)) {
            return false;
        }

        if (isSameEmployee(otherPerson)) {
            return false;
        }

        return otherPerson.getPhone().equals(getPhone());
    }

    /**
     * Returns true if there is a clash in any of the two fields: email, phone.
     * Name is excluded since several people can share the same names.
     */
    public boolean clashes(Employee otherPerson) {
        if (isSameEmployee(otherPerson)) {
            return false;
        }

        return emailClashes(otherPerson) || phoneClashes(otherPerson);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Employee ID: ")
                .append(getId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
