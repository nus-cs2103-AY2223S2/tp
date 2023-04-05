package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;

/**
 * Represents an Event in the Calendar.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Event {
    protected final Description description;
    protected final DateTime startDateTime;
    protected final DateTime endDateTime;
    protected final Recurrence recurrence;
    protected final Set<Person> taggedPeople;

    /**
     * Every field must be present and not null.
     * One Time Events will be handled by a None Recurrence Enum.
     */
    public Event(Description description, DateTime startDateTime, DateTime endDateTime, Recurrence recurrence,
                 Set<Person> taggedPeople) {
        requireAllNonNull(description, startDateTime, endDateTime, recurrence, taggedPeople);
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.recurrence = recurrence;
        this.taggedPeople = new HashSet<>();
        this.taggedPeople.addAll(taggedPeople);
    }

    /**
     * Returns a copy of this {@code Event}.
     */
    public abstract Event copy();

    public Description getDescription() {
        return description;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public Set<Person> getTaggedPeople() {
        return this.taggedPeople;
    }

    /**
     * @return Whether this event is recurring.
     */
    public boolean isRecurring() {
        return recurrence.isRecurring();
    }

    /**
     * Returns a copy of the event where all the dates and times of the event
     * are updated to reflect the next earliest occurrence.
     */
    public Event updateDateTime() {
        Event eventCopy = this.copy();
        if (!isRecurring()) {
            return eventCopy;
        }
        ChronoUnit timeUnit = eventCopy.getRecurrence().getIncrementUnit();
        while (eventCopy.endDateTime.getDateTime().isBefore(LocalDateTime.now())) {
            eventCopy.startDateTime.plus(timeUnit);
            eventCopy.endDateTime.plus(timeUnit);
        }
        return eventCopy;
    }

    /**
     * Creates a copy of this event with {@code person} added, if they were not previously in.
     */
    public Event addTaggedPerson(Person person) {
        requireAllNonNull(person);
        Event eventCopy = copy();
        eventCopy.taggedPeople.add(person);
        return eventCopy;
    }

    /**
     * Creates a copy of this event, with the {@code personToEdit} removed and the {@code editedPerson} added in.
     */
    public Event editTaggedPerson(Person personToEdit, Person editedPerson) {
        return hasTaggedPerson(personToEdit)
                ? deleteTaggedPerson(personToEdit).addTaggedPerson(editedPerson)
                : copy();
    }

    /**
     * Creates a copy of this event with the {@code person} removed, if they exist.
     */
    public Event deleteTaggedPerson(Person person) {
        /* Set#remove does not work here as Person#hashCode is stricter than Person#equals. */
        Event eventCopy = copy();
        eventCopy.taggedPeople.removeIf(p -> Objects.equals(person, p));
        return eventCopy;
    }

    /**
     * Returns true iff the given {@code person} is tagged to this {@code event}.
     */
    public boolean hasTaggedPerson(Person person) {
        /* Set#contains does not work here as Person#hashCode is stricter than Person#equals.
         * TODO: Fix Person#hashCode (likely due to a problem with SuperField#hashCode). */
        return taggedPeople.stream().anyMatch(p -> Objects.equals(person, p));
    }

    /**
     * Returns a copy of the start date time.
     */
    public DateTime getEffectiveStartDateTime() {
        return new DateTime(this.startDateTime.getDateTime());
    }

    /**
     * Returns a copy of the end date time.
     */
    public DateTime getEffectiveEndDateTime() {
        return new DateTime(this.endDateTime.getDateTime());
    }

    /**
     * Returns true if both Events have same Description.
     * This defines a weaker notion of equality between two Events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both Events have same Description, and data fields.
     * This defines a Stronger notion of equality between two Events.
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
        return otherEvent.getDescription().equals(getDescription())
                && otherEvent.getStartDateTime().equals(getStartDateTime())
                && otherEvent.getEndDateTime().equals(getEndDateTime())
                && otherEvent.getRecurrence().equals(getRecurrence())
                && otherEvent.getTaggedPeople().equals(getTaggedPeople());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.description + "; "
                + this.startDateTime + "; "
                + this.endDateTime + "; "
                + this.recurrence + "; ");
        for (Person p: taggedPeople) {
            str.append(String.format("[%s]", p.getName()));
        }
        return str.toString();
    }
}
