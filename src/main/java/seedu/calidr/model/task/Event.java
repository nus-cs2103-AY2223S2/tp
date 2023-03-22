package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Description;
import seedu.calidr.model.task.params.EventDateTimes;
import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.params.Title;

/**
 * Represents an event - a task with specific start and end
 * dates and times.
 */
public class Event extends Task {

    private final EventDateTimes eventDateTimes;

    /**
     * Creates an Event with the given details.
     *
     * @param title The title of the Event.
     * @param eventDateTimes The date-times associated with the Event.
     * @param priority The priority of the Event.
     */
    public Event(Title title, Description description, Priority priority, EventDateTimes eventDateTimes) {
        super(title, description, priority);

        assert eventDateTimes != null;
        this.eventDateTimes = eventDateTimes;
    }

    public EventDateTimes getEventDateTimes() {
        return eventDateTimes;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " ("
                + eventDateTimes.toString()
                + ")";
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && eventDateTimes.equals(((Event) other).eventDateTimes);
    }
}
