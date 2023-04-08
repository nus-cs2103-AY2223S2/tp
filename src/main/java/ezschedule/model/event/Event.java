package ezschedule.model.event;

/**
 * Represents an Event in the scheduler.
 * When comparing, earlier events come first, then later events (chronological order)
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event implements Comparable<Event> {

    private final Name name;
    private final Date date;
    private final Time startTime;
    private final Time endTime;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, Date date, Time startTime, Time endTime) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Name getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    /**
     * Returns true if the event has been completed/is over.
     *
     * @return true if event is completed.
     */
    public boolean isCompleted() {
        if (date.isPastDate()) {
            return true; // Event is before today
        } else if (date.isFutureDate()) {
            return false; // Event is after today
        } else {
            // Event is sometime today
            // Is current time passed the event end time?
            return endTime.isPastTime();
        }
    }

    /**
     * Returns true if otherEvent's date and time overlaps this event.
     *
     * @param otherEvent the other event to compare to.
     * @return true if event is overlap; otherwise, false.
     */
    public boolean isEventOverlap(Event otherEvent) {
        return isSameDate(otherEvent) && isTimeOverlap(otherEvent);
    }

    @Override
    public int compareTo(Event otherEvent) {
        if (otherEvent == this) {
            return 0;
        }

        int daysDelta = getDate().compareTo(otherEvent.getDate());
        if (daysDelta != 0) {
            return daysDelta;
        }

        return getStartTime().compareTo(otherEvent.getStartTime());
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
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getStartTime().equals(getStartTime())
                && otherEvent.getEndTime().equals(getEndTime());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nName: ")
                .append(getName())
                .append("\nDate: ")
                .append(getDate())
                .append("\nStart time: ")
                .append(getStartTime())
                .append("\nEnd time: ")
                .append(getEndTime());
        return sb.toString();
    }

    private boolean isSameDate(Event otherEvent) {
        return this.getDate().equals(otherEvent.getDate());
    }

    private boolean isTimeOverlap(Event otherEvent) {
        return isTimeIsEqual(otherEvent)
                || isTimeInBetween(otherEvent)
                || isStartTimeOverlap(otherEvent)
                || isEndTimeOverlap(otherEvent);
    }

    private boolean isTimeIsEqual(Event otherEvent) {
        return otherEvent.getStartTime().equals(getStartTime())
                && otherEvent.getEndTime().equals(getEndTime());
    }

    private boolean isTimeInBetween(Event otherEvent) {
        return otherEvent.getStartTime().isAfter(getStartTime())
                && otherEvent.getEndTime().isBefore(getEndTime());
    }

    private boolean isStartTimeOverlap(Event otherEvent) {
        return (otherEvent.getStartTime().isBefore(getStartTime())
                || otherEvent.getStartTime().equals(getStartTime()))
                && otherEvent.getEndTime().isAfter(getStartTime());
    }

    private boolean isEndTimeOverlap(Event otherEvent) {
        return otherEvent.getStartTime().isBefore(getEndTime())
                && (otherEvent.getEndTime().isAfter(getEndTime())
                || otherEvent.getEndTime().equals(getEndTime()));
    }
}
