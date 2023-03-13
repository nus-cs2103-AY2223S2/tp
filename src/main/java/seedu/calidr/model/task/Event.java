package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Priority;

import java.time.LocalDateTime;

/**
 * Represents an event - a task with specific start and end
 * dates and times.
 */
public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a Event object with the given description and MEDIUM priority.
     *
     * @param description The description of the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);

        assert from != null;
        assert to != null;

        this.from = from;
        this.to = to;
    }

    /**
     * Creates a Event object with the given description and priority.
     *
     * @param description The description of the Event.
     * @param priority The priority associated with the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Priority priority) {
        super(description, priority);

        assert from != null;
        assert to != null;

        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Gets the String representation of the event to be stored in the text file.
     *
     * @return The String representation of the event to be stored in the text file.
     */
    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone()) ? "X" : " ";
        return String.format("E~%s~%s~%s~%s~%s", this.getPriority(), mark, this.getDescription(), this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + Task.getDateTimeString(this.from)
                + " ; to: " + Task.getDateTimeString(this.to)
                + ")";
    }
}
