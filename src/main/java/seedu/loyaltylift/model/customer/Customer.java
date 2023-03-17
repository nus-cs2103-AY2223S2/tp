package seedu.loyaltylift.model.customer;

import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.tag.Tag;

/**
 * Represents a Customer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final CustomerType customerType;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final Points points;
    /**
     * Every field must be present and not null.
     */
    public Customer(CustomerType customerType, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(customerType, name, phone, email, address, tags);
        this.customerType = customerType;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.points = new Points(0, 0);
    }

    /**
     * Every field must be present and not null.
     */
    public Customer(CustomerType customerType, Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                    Points points) {
        requireAllNonNull(name, phone, email, address, tags, points);
        this.customerType = customerType;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.points = points;
    }

    public CustomerType getCustomerType() {
        return customerType;
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

    public Points getPoints() {
        return points;
    }

    /**
     * Returns true if both customers have the same name.
     * This defines a weaker notion of equality between two customers.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && otherCustomer.getName().equals(getName());
    }

    /**
     * Returns true if both customers have the same identity and data fields.
     * This defines a stronger notion of equality between two customers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
                && otherCustomer.getPhone().equals(getPhone())
                && otherCustomer.getEmail().equals(getEmail())
                && otherCustomer.getAddress().equals(getAddress())
                && otherCustomer.getTags().equals(getTags())
                && otherCustomer.getCustomerType().equals(getCustomerType())
                && otherCustomer.getPoints().equals(getPoints());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(customerType, name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCustomerType())
                .append(": ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Points: ")
                .append(getPoints());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
