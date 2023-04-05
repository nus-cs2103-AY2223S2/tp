package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {
    private final EventName name;
    private final DateTime startDateTime;
    private final DateTime endDateTime;

    /**
     * Every field must be present and not null.
     */
    public Event(EventName name, DateTime startDateTime, DateTime endDateTime) {
        requireAllNonNull(name, startDateTime, endDateTime);
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public EventName getName() {
        return name;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
    }

    /**
     * Returns true if both events have the same name and start and end date times.
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
        return otherEvent.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(", Start Date Time: ")
                .append(getStartDateTime())
                .append(", End Date Time: ")
                .append(getEndDateTime());

        return builder.toString();
    }
}
