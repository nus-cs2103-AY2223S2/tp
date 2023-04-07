package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.RecurringEventList;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.PersonAlreadyInGroupException;
import seedu.address.model.group.exceptions.PersonNotInGroupException;
import seedu.address.model.tag.Tag;
import seedu.address.model.time.TimeMask;

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
    private final IsolatedEventList isolatedEventList;
    private final RecurringEventList recurringEventList;
    private Set<Group> groups = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Group> groups,
                  IsolatedEventList isolatedEventList, RecurringEventList recurringEventList) {
        requireAllNonNull(name, phone, email, address, tags, groups, isolatedEventList, recurringEventList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.groups.addAll(groups);
        this.isolatedEventList = isolatedEventList;
        this.recurringEventList = recurringEventList;
    }

    /**
     * Add a group into person
     *
     * @param group Group that a person is in
     */
    public void addGroup(Group group) {
        if (groups.contains(group)) {
            throw new PersonAlreadyInGroupException();
        }
        this.groups.add(group);
    }


    /**
     * Remove a group from person
     *
     * @param group Group that a person does not belong in anymore
     */
    public void removeGroup(Group group) {
        if (!groups.contains(group)) {
            throw new PersonNotInGroupException();
        }
        this.groups.remove(group);
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

    public void addIsolatedEvent(IsolatedEvent event) {
        isolatedEventList.insert(event);
    }

    public IsolatedEventList getIsolatedEventList() {
        return isolatedEventList;
    }

    /**
     * Add the recurring event into the person's recurring event list.
     * @param event
     */
    public void addRecurringEvent(RecurringEvent event) {
        // TODO: Should standardise Add/Delete in EventList or in Person
        recurringEventList.insert(event);
        getRecurringMask().modifyOccupancy(event, true);
    }

    public RecurringEventList getRecurringEventList() {
        return recurringEventList;
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
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    public TimeMask getRecurringMask() {
        return recurringEventList.getRecurringMask();
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
     * Remove the expired isolated events from the person's isolated event list.
     */
    public void removeExpiredEvent() {
        int len = isolatedEventList.getSize();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < len; i++) {
            IsolatedEvent event = isolatedEventList.getIsolatedEvent(i);
            if (event.getStartDate().isBefore(now)) {
                isolatedEventList.deleteIsolatedEvent(event);
            }
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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGroups().equals(getGroups());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, groups);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        Set<Group> groups = getGroups();
        if (!groups.isEmpty()) {
            builder.append("\nGroups: ");
            groups.forEach(builder::append);
        }

        if (isolatedEventList.getSize() > 0) {
            builder.append("\n");
            builder.append(isolatedEventList);
        }

        if (recurringEventList.getSize() > 0) {
            builder.append("\n");
            builder.append(recurringEventList);
        }

        return builder.toString();
    }
}
