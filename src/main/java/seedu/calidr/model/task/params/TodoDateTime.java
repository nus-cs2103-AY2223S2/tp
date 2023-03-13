package seedu.calidr.model.task.params;

import static java.util.Objects.requireNonNull;
import static seedu.calidr.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline date-time for a ToDo.
 */
public class TodoDateTime {

    public static final String MESSAGE_CONSTRAINTS = "The deadline date-time cannot be before the current time.";

    public final LocalDateTime byDateTime;

    /**
     * Constructs a {@code TodoDateTime}.
     *
     * @param dateTime A valid deadline date-time.
     */
    public TodoDateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        checkArgument(!isOver(dateTime), MESSAGE_CONSTRAINTS);

        byDateTime = dateTime;

    }

    /**
     * Returns true if a given date-time is a valid date-time.
     */
    public boolean isOver(LocalDateTime test) {
        return test.isBefore(LocalDateTime.now());
    }

    public String toString() {
        DateTimeFormatter formatToPrint = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "by: " + byDateTime.format(formatToPrint);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoDateTime // instanceof handles nulls
                && byDateTime.equals(((TodoDateTime) other).byDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return byDateTime.hashCode();
    }

}
