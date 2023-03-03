package seedu.dengue.model.person;

import static seedu.dengue.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.dengue.model.tag.Tag;

/**
 * Represents a Person in the Dengue Hotspot Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Postal postal;
    private final DateAndTime dateAndTime;

    // Data fields
    private final Age age;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Postal postal, DateAndTime dateAndTime, Age age, Set<Tag> tags) {
        requireAllNonNull(name, postal, dateAndTime, age, tags);
        this.name = name;
        this.postal = postal;
        this.dateAndTime = dateAndTime;
        this.age = age;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Postal getPostal() {
        return postal;
    }

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public Age getAge() {
        return age;
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
                && otherPerson.getPostal().equals(getPostal())
                && otherPerson.getDateAndTime().equals(getDateAndTime())
                && otherPerson.getAge().equals(getAge())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, postal, dateAndTime, age, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Postal: ")
                .append(getPostal())
                .append("; DateTime: ")
                .append(getDateAndTime())
                .append("; Age: ")
                .append(getAge());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
