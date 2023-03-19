package vimification.model.task;

import java.time.LocalDateTime;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone, TaskType.EVENT);
        requireAllNonNull(start, end);
        this.startDateTime = start;
        this.endDateTime = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        this(description, false, start, end);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public Event clone() {
        return new Event(getDescription(), isDone(), startDateTime, endDateTime);
    }

    @Override
    public boolean isSameTask(Task task) {
        if (task == this) {
            return true;
        }

        if (!(task instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) task;
        return isSameTask(otherEvent)
                && otherEvent.getStartDateTime().equals(startDateTime)
                && otherEvent.getEndDateTime().equals(endDateTime);
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
        return super.equals(otherEvent)
                && otherEvent.getStartDateTime().equals(startDateTime)
                && otherEvent.getEndDateTime().equals(endDateTime);
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString())
                .append("; from: ")
                .append(getStartDateTime())
                .append(" to: ")
                .append(getEndDateTime());
        return builder.toString();
    }
}
