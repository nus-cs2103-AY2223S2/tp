package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final BusinessSize businessSize;

    private final Company company;

    private Mark mark;

    private final Priority priority;

    private final TransactionCount transactionCount;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, BusinessSize businessSize,
                  Company company, Priority priority, TransactionCount transactionCount, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, businessSize, company, priority, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.businessSize = businessSize;
        this.company = company;
        this.mark = new Mark("yes");
        this.priority = priority;
        this.transactionCount = transactionCount;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public String getNameString() { return getName().fullName; }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public BusinessSize getBusinessSize() {
        return businessSize;
    }

    public Long getBusinessSizeLong() { return businessSize.getLongValue(); }

    public Company getCompany() {
        return company;
    }

    public Mark getMark() {
        return mark;
    }

    public TransactionCount getTransactionCount() {
        return transactionCount;
    }


    public Integer getTransactionCountInt() { return transactionCount.getIntValue(); }


    public void changeMark() {
        mark = new Mark(mark.isMark ? "no" : "yes");
    }

    public Priority getPriority() {
        return priority;
    }

    public Integer getPriorityInt() { return priority.getPriorityValue(); }

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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getBusinessSize().equals(getBusinessSize())
                && otherPerson.getCompany().equals(getCompany())
                && otherPerson.getPriority().equals(getPriority())
                && otherPerson.getTransactionCount().equals(getTransactionCount())
                && otherPerson.getTags().equals(getTags());
    }



    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, businessSize, company,
                priority, mark, transactionCount, tags);
    }



    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Business Size: ")
                .append(getBusinessSize())
                .append("; Company: ")
                .append(getCompany())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Mark: ")
                .append(getMark())
                .append("; Transaction Count: ")
                .append(getTransactionCount());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Person o) {
        if (o.getBusinessSizeLong() - this.getBusinessSizeLong() > 0) {
            return 1;
        } else if (o.getBusinessSizeLong() - this.getBusinessSizeLong() < 0) {
            return -1;
        } else {
            return  0;
        }
        //return o.getBusinessSizeInt() - this.getBusinessSizeInt();
    }
}
