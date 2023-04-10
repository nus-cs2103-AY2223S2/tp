package teambuilder.model.person;

import static teambuilder.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import teambuilder.model.tag.Tag;

/**
 * Represents a Person in the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Major major;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Tag> teams = new HashSet<>();

    /**
     * Every field must be present and not null. A person can however have no tags within their team tags.
     */
    public Person(Name name, Phone phone, Email email, Address address, Major major,
                  Set<Tag> tags, Set<Tag> teams) {
        requireAllNonNull(name, email, address, major, tags, teams);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.major = major;
        this.tags.addAll(tags);
        this.teams.addAll(teams);
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

    public Major getMajor() {
        return major;
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
    public Set<Tag> getTeams() {
        return Collections.unmodifiableSet(teams);
    }

    /**
     * Returns number of tags.
     */
    public int getNumTags() {
        return tags.size();
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
                && otherPerson.getMajor().equals(getMajor())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getTeams().equals((getTeams()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, major, tags, teams);
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
                .append("; Major: ")
                .append(getMajor());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Tag> teams = getTeams();
        if (!teams.isEmpty()) {
            builder.append("; Teams: ");
            teams.forEach(builder::append);
        }
        return builder.toString();
    }

}
