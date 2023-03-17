package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * Represents a Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final Name name;

    // Data fields
    private final Rate rate;
    private final Address address;
    private final Time startTime;
    private final Time endTime;
    private final Set<Tag> tags = new HashSet<>();
    private Mark mark;
    private Contact contact;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, Rate rate, Address address, Time startTime, Time endTime, Set<Tag> tags) {
        requireAllNonNull(name, rate, address, tags);
        this.name = name;
        this.rate = rate;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags.addAll(tags);
        this.mark = new Mark();
        this.contact = new Contact();
    }

    public Name getName() {
        return name;
    }

    public Rate getRate() {
        return rate;
    }

    public Address getAddress() {
        return address;
    }
    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {
        return endTime;
    }

    public Mark getMark() {
        return mark;
    }

    public Contact getContact() {
        return contact;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Marks event as done.
     */
    public void mark() {
        mark.setDone();
    }

    /**
     * Marks event as undone.
     */
    public void unmark() {
        mark.setUndone();
    }

    /**
     * Link a contact to
     * @param contact
     */
    public void linkContact(Contact contact) {
        requireAllNonNull(contact);
        this.contact = contact;
    }

    /**
     * Returns true if both events have the same name and the same timing.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName())
                && otherEvent.getStartTime().equals(getStartTime())
                && otherEvent.getEndTime().equals(getEndTime());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getRate().equals(getRate())
                && otherEvent.getAddress().equals(getAddress())
                && otherEvent.getStartTime().equals(getStartTime())
                && otherEvent.getEndTime().equals(getEndTime())
                && otherEvent.getMark().equals(getMark())
                && otherEvent.getContact().equals(getContact())
                && otherEvent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, rate, address, startTime, endTime, mark, contact, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Rate: ")
                .append(getRate())
                .append("; Address: ")
                .append(getAddress())
                .append("; Timing: ")
                .append(getStartTime())
                .append(" to ")
                .append(getEndTime())
                .append("; Mark: ")
                .append(getMark());

        if (!contact.isNull()) {
            builder.append("; Contact: ")
                    .append(getContact());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
