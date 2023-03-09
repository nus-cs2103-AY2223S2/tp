package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents an Event's timing in the event book.
 */
public class Timing {
    public static final String MESSAGE_CONSTRAINTS =
            "Timings should be of the format dd-MM-uuuu hh:mm";
    public final String startTime;
    public final String endTime;

    /**
     * Constructs a {@code Timing}.
     *
     * @param startTime A valid starting time.
     * @param endTime A valid ending time.
     */
    public Timing(String startTime, String endTime) {
        requireNonNull(startTime);
        requireNonNull(endTime);
        checkArgument(isValidTiming(startTime, endTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns true if given strings give a valid timing.
     */
    public static boolean isValidTiming(String testStartTime, String testEndTime) {
        return isValidTime(testStartTime) && isValidTime(testEndTime);
    }

    private static boolean isValidTime(String test) {
        if (test == null) {
            return false;
        }
        try {
            LocalDateTime.parse(test, DateTimeFormatter
                    .ofPattern("dd-MM-uuuu HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeException e) {
            return false;
        }

    }

    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timing // instanceof handles nulls
                && startTime.equals(((Timing) other).startTime)
                && endTime.equals(((Timing) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
