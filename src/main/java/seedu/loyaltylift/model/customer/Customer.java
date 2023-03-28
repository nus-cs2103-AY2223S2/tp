package seedu.loyaltylift.model.customer;

import static seedu.loyaltylift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.tag.Tag;

/**
 * Represents a Customer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer {

    // Comparators
    public static final Comparator<Customer> SORT_NAME = Comparator.comparing(Customer::getName);
    public static final Comparator<Customer> SORT_POINTS = Comparator.comparing(Customer::getPoints)
            .reversed().thenComparing(SORT_NAME);

    // Predicates
    public static final Predicate<Customer> FILTER_SHOW_MARKED = customer -> customer.getMarked().value;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final CustomerType customerType;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Optional fields
    private final Points points;
    private final Marked marked;
    private final Note note;

    /**
     * Every field except note must be present and not null.
     */
    public Customer(CustomerType customerType, Name name, Phone phone, Email email,
            Address address, Set<Tag> tags) {
        this(customerType, name, phone, email, address, tags,
                new Points(0, 0),
                new Marked(false),
                new Note(""));
    }

    /**
     * Every field must be present and not null.
     */
    public Customer(CustomerType customerType, Name name, Phone phone, Email email,
            Address address, Set<Tag> tags, Points points, Marked marked, Note note) {
        requireAllNonNull(name, phone, email, address, tags, points);
        this.customerType = customerType;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.points = points;
        this.marked = marked;
        this.note = note;
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

    public Marked getMarked() {
        return marked;
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

    public Note getNote() {
        return note;
    }

    /**
     * Returns a unique ID for the {@code Customer}.
     * No 2 customer should exist with the same unique ID.
     * @return A string representation of the unique identification of the customer.
     */
    public final String getUid() {
        return getName().fullName;
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
                && otherCustomer.getUid().equals(getUid());
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
                && otherCustomer.getPoints().equals(getPoints())
                && otherCustomer.getMarked().equals(getMarked())
                && otherCustomer.getNote().equals(getNote());
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
                .append(getPoints())
                .append("; Bookmarked: ")
                .append(getMarked());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
