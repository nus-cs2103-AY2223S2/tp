package seedu.dengue.model.person;

import static seedu.dengue.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.dengue.model.variant.Variant;

/**
 * Represents a Person in the Dengue Hotspot Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Postal postal;
    private final Date date;

    // Data fields
    private final Age age;
    private final Set<Variant> variants = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Postal postal, Date date, Age age, Set<Variant> variants) {
        requireAllNonNull(name, postal, date, age, variants);
        this.name = name;
        this.postal = postal;
        this.date = date;
        this.age = age;
        this.variants.addAll(variants);
    }

    public Name getName() {
        return name;
    }

    public Postal getPostal() {
        return postal;
    }

    public Date getDate() {
        return date;
    }

    public Age getAge() {
        return age;
    }

    public Person getCopy() {
        return new Person(this.name, this.postal, this.date, this.age, this.variants);
    }

    /**
     * Returns an immutable variant set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Variant> getVariants() {
        return Collections.unmodifiableSet(variants);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        boolean isSame;
        if (otherPerson == this) {
            return true;
        } else if (otherPerson == null) {
            return false;
        } else {
            boolean hasSameName = otherPerson.getName().equals(getName());
            boolean hasSameAge = otherPerson.getAge().equals(getAge());
            boolean hasSamePostal = otherPerson.getPostal().equals(getPostal());
            isSame = hasSameAge && hasSameName && hasSamePostal;
            return isSame;
        }


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
                && otherPerson.getPostal().equals(getPostal())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getAge().equals(getAge())
                && otherPerson.getVariants().equals(getVariants());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, postal, date, age, variants);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Postal: ")
                .append(getPostal())
                .append("; Date: ")
                .append(getDate())
                .append("; Age: ")
                .append(getAge());

        Set<Variant> variantSet = getVariants();
        if (!variantSet.isEmpty()) {
            builder.append("; Variants: ");
            variantSet.forEach(builder::append);
        }
        return builder.toString();
    }
}
