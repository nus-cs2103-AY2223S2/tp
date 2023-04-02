package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    private final Rank rank;
    private final Name name;
    private final Unit unit;
    private final Company company;
    private final Platoon platoon;

    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private boolean isFavorite = false;

    /**
     * Every field must be present and not null.
     */
    public Person(
            Rank rank,
            Name name,
            Unit unit,
            Company company,
            Platoon platoon,
            Phone phone,
            Email email,
            Address address,
            Set<Tag> tags) {

        requireAllNonNull(rank, name, unit, company, platoon, phone, email, address, tags);
        this.rank = rank;
        this.name = name;
        this.unit = unit;
        this.company = company;
        this.platoon = platoon;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Rank getRank() {
        return rank;
    }

    public Name getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    public Company getCompany() {
        return company;
    }

    public Platoon getPlatoon() {
        return platoon;
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

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    /**
     * Returns true if both persons are duplicates of each other.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        return PersonUtil.isDuplicated(this, otherPerson);
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
        return otherPerson.getRank().equals(getRank())
                && otherPerson.getName().equals(getName())
                && otherPerson.getUnit().equals(getUnit())
                && otherPerson.getCompany().equals(getCompany())
                && otherPerson.getPlatoon().equals(getPlatoon())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(rank, name, unit, company, platoon, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Rank: ")
                .append(getRank())
                .append("; Name: ")
                .append(getName())
                .append("; Unit: ")
                .append(getUnit())
                .append("; Company: ")
                .append(getCompany())
                .append("; Platoon: ")
                .append(getPlatoon())
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
