package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final ArrayList<Meeting> meetings;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.meetings = new ArrayList<>();
    }

    /**
     * Overloaded constructor to take in meetings as an argument
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  ArrayList<Meeting> meetings) {
        requireAllNonNull(name, phone, email, address, tags, meetings);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.meetings = meetings;
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

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Gets the most recent meeting that the person has
     */
    public Meeting getUpcomingMeeting() {
        if (meetings.isEmpty()) {
            return new Meeting();
        }

        Meeting mostUpcomingMeeting = meetings.get(0);

        for (Meeting meeting : meetings) {
            if (mostUpcomingMeeting.compareTo(meeting) > 0) {
                mostUpcomingMeeting = meeting;
            }
        }

        return mostUpcomingMeeting;
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
            && otherPerson.getPhone().equals(getPhone())
            && otherPerson.getEmail().equals(getEmail())
            && otherPerson.getAddress().equals(getAddress())
            && otherPerson.getTags().equals(getTags())
            && otherPerson.getMeetings().equals(getMeetings());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, meetings);
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
            .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Meeting> meetings = getMeetings();
        String meetingHeader = meetings.size() == 1
            ? "; Meeting: "
            : "; Meetings: ";
        if (!meetings.isEmpty()) {
            builder.append(meetingHeader);
            meetings.forEach(builder::append);
        }

        return builder.toString();
    }

}
