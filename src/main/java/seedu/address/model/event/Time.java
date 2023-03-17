package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents an Event's start or end time in the event book.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be of the format dd-MM-uuuu hh:mm";
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd-MM-uuuu HH:mm");
    private final LocalDateTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = parseLocalDateTime(time);
    }

    /**
     * Returns true if given string gives a valid time.
     */
    public static boolean isValidTime(String test) {
        if (test == null) {
            return false;
        }
        try {
            parseLocalDateTime(test);
            return true;
        } catch (DateTimeException e) {
            return false;
        }

    }

    private static LocalDateTime parseLocalDateTime(String test) {
        return LocalDateTime.parse(test, LOCAL_DATE_TIME_FORMATTER
                .withResolverStyle(ResolverStyle.STRICT));
    }

    @Override
    public String toString() {
        return time.format(LOCAL_DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}
