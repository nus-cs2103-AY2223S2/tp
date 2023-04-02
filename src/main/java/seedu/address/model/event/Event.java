package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Creates a copy of this event, with the Person {@code person} removed.
     */
    public Event deleteTaggedPerson(Person person) {
        Event eventCopy = copy();
        eventCopy.getTaggedPeople().removeIf(p -> Objects.equals(person, p));
        return eventCopy;
    }

    /**
     * Creates a copy of this event, with the {@code personToEdit} removed and the {@code editedPerson} added in.
     */
    public Event editTaggedPerson(Person personToEdit, Person editedPerson) {
        requireAllNonNull(editedPerson);
        Event eventCopy = copy();
        eventCopy.setTaggedPeople(taggedPeople.stream()
                .map(p -> Objects.equals(personToEdit, p) ? editedPerson : p)
                .collect(Collectors.toList()));
        return eventCopy;
    }

    public Event addTaggedPerson(Person person) {
        editTaggedPerson(person, person);
    }

    /**
     * Returns true if there is a person in the addressbook that is equal to the person {@code p}.
     */
    public boolean hasTaggedPerson(Person p) {
        for (Person p2: this.taggedPeople) {
            if (p.equals(p2)) {
                return true;
            }
        }
        return false;
    }

    public Set<Person> getTaggedPeople() {
        return this.taggedPeople;
    }

    public void setTaggedPeople(Collection<? extends Person> people) {
        requireAllNonNull(people);
        taggedPeople.clear();
        taggedPeople.addAll(people);
    }

    /**
     * @return Whether this event is recurring.
     */
    public boolean isRecurring() {
        return recurrence.isRecurring();
    }

    public DateTime getEffectiveStartDateTime() {
        return startDateTime;
    }

    public DateTime getEffectiveEndDateTime() {
        return endDateTime;
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
                && otherEvent.getRecurrence().equals(getRecurrence());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(
                this.description + "; " + this.startDateTime + "; " + this.endDateTime + "; " + this.recurrence);
        for (Person p: taggedPeople) {
            str.append(p.getName());
        }
        return str.toString();
    }
}
