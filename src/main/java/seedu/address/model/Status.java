package seedu.address.model;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;

/**
 * Represents the abstract status that can be possessed by a class, that has a timestamp of
 * when its status was set.
 */
public abstract class Status {
    private static final int NUM_SECS_IN_A_YEAR = 60 * 60 * 24 * 365;
    private static final int NUM_SECS_IN_A_DAY = 60 * 60 * 24;
    private static final int NUM_SECS_IN_AN_HOUR = 60 * 60;
    private static final int NUM_SECS_IN_A_MINUTE = 60;

    private Instant timestamp;

    public abstract String getInstantInIso();

    public abstract Instant getTimestamp();

    public abstract Duration getDurationSinceLastUpdate();

    /**
     * Returns the English representation of a Duration, only showing the least granular time.
     */
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        if (seconds < 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (seconds == 0) {
            return "0 seconds";
        }
        if (seconds >= NUM_SECS_IN_A_YEAR) {
            long years = seconds / (NUM_SECS_IN_A_YEAR);
            return years + " year" + (years > 1 ? "s" : "");
        }
        if (seconds >= NUM_SECS_IN_A_DAY) {
            long days = seconds / (NUM_SECS_IN_A_DAY);
            return days + " day" + (days > 1 ? "s" : "");
        }
        if (seconds >= NUM_SECS_IN_AN_HOUR) {
            long hours = seconds / (NUM_SECS_IN_AN_HOUR);
            return hours + " hour" + (hours > 1 ? "s" : "");
        }
        if (seconds >= NUM_SECS_IN_A_MINUTE) {
            long minutes = seconds / NUM_SECS_IN_A_MINUTE;
            return minutes + " minute" + (minutes > 1 ? "s" : "");
        }
        return seconds + " second" + (seconds > 1 ? "s" : "");
    }

    /**
     *  Returns true if the input String is parsable into a timestamp.
     */
    public static boolean isValidTimestamp(String test) {
        try {
            Instant.parse(test);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }
}
