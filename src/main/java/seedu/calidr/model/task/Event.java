package seedu.calidr.model.task;

import java.time.LocalDateTime;

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
     * Creates a Event object with the given details and MEDIUM priority.
     *
     * @param title The title of the Event.
     * @param eventDateTimes The date-times associaited with the Event.
     */
    public Event(Title title, EventDateTimes eventDateTimes) {
        super(title);

        assert eventDateTimes != null;

        this.eventDateTimes = eventDateTimes;
    }

    /**
     * Creates an Event with the given details.
     *
     * @param title The title of the Event.
     * @param eventDateTimes The date-times associated with the Event.
     * @param priority The priority of the Event.
     */
    public Event(Title title, EventDateTimes eventDateTimes, Priority priority) {
        super(title, priority);

        assert eventDateTimes != null;

        this.eventDateTimes = eventDateTimes;
    }

    public LocalDateTime getFrom() {
        return eventDateTimes.fromDateTime;
    }

    public LocalDateTime getTo() {
        return eventDateTimes.toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " ("
                + eventDateTimes.toString()
                + ")";
    }
}
