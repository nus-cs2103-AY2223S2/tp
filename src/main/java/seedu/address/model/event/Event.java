package seedu.address.model.event;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.time.LocalDate;

public class Event {

    private Name name;
    private String startTime;
    private String endTime;

    public Event(Name name, String startTime, String endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Name getName() {
        return name;
    }

    public String getParsedStartTime() {
        return startTime;
    }

    public String getParsedEndTime() {
        return endTime;
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
                && otherEvent.getParsedStartTime().equals(getParsedStartTime())
                && otherEvent.getParsedEndTime().equals(getParsedEndTime());
    }
}
