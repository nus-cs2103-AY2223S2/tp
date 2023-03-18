package seedu.address.model.tutee;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the end time of a tuition session.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime {

    public static final String MESSAGE_CONSTRAINTS =
            "End time should be a valid time in the format of HH:mm.";

    public static final String MESSAGE_CONSTRAINTS_AFTER_START_TIME =
            "End time should be after Start time.";

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public final LocalTime time;

    public final String endTime;

    /**
     * Constructs an {@code EndTime} with the specified end time.
     *
     * @param endTime A valid end time in the format of HH:mm.
     */
    public EndTime(String endTime) {
        requireNonNull(endTime);
        checkArgument(isValidEndTime(endTime), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(endTime, TIME_FORMATTER);
        this.endTime = endTime;
    }

    /**
     * Returns true if a given string is a valid end time.
     */
    public static boolean isValidEndTime(String test) {
        try {
            LocalTime.parse(test, TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return time.format(TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EndTime
                && time.equals(((EndTime) other).time));
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}

