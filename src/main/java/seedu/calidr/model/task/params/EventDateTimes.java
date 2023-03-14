package seedu.calidr.model.task.params;

import static seedu.calidr.commons.util.AppUtil.checkArgument;
import static seedu.calidr.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the two date-times associated with an Event.
 */
public class EventDateTimes {

    public static final String MESSAGE_CONSTRAINTS =
            "The END date-time should be after the START date-time";

    public final LocalDateTime fromDateTime;
    public final LocalDateTime toDateTime;


    /**
     * Constructs an {@code EventDateTimes}
     *
     * @param fromDateTime A valid START date-time.
     * @param toDateTime A valid END date-time.
     */
    public EventDateTimes(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        requireAllNonNull(fromDateTime, toDateTime);
        checkArgument(isValidEventDateTimes(fromDateTime, toDateTime), MESSAGE_CONSTRAINTS);

        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;

    }

    /**
     * Returns true if a particular set of START and END date-times
     * is valid.
     */
    public boolean isValidEventDateTimes(LocalDateTime start, LocalDateTime end) {
        return end.isAfter(start);
    }

    /**
     * Returns the string representation of the EventDateTimes object.
     */
    public String toString() {
        DateTimeFormatter formatToPrint = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "from: " + fromDateTime.format(formatToPrint)
                + " ; to: " + toDateTime.format(formatToPrint);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDateTimes // instanceof handles nulls
                && fromDateTime.equals(((EventDateTimes) other).fromDateTime)
                && toDateTime.equals(((EventDateTimes) other).toDateTime)); // state check
    }

}
