package ezschedule.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ezschedule.commons.util.AppUtil;

/**
 * Represents an Event's time in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Time should only contain numeric characters, "
            + "follows the format HH:mm (starting from 00:00 to 23:59), and it should not be blank";

    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    public final LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        AppUtil.checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time, formatter);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if this time has past current present time.
     */
    public boolean isPastTime() {
        return time.isBefore(LocalTime.now());
    }

    /**
     * Returns true if time is before given time.
     */
    public boolean isBefore(Time other) {
        return time.isBefore(other.time);
    }

    /**
     * Returns true if time is after given time.
     */
    public boolean isAfter(Time other) {
        return time.isAfter(other.time);
    }

    @Override
    public int compareTo(Time otherTime) {
        return time.compareTo(otherTime.time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
