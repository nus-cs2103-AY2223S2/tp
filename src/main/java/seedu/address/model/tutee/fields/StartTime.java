package seedu.address.model.tutee.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the start time of a tuition session.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Start time should be a valid time in the format of HH:mm.";

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalTime time;
    public String startTime;

    /**
     * Constructs a {@code StartTime} with the specified start time.
     *
     * @param startTime A valid start time in the format of HH:mm.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(startTime, TIME_FORMATTER);
        this.startTime = startTime;
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidStartTime(String test) {
        try {
            LocalTime.parse(test, TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LocalTime getTimeValue() { 
        return time;
    }

    @Override
    public String toString() {
        return time.format(TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StartTime
                && time.equals(((StartTime) other).time));
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}

