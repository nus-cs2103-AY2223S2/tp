package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

/**
 * Represents an Event in the Calendar.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Event {
    private final Description description;
    private final DateTime startDateTime;
    private final DateTime endDateTime;
    private final Recurrence recurrence;

    /**
     * Every field must be present and not null.
     * One Time Events will be handled by a None Recurrence Enum.
     */
    public Event(Description description, DateTime startDateTime, DateTime endDateTime, Recurrence recurrence) {
        requireAllNonNull(description, startDateTime, endDateTime, recurrence);
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.recurrence = recurrence;
    }

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

    //Check if this is a recurring event
    public boolean isRecurring() {
        return recurrence.isRecurring();
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
}
